/**
 * 系统 日志 管理
 * 
 * @include "../common/communal.js"
 * @include "../pageInit.js"
 * @returns {SysLog}
 * 
 */
function SysLog() {
	var t = this;
	this.gridPageSize = pageInfoCfg.datagrid.pageSize;
	this.winWidth = 600;
	this.winHeight = 360;
	if (Ext.isIE) {
		t.winWidth = 615;
		t.winHeight = 363;

	} else {
		t.winWidth = 610;
		t.winHeight = 352;
	}
	this.gridStore = new Ext.data.Store({
				url : 'system/sys/sysLogController/getGridStore.action',
				reader : new Ext.data.JsonReader({
							root : 'list',
							totalProperty : 'totalProperty',
							fields : [{
										name : 'id',
										mapping : 'id'
									}, {
										name : 'business',
										mapping : 'business'
									}, {
										name : 'oper_type',
										mapping : 'oper_type'
									}, {
										name : 'oper_key',
										mapping : 'oper_key'
									}, {
										name : 'content',
										mapping : 'content'
									}, {
										name : 'success',
										mapping : 'success'
									}, {
										name : 'user_account',
										mapping : 'user_account'
									}, {
										name : 'c_time',
										mapping : 'c_time_format'
									}]
						})
			});
	this.colModel = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
					// header : '序号',
					// width : 40
					}), new Ext.grid.CheckboxSelectionModel(), {
				header : "ID",
				sortable : true,
				dataIndex : 'id',
				width : 120,
				hidden : true
			}, {
				header : "处理业务",
				sortable : true,
				dataIndex : 'business',
				renderer : function(v) {
					return (v == null ? "" : v);
				},
				width : 120
			}, {
				header : "操作",
				sortable : true,
				dataIndex : 'oper_type',
				width : 80
			}, {
				header : "内容",
				sortable : true,
				dataIndex : 'content',
				renderer : function(v) {
					return (v == null ? "" : v);
				},
				width : 600
			}, {
				header : "结果",
				sortable : true,
				dataIndex : 'success',
				renderer : function(v) {
					if (1 == v) {
						return '<font color="green">成功</font>';
					} else if (2 == v) {
						return '<font color="red">失败</font>';
					} else {
						return "";
					}
				},
				width : 80
			}, {
				header : "操作者",
				sortable : true,
				dataIndex : 'user_account',
				width : 140
			}, {
				header : "操作时间",
				sortable : true,
				dataIndex : 'c_time',
				width : 140
			}]);
	this.del = function() {
		var records = t.grid.getSelectionModel().getSelections();
		if (records.length > 0) {
			var id = "";
			for (var i = 0; i < records.length; i++) {
				if (i != 0) {
					id += ",";
				}

				id += "'" + records[i].get('id') + "'";
			}
			ExtMsg.confirm('确定要删除所选项吗？', function(btn) {
						if (btn == 'yes') {
							var del = PassOwner
									.RequestOfPost(
											'system/sys/sysLogController/delete.action',
											{
												ids : id
											}, false);
							msgShow(del.msg);
							if (del.execute) {
								gridLoadDel(t.grid, t.gridPageSize);
							}
						}
					}, 250);

		} else {
			msgShow("至少要选择一条记录进行删除!");
			return false;
		}
	}
	this.searchUserAccount = new Ext.form.TextField({
				width : 120,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});
	this.searchStartTime = new Ext.ux.form.DateTimeField({
				width : 180,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				},
				format : 'Y-m-d H:i:s'
			});
	this.searchStopTime = new Ext.ux.form.DateTimeField({
				width : 180,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				},
				format : 'Y-m-d H:i:s'
			});
	this.searchSuccess = new Ext.form.ComboBox({
				typeAhead : true,
				triggerAction : 'all',
				lazyRender : true,
				mode : 'local',
				emptyText : '--所有--',
				store : new Ext.data.ArrayStore({
							id : 0,
							fields : ['id', 'text'],
							data : [[-1, '--所有--'], [2, '失败'], [1, '成功']]
						}),
				valueField : 'id',
				displayField : 'text'
			});
	this.grantRoleWin = null;
	this.gridTbar = new Ext.Panel({
				border : false,
				layout : 'form',
				items : [{
							xtype : 'toolbar',
							items : [{
										xtype : 'tbspacer',
										width : 15
									}, '操作者：', t.searchUserAccount, {
										xtype : 'tbspacer',
										width : 15
									}, '操作结果：', t.searchSuccess, {
										xtype : 'tbspacer',
										width : 15
									}, '开始时间：', t.searchStartTime, {
										xtype : 'tbspacer',
										width : 15
									}, '结束时间：', t.searchStopTime, {
										xtype : 'tbspacer',
										width : 15
									}, '　', {
										xtype : 'tbbutton',
										iconCls : 'icon-search',
										text : '查询',
										handler : function() {
											gridLoadStart(t.grid, 0,
													t.gridPageSize);
										}
									}]
						}]
			});
	this.getSearchParams = function() {
		var params = {
			user_account : t.searchUserAccount.getValue(),
			success : t.searchSuccess.getValue(),
			startTime : t.searchStartTime.getValue(),
			stopTime: t.searchStopTime.getValue()
		};
		return params;
	}
	this.gridStore.on('beforeload', function(s) {
				s.baseParams = t.getSearchParams();
			}, this);
	this.grid = new Ext.grid.GridPanel({
				anchor : '100% 100%',
				frame : false,
				border : false,
				store : t.gridStore,
				cm : t.colModel,
				tbar : t.gridTbar,
				// plugins : t.colMdExpander,
				loadMask : {
					msg : '<span style="font-size:12px;">正在加载数据...</span>'
				},
				sm : new Ext.grid.CheckboxSelectionModel({
							singleSelect : false
						}),
				viewConfig : {
					forceFit : true
					// 让grid的列自动填满grid的整个宽度，不用一列一列的设定宽
				},
				bbar : new Ext.PagingToolbar({
							pageSize : t.gridPageSize,
							store : t.gridStore,
							displayInfo : true,
							afterPageText : '共{0}页',
							beforePageText : '当前页',
							displayMsg : ' {0} - {1} 条 共 {2} 条记录',
							emptyMsg : "没有记录"
						})

			});

	this.form = new Ext.form.FormPanel({
		border : false,
		buttonAlign : 'center',
		frame : true,
		bodyStyle : 'padding:5px;',
		labelAlign : 'right',
		autoHeight : true,
		labelWidth : 90,
		items : [new Ext.form.FieldSet({
			title : '基本信息',
			autoHeight : true,
			border : true,
			layout : "column",
			anchor : '100%',
			defaults : {
				columnWidth : .5,
				border : false,
				defaults : {
					anchor : '100%'
				}
			},
			items : [{
						xtype : 'hidden',
						name : 'type'
					}, {
						xtype : 'hidden',
						name : 'id'
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>关键字</font>',
									name : 'keyword',
									hiddenName : 'keyword',
									allowBlank : false,
									blankText : '关键字不能为空!',
									maxLength : 20,
									maxLengthText : '关键字不能超过20个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'combo',
									fieldLabel : '<font color=#467CD5>状    态</font>',
									name : 'state',
									hiddenName : 'state',
									editable : false,// 不让编辑
									forceselection : true,
									mode : 'local',
									valueField : 'id',
									displayField : 'text',
									triggerAction : 'all',
									emptyText : '--请选择--',
									allowBlank : false,
									blankText : '状态不能为空!',
									store : new Ext.data.JsonStore({
												id : -1,
												fields : ['id', 'text'],
												data : [{
															id : 2,
															text : '正常'
														}, {
															id : 1,
															text : '废弃'
														}]
											}),
									listeners : {
										scope : this
									}
								}]
					}]
		})],
		buttons : [{
			text : '保存',
			handler : function() {

				console.log(Ext.encode(t.form.getForm().getValues()));

				if (t.form.getForm().isValid() == true) {
					t.form.getForm().submit({
						url : 'system/sys/sysLogController/saveOrUpdate.action',
						waitTitle : '请稍等...',
						waitMsg : '正在提交信息...',
						method : 'post',
						params : {
							jsonData : Ext.encode(t.form.getForm().getValues())
						},
						success : function(form, action) {
							var data = eval(action.result);
							if (data.execute) {
								t.win.hide();
								msgShow(data.msg);
								gridLoad(t.grid, t.gridPageSize, 'id', t.form
												.getForm().findField('id')
												.getValue());
							} else {
								msgShow(data.msg);
							}
						},
						failure : function(form, action) {
							msgShow('数据操作失败!');
						}
					});
				}

			}
		}, {
			text : '取消',
			handler : function() {
				t.win.hide();
				t.form.getForm().reset();
			},
			scope : this
		}]
	});
	this.win = new Ext.Window({
				closeAction : 'hide',
				border : false,
				plain : true,
				constrain : true,
				resizable : false,
				width : t.winWidth,
				modal : true,
				items : t.form
			});
	// 初始化数据方法
	this.infoData = function() {
		// // 加载
		t.gridPageSize = t.gridPageSize + 2;
		gridLoadStart(t.grid, 0, t.gridPageSize);
	}
	// 初始化
	t.infoData();
}
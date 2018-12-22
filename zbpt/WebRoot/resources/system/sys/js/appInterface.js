/**
 * 
 * 函数名称：SysContent<br>
 * 函数说明: 应用接入 <br>
 * 创建人: 王鹏飞<br>
 * 创建日期: 2016-3-31<br>
 * 
 * @include "../common/communal.js"
 * @include "../pageInit.js"
 * @returns {User}
 */
function AppInterface() {
	var t = this;

	this.gridPageSize = pageInfoCfg.datagrid.pageSize;

	this.gridStore = new Ext.data.Store({
				url : 'system/sys/AppInterfaceController/getObjGridStore.action',
				reader : new Ext.data.JsonReader({
							root : 'list',
							totalProperty : 'totalProperty',
							fields : [{
										name : 'id',
										mapping : 'id'
									}, {
										name : 'name',
										mapping : 'name'
									}, {
										name : 'url',
										mapping : 'url'
									}, {
										name : 'state',
										mapping : 'state'
									}, {
										name : 'bz',
										mapping : 'bz'
									}, {
										name : 'creator',
										mapping : 'creator'
									}, {
										name : 'c_time',
										mapping : 'c_time'
									}, {
										name : 'modifier',
										mapping : 'modifier'
									}, {
										name : 'u_time',
										mapping : 'u_time'
									}]
						})
			});

	this.colModel = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
						header : '序号',
						width : 40
					}), new Ext.grid.CheckboxSelectionModel(),
			{
				header : '序号',
				sortable : true,
				dataIndex : 'id'
			}, {
				header : '名称',
				sortable : true,
				dataIndex : 'name',
				width : 100
			}, {
				header : '访问路径',
				sortable : true,
				dataIndex : 'url',
				renderer : function(v) {
					if (null == v) {
						v = '';
					}
					return '<span title="' + v + '">' + v + '</span>';
				},
				width : 500
			}, {
				header : '状态',
				sortable : true,
				dataIndex : 'state',
				renderer : function(v) {
					if ('1' == v) {
						return '<font color="green">启用</font>';
					} else if ('0' == v) {
						return '<font color="red">禁用</font>';
					} else {
						return v;
					}
				},
				width : 100
			}, {
				header : '备注',
				sortable : true,
				dataIndex : 'bz',
				renderer : function(v) {
					if (null == v) {
						v = '';
					}
					return '<span title="' + v + '">' + v + '</span>';
				},
				width : 350
			}, {
				header : '创建者',
				sortable : true,
				dataIndex : 'creator'
			}, {
				header : '创建时间',
				sortable : true,
				dataIndex : 'c_time',
				width : 140
			}, {
				header : '修改者',
				sortable : true,
				dataIndex : 'modifier'
			}, {
				header : '修改时间',
				sortable : true,
				dataIndex : 'u_time',
				width : 140
			}]);
			
	this.del = function() {
		var records = t.grid.getSelectionModel().getSelections();
		if (records.length > 0) {
			var ids = "";
			for (var i = 0; i < records.length; i++) {
				if (i != 0) {
					ids += ",";
				}
				ids += "'" + records[i].get('id') + "'";
			}
			ExtMsg.confirm('确定要删除所选项吗？', function(btn) {
						if (btn == 'yes') {
							var del = PassOwner
									.RequestOfPost(
											'system/sys/AppInterfaceController/delete.action',
											{
												ids : ids
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
	};

	this.search1 = new Ext.form.TextField({
				width : 200,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});

	this.search2 = new Ext.form.ComboBox({
				typeAhead : true,
				triggerAction : 'all',
				lazyRender : true,
				mode : 'local',
				emptyText : '--全部--',
				store : new Ext.data.ArrayStore({
							id : 0,
							fields : ['id', 'text'],
							data : [['', '--全部--'], ['1', '启用'],
									['0', '禁用']]
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
					id : 'wzTbone',
					frame : true,
					margins : '0 0 0 0',
					items : [{
						xtype : 'tbbutton',
						text : '增加',
						iconCls : 'icon-add',
						ref : '../addObj',
						tooltip : '增加应用接入',
						handler : function() {
							t.win.setTitle("增加信息");
							t.win.show();
							t.form.getForm().reset();
							t.form.getForm().findField('isAdd').setValue("true");
							t.form.getForm().findField('id').setReadOnly(false);
						}
					}, {
						xtype : 'tbseparator',
						ref : '../addObj_temp'
					}, {
						xtype : 'tbbutton',
						text : '修改',
						tooltip : '修改应用接入',
						iconCls : 'icon-edit',
						ref : '../editObj',
						handler : function() {
							var records = t.grid.getSelectionModel().getSelections();
							if (records.length == 1) {
								var rd = records[0];
								t.win.setTitle("修改信息");
								t.win.show();
								t.form.getForm().reset();
								t.form.getForm().loadRecord(rd);
								t.form.getForm().loadRecord(records[0]);
								t.form.getForm().findField('isAdd').setValue("false");
								t.form.getForm().findField('id').setReadOnly(true);
							} else {
								msgShow("请选择一条记录进行修改!");
								return false;
							}
						}
					}, {
						xtype : 'tbseparator',
						ref : '../editObj_temp'
					}, {
						xtype : 'tbbutton',
						text : '删除',
						tooltip : '删除应用接入',
						iconCls : 'icon-delete',
						ref : '../delObj',
						handler : t.del.createCallback()
					}, {
						xtype : 'tbseparator',
						ref : '../delObj_temp'
					}]
				}, {
					xtype : 'toolbar',
					items : [{
								xtype : 'tbspacer',
								width : 15
							}, '状态：', t.search2, {
								xtype : 'tbspacer',
								width : 15
							}, '名称：', t.search1, {
								xtype : 'tbspacer',
								width : 15
							}, '　', {
								xtype : 'tbbutton',
								iconCls : 'icon-search',
								text : '查询',
								handler : function() {
									gridLoadStart(t.grid, 0, t.gridPageSize);
								}
							}]
				}]
			});
	this.getSearchParams = function() {
		var params = {
			state : t.search2.getValue(),
			name : t.search1.getValue()
		};
		return params;
	};
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
				loadMask : {
					msg : '<span style="font-size:12px;">正在加载数据...</span>'
				},
				sm : new Ext.grid.CheckboxSelectionModel({
							singleSelect : false
						}),
				viewConfig : {

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
		labelWidth : pageInfoCfg.form.doubleColumnLabelWidth,
		items : [new Ext.form.FieldSet({
			title : '基本信息',
			autoHeight : true,
			border : true,
			layout : "column",
			anchor : '100%',
			defaults : {
				columnWidth : 1,
				border : false,
				defaults : {
					anchor : '100%'
				}
			},
			items : [{
						xtype : 'hidden',
						name : 'isAdd'
					}, {
						xtype : 'hidden',
						name : 'id'
					}, {
						layout : 'form',
						items : [{
									xtype : 'combo',
									fieldLabel : '<font color=#467CD5>状态</font>',
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
												id : 1,
												fields : ['id', 'text'],
												data : [{
															id : '1',
															text : '启用'
														}, {
															id : '0',
															text : '禁用'
														}]
											}),
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>名称</font>',
									name : 'name',
									allowBlank : false,
									blankText : '名称不能为空!',
									maxLength : 20,
									maxLengthText : '名称不能超过20个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>访问路径</font>',
									name : 'url',
									allowBlank : false,
									blankText : '访问路径不能为空!',
									maxValue : 100,
									maxValueText : '访问路径不能超过100个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textarea',
									fieldLabel : '备注',
									name : 'bz',
									maxLength : 200,
									maxLengthText : '备注不能超过200个字符!',
									listeners : {
										scope : this
									}
								}]
					}]
		})],
		buttons : [{
			text : '保存',
			handler : function() {
				if (t.form.getForm().isValid() == true) {
					t.form.getForm().submit({
						url : 'system/sys/AppInterfaceController/saveOrUpdate.action',
						waitTitle : pageInfoCfg.form.submit.waitTitle,
						waitMsg : pageInfoCfg.form.submit.waitMsg,
						method : 'POST',
						success : function(form, action) {
							var data = action.result;
							if (data.execute) {
								gridLoad(t.grid, t.gridPageSize, 'id', t.form
												.getForm().findField('id')
												.getValue());
								msgShow(data.msg);
								t.win.hide();
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
				width : ExtMyUtil
						.getWidth(pageInfoCfg.window.singleColumnWidth),
				modal : true,
				items : t.form
			});
	// 初始化数据方法
	this.infoData = function() {
		// 按钮 权限 控制
		buttonPowerFn("1001_113_101", t.gridTbar.addObj);
		buttonPowerFn("1001_113_101", t.gridTbar.addObj_temp);
		buttonPowerFn("1001_113_102", t.gridTbar.editObj);
		buttonPowerFn("1001_113_102", t.gridTbar.editObj_temp);
		buttonPowerFn("1001_113_103", t.gridTbar.delObj);
		buttonPowerFn("1001_113_103", t.gridTbar.delObj_temp);
		var isTbarHide = tbarIsVisible(t.gridTbar.items.itemAt(0));
		if (isTbarHide) {
			// t.gridPageSize = 22;
		}
		// 加载
		gridLoadStart(t.grid, 0, t.gridPageSize);
	}
	// 初始化
	t.infoData();
}
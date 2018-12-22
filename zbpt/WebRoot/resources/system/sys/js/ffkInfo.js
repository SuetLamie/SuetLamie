/**
 * 
 * 函数名称：SysContent<br>
 * 函数说明: 分发库 <br>
 * 创建人: 王鹏飞<br>
 * 创建日期: 2016-3-31<br>
 * 
 * @include "../common/communal.js"
 * @include "../pageInit.js"
 * @returns {User}
 */

function FfkInfo() {
	var t = this;

	this.gridPageSize = pageInfoCfg.datagrid.pageSize;

	this.gridStore = new Ext.data.Store({
				url : 'system/sys/FfkInfoController/getObjGridStore.action',
				reader : new Ext.data.JsonReader({
							root : 'list',
							totalProperty : 'totalProperty',
							fields : [{
										name : 'id',
										mapping : 'id'
									}, {
										name : 'mc',
										mapping : 'mc'
									}, {
										name : 'bz',
										mapping : 'bz'
									}]
						})
			});

	this.comModelBzExpander = new Ext.ux.grid.RowExpander({
		tpl : new Ext.Template('<pre style="line-height:1.7; border:#999999 1px dotted; background:#F7F9FC; padding:5px; color:#888888;"><b>备注:</b>{bz}</pre>')
	});

	this.colModel = new Ext.grid.ColumnModel([t.comModelBzExpander, {
//				header : '序号',
//				sortable : true,
//				width : 80,
//				dataIndex : 'xh'
//			}, {
				header : '接口ID',
				sortable : true,
				width : 80,
				dataIndex : 'id'
			}, {
				header : '名称',
				sortable : true,
				width : 200,
				dataIndex : 'mc'
			}, {
				header : '备注',
				sortable : true,
				width : 120,
				dataIndex : 'bz',
				renderer : function(v) {
					return '<span title="'+v+'">'+v+'</span>';
				}
			}]);
	this.del = function() {
		var records = t.grid.getSelectionModel().getSelections();
		if (records.length > 0) {
			var ids = "";
			for (var i = 0; i < records.length; i++) {
				if (i != 0) {
					ids += ",";
				}
				ids += "'" + records[i].get('xh') + "'";
			}
			ExtMsg.confirm('确定要删除所选项吗？', function(btn) {
						if (btn == 'yes') {
							var del = PassOwner
									.RequestOfPost(
											'system/sys/xxxxxxxx/delete.action',
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
				width : 120,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});
	this.search2 = new Ext.form.TextField({
				width : 120,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});
	this.grantRoleWin = null;
	this.gridTbar = new Ext.Panel({
				border : false,
				layout : 'form',
				items : [{
//					xtype : 'toolbar',
//					id : 'wzTbone',
//					frame : true,
//					margins : '0 0 0 0',
//					items : [{
//						xtype : 'tbbutton',
//						text : '增加',
//						iconCls : 'icon-add',
//						ref : '../addObj',
//						tooltip : '增加分发库',
//						handler : function() {
//							t.win.setTitle("增加信息");
//							t.win.show();
//							t.form.getForm().reset();
//							t.form.getForm().findField('isAdd')
//									.setValue("true");
//						}
//					}, {
//						xtype : 'tbseparator',
//						ref : '../addObj_temp'
//					}, {
//						xtype : 'tbbutton',
//						text : '修改',
//						tooltip : '修改信息',
//						iconCls : 'icon-edit',
//						ref : '../editObj',
//						handler : function() {
//							var records = t.grid.getSelectionModel()
//									.getSelections();
//							if (records.length == 1) {
//								var rd = records[0];
//								t.win.setTitle("修改信息");
//								t.win.show();
//								t.form.getForm().reset();
//								t.form.getForm().loadRecord(rd);
//								t.form.getForm().loadRecord(records[0]);
//								t.form.getForm().findField('isAdd')
//										.setValue("false");
//							} else {
//								msgShow("请选择一条记录进行修改!");
//								return false;
//							}
//						}
//					}, {
//						xtype : 'tbseparator',
//						ref : '../editObj_temp'
//					}, {
//						xtype : 'tbbutton',
//						text : '删除',
//						tooltip : '删除信息',
//						iconCls : 'icon-delete',
//						ref : '../delObj',
//						handler : t.del.createCallback()
//					}, {
//						xtype : 'tbseparator',
//						ref : '../delObj_temp'
//					}]
//				}, {
					xtype : 'toolbar',
					items : [{
								xtype : 'tbspacer',
								width : 15
							}, '名称：', t.search1, {
								xtype : 'tbspacer',
								width : 15
							}, '备注：', t.search2, {
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
			mc : t.search1.getValue(),
			bz : t.search2.getValue()
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
				plugins : t.comModelBzExpander,
				loadMask : {
					msg : '<span style="font-size:12px;">正在加载数据...</span>'
				},
				sm : new Ext.grid.CheckboxSelectionModel({
							singleSelect : false
						}),
				viewConfig : {
					forceFit : true
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
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>序号</font>',
									name : 'xh',
									allowBlank : false,
									blankText : '序号不能为空!',
									maxLength : 4,
									maxLengthText : '序号不能超过4个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>接口id</font>',
									name : 'id',
									allowBlank : false,
									blankText : '接口id不能为空!',
									maxLength : 5,
									maxLengthText : '接口id不能超过5个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>接口状态</font>',
									name : 'zt',
									allowBlank : false,
									blankText : '接口状态不能为空!',
									maxLength : 50,
									maxLengthText : '接口状态不能超过50个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>接口名称</font>',
									name : 'mc',
									allowBlank : false,
									blankText : '接口名称不能为空!',
									maxLength : 100,
									maxLengthText : '接口名称不能超过100个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
							xtype : 'combo',
									fieldLabel : '<font color=#467CD5>接口类型</font>',
									name : 'lx',
									hiddenName : 'lx',
									editable : false,// 不让编辑
									forceselection : true,
									mode : 'local',
									valueField : 'id',
									displayField : 'text',
									triggerAction : 'all',
									emptyText : '--请选择--',
									allowBlank : false,
									blankText : '接口类型为空!',
									store : new Ext.data.JsonStore({
												id : 1,
												fields : ['id', 'text'],
												data : [{
															id : 1,
															text : "查询接口"
														}, {
															id : 2,
															text : "写入接口"
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
							fieldLabel : '<font color=#467CD5>访问量(次/天)</font>',
							name : 'rjfwl',
							allowBlank : false,
							blankText : '日均访问量(次/天)不能为空!',
							listeners : {
								scope : this
							}
						}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textarea',
									fieldLabel : '<font color=#467CD5>备注</font>',
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
						url : 'system/sys/JcywnlController/saveOrUpdate.action',
						waitTitle : pageInfoCfg.form.submit.waitTitle,
						waitMsg : pageInfoCfg.form.submit.waitMsg,
						method : 'POST',
						success : function(form, action) {
							var data = action.result;
							if (data.execute) {
								gridLoad(t.grid, t.gridPageSize, 'xh', t.form
												.getForm().findField('xh')
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
//		buttonPowerFn("1001_108_101", t.gridTbar.addObj);
//		buttonPowerFn("1001_108_101", t.gridTbar.addObj_temp);
//		buttonPowerFn("1001_108_102", t.gridTbar.editObj);
//		buttonPowerFn("1001_108_102", t.gridTbar.editObj_temp);
//		buttonPowerFn("1001_108_103", t.gridTbar.delObj);
//		buttonPowerFn("1001_108_103", t.gridTbar.delObj_temp);
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
/**
 * 
 * 函数名称：SysContent<br>
 * 函数说明: 平台审计 <br>
 * 创建人: 王鹏飞<br>
 * 创建日期: 2016-3-31<br>
 * 
 * @include "../common/communal.js"
 * @include "../pageInit.js"
 * @returns {User}
 */

function WsdlStubCfg() {
	var t = this;

	this.gridPageSize = pageInfoCfg.datagrid.pageSize;

	this.gridStore = new Ext.data.Store({
				url : 'system/sys/WsdlStubCfgController/getObjGridStore.action',
				reader : new Ext.data.JsonReader({
							root : 'list',
							totalProperty : 'totalProperty',
							fields : [{
										name : 'wsdl_id',
										mapping : 'wsdl_id'
									}, {
										name : 'wsdl_name',
										mapping : 'wsdl_name'
									}, {
										name : 'wsdl_url',
										mapping : 'wsdl_url'
									}, {
										name : 'jksqm',
										mapping : 'jksqm'
									}]
						})
			});
	this.colModel = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
						header : '序号',
						width : 40
					}), new Ext.grid.CheckboxSelectionModel(), {
				// header : '唯一标识',
				// sortable : true,
				// dataIndex : 'id'
				// }, {
				header : '主键',
				sortable : true,
				width : 80,
				dataIndex : 'wsdl_id'
			}, {
				header : '名称',
				sortable : true,
				width : 140,
				dataIndex : 'wsdl_name'
			}, {
				header : '地址',
				sortable : true,
				width : 300,
				dataIndex : 'wsdl_url'
			}, {
				header : '接口授权码',
				sortable : true,
				width : 300,
				dataIndex : 'jksqm',
				renderer : function(v) {
					return '<span title="'+v+'">'+v+'</span>';
				},
			}]);
	this.del = function() {
		var records = t.grid.getSelectionModel().getSelections();
		if (records.length > 0) {
			var ids = "";
			for (var i = 0; i < records.length; i++) {
				if (i != 0) {
					ids += ",";
				}
				ids += "'" + records[i].get('wsdl_id') + "'";
			}
			ExtMsg.confirm('确定要删除所选项吗？', function(btn) {
						if (btn == 'yes') {
							var del = PassOwner
									.RequestOfPost(
											'system/sys/WsdlStubCfgController/delete.action',
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
						tooltip : '增加平台审计',
						handler : function() {
							t.win.setTitle("增加信息");
							t.win.show();
							t.form.getForm().reset();
							t.form.getForm().findField('isAdd')
									.setValue("true");
							t.form.getForm().findField('wsdl_id').setReadOnly(false);
						}
					}, {
						xtype : 'tbseparator',
						ref : '../addObj_temp'
					}, {
						xtype : 'tbbutton',
						text : '修改',
						tooltip : '修改信息',
						iconCls : 'icon-edit',
						ref : '../editObj',
						handler : function() {
							var records = t.grid.getSelectionModel()
									.getSelections();
							if (records.length == 1) {
								var rd = records[0];
								t.win.setTitle("修改信息");
								t.win.show();
								t.form.getForm().reset();
								t.form.getForm().loadRecord(rd);
								t.form.getForm().loadRecord(records[0]);
								t.form.getForm().findField('isAdd')
										.setValue("false");
								t.form.getForm().findField('wsdl_id').setReadOnly(true);
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
						tooltip : '删除信息',
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
			wsdl_name : t.search1.getValue()
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
				// plugins : t.colMdExpander,
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
									fieldLabel : '<font color=#467CD5>主键</font>',
									name : 'wsdl_id',
									allowBlank : false,
									blankText : '主键不能为空!',
									maxLength : 4,
									maxLengthText : '主键不能超过4个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>名称</font>',
									name : 'wsdl_name',
									allowBlank : false,
									blankText : '名称不能为空!',
									maxLength : 50,
									maxLengthText : '名称不能超过50个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>地址</font>',
									name : 'wsdl_url',
									allowBlank : false,
									blankText : '地址不能为空!',
									maxLength : 100,
									maxLengthText : '地址不能超过100个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textarea',
									fieldLabel : '<font color=#467CD5>接口授权码</font>',
									name : 'jksqm',
									allowBlank : false,
									blankText : '接口授权码不能为空!',
									maxLength : 100,
									maxLengthText : '接口授权码不能超过100个字符!',
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
						url : 'system/sys/WsdlStubCfgController/saveOrUpdate.action',
						waitTitle : pageInfoCfg.form.submit.waitTitle,
						waitMsg : pageInfoCfg.form.submit.waitMsg,
						method : 'POST',
						success : function(form, action) {
							var data = action.result;
							if (data.execute) {
								gridLoad(t.grid, t.gridPageSize, 'wsdl_id', t.form
												.getForm().findField('wsdl_id')
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
		// 按钮 权限 控制  tem 为添加内容 不显示按钮
		buttonPowerFn("1001_109_101_tem", t.gridTbar.addObj);
		buttonPowerFn("1001_109_101_tem", t.gridTbar.addObj_temp);
		buttonPowerFn("1001_109_102_tem", t.gridTbar.editObj);
		buttonPowerFn("1001_109_102_tem", t.gridTbar.editObj_temp);
		buttonPowerFn("1001_109_103_tem", t.gridTbar.delObj);
		buttonPowerFn("1001_109_103_tem", t.gridTbar.delObj_temp);
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
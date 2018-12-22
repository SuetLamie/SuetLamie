Ext.namespace('com.system.sys');
/**
 * @author 方曙强
 * @param {}
 *            config
 */

function dfPerson() {
	var t = this;
	this.roleId = "";
	this.gridPageSize = pageInfoCfg.datagrid.pageSize;
	this.gridStore = new Ext.data.Store({
				url : 'system/sys/roleController/getDfpRoleUserGridStore.action',
				reader : new Ext.data.JsonReader({
							root : 'list',
							totalProperty : 'totalProperty',
							fields : ['xh', 'xm', 'bmqc', 'jybh']
						})
			});
	this.colModel = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
						header : '序号',
						width : 40
					}), new Ext.grid.CheckboxSelectionModel(), {
				header : "警号",
				sortable : true,
				dataIndex : 'jybh',
				width : 40
			}, {
				header : "姓名",
				sortable : true,
				dataIndex : 'xm',
				width : 40
			}, {
				header : "所属部门",
				sortable : true,
				dataIndex : 'bmqc'
			}]);
	this.search_userName = new Ext.form.TextField({
				width : 90,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});
	this.selectTreeNode = null;
	this.search_depName = new topExt.form.ComboBoxTree({
				width : 200,
				// allowBlank : false,
				tree : new topExt.tree.TreePanel({
							height : 300,
							autoScroll : true,
							border : false,
							rootVisible : true,
							loader : new topExt.tree.TreeLoader({
										dataUrl : 'system/sys/departmentController/getDepartmentTreeNode.action'
									}),
							root : {
								nodeType : 'async',
								expanded : true,
								text : CommUtil.getSysInfo.loginUser.bmdmText,
								id : CommUtil.getSysInfo.loginUser.bmdm
							},
							listeners : {
								click : function(node, e) {
									t.selectTreeNode = node;
								}
							}
						}),
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == topExt.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});
	this.gridStore.on('beforeload', function(s) {
				s.baseParams = {
					'roleId' : t.roleId,
					'jybh' : t.search_userName.getValue()
				};
				if (t.selectTreeNode) {
					s.baseParams.bmdm = t.selectTreeNode.attributes.path;
				}
			}, this);

	this.gridTbar = new topExt.Toolbar({
				items : [{
							xtype : 'tbspacer',
							width : 5
						}, '警号：', t.search_userName, {
							xtype : 'tbspacer',
							width : 5
						}, '部门：', t.search_depName, '　', {
							xtype : 'tbbutton',
							iconCls : 'icon-search',
							text : '查询',
							handler : function() {
								gridLoadStart(t.grid, 0, t.gridPageSize);
							}
						}]
			});

	this.grid = new topExt.grid.GridPanel({
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
				sm : new topExt.grid.CheckboxSelectionModel({
							singleSelect : false
						}),
				viewConfig : {
					forceFit : true
				},
				bbar : new topExt.PagingToolbar({
							pageSize : t.gridPageSize,
							store : t.gridStore,
							displayInfo : true,
							afterPageText : '共{0}页',
							beforePageText : '第',
							displayMsg : ' {0} - {1} 条 共 {2} 条',
							emptyMsg : "没有记录"
						})
			});
}
function yjfpPerson() {
	var t = this;
	this.roleId = "";
	this.gridPageSize = 20;
	this.gridStore = new Ext.data.Store({
				url : 'system/sys/roleController/getYjfpRoleUserGridStore.action',
				reader : new Ext.data.JsonReader({
							root : 'list',
							totalProperty : 'totalProperty',
							fields : ['xh', 'xm', 'bmqc', 'jybh']

						})
			});
	this.colModel = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
						header : '序号',
						width : 40
					}), new Ext.grid.CheckboxSelectionModel(), {
				header : "警号",
				sortable : true,
				dataIndex : 'jybh',
				width : 40
			}, {
				header : "姓名",
				sortable : true,
				dataIndex : 'xm',
				width : 40
			}, {
				header : "所属部门",
				sortable : true,
				dataIndex : 'bmqc'
			}]);
	this.search_userName = new Ext.form.TextField({
				width : 90,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});
	this.selectTreeNode = null;
	this.search_depName = new topExt.form.ComboBoxTree({
				width : 200,
				// allowBlank : false,
				tree : new topExt.tree.TreePanel({
							height : 300,
							autoScroll : true,
							border : false,
							rootVisible : true,
							loader : new topExt.tree.TreeLoader({
										dataUrl : 'system/sys/departmentController/getDepartmentTreeNode.action'
									}),
							root : {
								nodeType : 'async',
								expanded : true,
								text : CommUtil.getSysInfo.loginUser.bmdmText,
								id : CommUtil.getSysInfo.loginUser.bmdm
							},
							listeners : {
								click : function(node, e) {
									t.selectTreeNode = node;
								}
							}
						}),
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == topExt.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});
	this.gridStore.on('beforeload', function(s) {
				s.baseParams = {
					'roleId' : t.roleId,
					'jybh' : t.search_userName.getValue()
				};
				if (t.selectTreeNode) {
					s.baseParams.bmdm = t.selectTreeNode.attributes.path;
				}
			}, this);

	this.gridTbar = new topExt.Toolbar({
				items : [{
							xtype : 'tbspacer',
							width : 5
						}, '警号：', t.search_userName, {
							xtype : 'tbspacer',
							width : 5
						}, '部门：', t.search_depName, '　', {
							xtype : 'tbbutton',
							iconCls : 'icon-search',
							text : '查询',
							handler : function() {
								gridLoadStart(t.grid, 0, t.gridPageSize);
							}
						}]
			});

	this.grid = new topExt.grid.GridPanel({
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
				sm : new topExt.grid.CheckboxSelectionModel({
							singleSelect : false
						}),
				viewConfig : {
					forceFit : true
				},
				bbar : new topExt.PagingToolbar({
							pageSize : t.gridPageSize,
							store : t.gridStore,
							displayInfo : true,
							afterPageText : '共{0}页',
							beforePageText : '第',
							displayMsg : ' {0} - {1} 条 共 {2} 条',
							emptyMsg : "没有记录"
						})
			});
}
com.system.sys.GrantUsersToRole = function(config) {
	// this.layout = 'border';
	var t = this;
	this.roleId = "";
	this.gridWidth = 480;
	this.dfpUs = new dfPerson(t.roleId);
	this.yjfpUs = new yjfpPerson(t.roleId);
	this.panle = new topExt.Panel({
		anchor : '100% 100%',
		layout : 'border',
		items : [{
					region : 'west',
					title : '待分配的用户',
					margins : '5 0 5 5',
					layout : 'anchor',
					width : t.gridWidth,
					items : t.dfpUs.grid
				}, {
					region : 'center',
					autoScroll : true,
					border : false,
					frame : true,
					margins : '5 2 5 2',
					layout : 'form',
					html : '<table style="width:100%; height:100%"><tr><td valign="middle" align="center"><div id="add_btn"></div><br><div id="del_btn"></div><br><div id="reset_btn"></div><br></td></tr></table>'
				}, {
					region : 'east',
					title : '已经分配的用户',
					width : t.gridWidth,
					margins : '5 5 5 0',
					layout : 'anchor',
					items : t.yjfpUs.grid
				}]
	});
	this.refresh = function() {
		t.dfpUs.roleId = t.roleId;
		t.yjfpUs.roleId = t.roleId;
		gridLoadStart(t.dfpUs.grid, 0, t.dfpUs.gridPageSize);
		gridLoadStart(t.yjfpUs.grid, 0, t.yjfpUs.gridPageSize);
	}
	this.addBtn = new topExt.Button({
				text : '增加',
				width : 50,
				handler : function() {
					var records = t.dfpUs.grid.getSelectionModel().getSelections();
					if (records.length > 0) {
						var ids = "";
						for (var i = 0; i < records.length; i++) {
							ids += records[i].get('xh') + ",";
						}
						ids = ids.substring(0, ids.length - 1);
						var addObj = PassOwner.RequestOfPost('system/sys/roleController/changeRoleUsers.action', {
									'ids' : ids,
									'roleId' : t.roleId,
									'contrl' : 'add'
								}, false);
						msgShow(addObj.msg);
						if (addObj.execute) {
							t.refresh();
						}
					} else {
						msgShow("请选择待分配记录!");
						return false;
					}
				}
			});
	this.delBtn = new topExt.Button({
				text : '删除',
				width : 50,
				handler : function() {
					var records = t.yjfpUs.grid.getSelectionModel().getSelections();
					if (records.length > 0) {
						var ids = "";
						for (var i = 0; i < records.length; i++) {
							ids += records[i].get('xh') + ",";
						}
						ids = ids.substring(0, ids.length - 1);
						var addObj = PassOwner.RequestOfPost('system/sys/roleController/changeRoleUsers.action', {
									'ids' : ids,
									'roleId' : t.roleId,
									'contrl' : 'del'
								}, false);
						msgShow(addObj.msg);
						if (addObj.execute) {
							t.refresh();
						}
					} else {
						msgShow("请选择已经分配记录!");
						return false;
					}
				}
			});
	this.resetSearch = function() {
		t.dfpUs.search_userName.reset();
		t.dfpUs.search_depName.reset();
		t.yjfpUs.search_userName.reset();
		t.yjfpUs.search_depName.reset();
	}
	this.resetBtn = new topExt.Button({
				text : '刷新',
				width : 50,
				handler : function() {
					t.resetSearch();
					t.refresh();
				}
			});
	this.rendeBtn = function() {
		t.addBtn.render("add_btn");
		t.delBtn.render("del_btn");
		t.resetBtn.render("reset_btn");
	}
	this.items = t.panle;
	Ext.apply(this, config, {
				listeners : {
					'activate' : function() {
						this.infoData();
					},
					scope : this
				}
			});
	com.system.sys.GrantUsersToRole.superclass.constructor.call(this, {
				title : this.title, // 标题
				plain : true, // 强制背景色
				closeAction : "hide",
				layout : 'anchor',
				height : 410,
				width : t.gridWidth * 2 + 100,
				modal : true, // 当前窗体为模态窗体
				resizable : false, // 不允许改变窗体大小
				border : false
			});
}
Ext.extend(com.system.sys.GrantUsersToRole, topExt.Window, {
			infoData : function() {
			}
		});
// Ext.onReady(function() {
// // type 1：应急班 2：施工队
// var win = new com.system.sys.GrantUsersToRole({
// title : '批量分配用户'
// });
// win.roleId = "1001";
// win.resetSearch();
// win.refresh();
// win.show();
// win.rendeBtn();
// // win.refresh();
// });

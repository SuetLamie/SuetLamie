/**
 * 系统 日志 管理
 * 
 * @include "../common/communal.js"
 * @include "../pageInit.js"
 * @returns {SysLog}
 * 
 */
function AppControlLog() {
	var t = this;
	this.gridPageSize = pageInfoCfg.datagrid.pageSize;;
	this.gridStore = new Ext.data.Store({
				url : 'system/sys/appControlLogController/getGridStore.action',
				reader : new Ext.data.JsonReader({
							root : 'list',
							totalProperty : 'totalProperty',
							fields : [{
										name : 'id',
										mapping : 'id'
									}, {
										name : 'ctr_user_id',
										mapping : 'ctr_user_id'
									}, {
										name : 'ctr_user_name',
										mapping : 'ctr_user_name'
									}, {
										name : 'itfc_id',
										mapping : 'itfc_id'
									}, {
										name : 'itfc_name',
										mapping : 'itfc_name'
									}, {
										name : 'request_path',
										mapping : 'request_path'
									}, {
										name : 'request_parmeter',
										mapping : 'request_parmeter'
									}, {
										name : 'request_sj',
										mapping : 'request_sj'
									}]
						})
			});
	this.colMdExpander = new Ext.ux.grid.RowExpander({
		tpl : new Ext.Template('<pre style="line-height:1.7; border:#999999 1px dotted; background:#F7F9FC; padding:5px; color:#888888;"><b>请求参数:</b>{request_parmeter}</pre>')
	});
	this.colModel = new Ext.grid.ColumnModel([t.colMdExpander, {
				header : "序号",
				sortable : true,
				dataIndex : 'id',
				width : 120
			}, {
				header : "操作人警号",
				sortable : true,
				dataIndex : 'ctr_user_id',
				width : 120
			}, {
				header : "操作人姓名",
				sortable : true,
				dataIndex : 'ctr_user_name',
				width : 80
			}, {
				header : "访问接口",
				sortable : true,
				dataIndex : 'itfc_name'
			}, {
				header : "请求时间",
				sortable : true,
				dataIndex : 'request_sj',
				renderer : ExtMyUtil.rendererDateFormat('Y-m-d H:i:s')
			}]);
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
	this.searchInterfaceComboBox = new Ext.form.ComboBox({
				typeAhead : true,
				triggerAction : 'all',
				lazyRender : true,
				mode : 'remote',
				editable : false,// 不让编辑
				emptyText : '--全部--',
				store : new Ext.data.JsonStore({
							url : 'system/sys/appControlLogController/getInterfaceCombox.action',
							fields : ['id', 'name'],
							listeners : {
								'load' : function(st, rds, op) {
									op.callback = function(r) {
										var fst = new st.recordType({
													id : '',
													name : '--全部--'
												});
										st.insert(0, fst);
									}
								}
							}
						}),
				valueField : 'id',
				displayField : 'name'
			});
	this.searchStart = new Ext.ux.form.DateTimeField({
				width : 180,
				format : 'Y-m-d H:i:s',
				// value:data,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});

	this.searchEnd = new Ext.ux.form.DateTimeField({
				width : 180,
				format : 'Y-m-d H:i:s',
				// value:Ext.util.Format.date(Ext.Date.add(new
				// Date(),Ext.Date.MONTH,-1),"Y-m-d"),
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});
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
									}, '接口：', t.searchInterfaceComboBox, {
										xtype : 'tbspacer',
										width : 15
									}, '开始时间：', t.searchStart, {
										xtype : 'tbspacer',
										width : 15
									}, '结束时间：', t.searchEnd, {
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
			ctr_user_id : t.searchUserAccount.getValue(),
			itfc_id : t.searchInterfaceComboBox.getValue(),
			searchStart : t.searchStart.getValue(),
			searchEnd : t.searchEnd.getValue()
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
				plugins : t.colMdExpander,
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
	// 初始化数据方法
	this.infoData = function() {
		// // 加载
		t.gridPageSize = t.gridPageSize + 2;
		gridLoadStart(t.grid, 0, t.gridPageSize);
	}
	// 初始化
	t.infoData();
}
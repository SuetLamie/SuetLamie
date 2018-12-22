/**
 * 
 * 函数名称：SysControlWs<br>
 * 函数说明: 管理控制 <br>
 * 创建人: 王鹏飞<br>
 * 创建日期: 2016-3-31<br>
 * 
 * @include "../common/communal.js"
 * @include "../pageInit.js"
 * @returns {SysControlWs}
 */

function SysControlWs() {
	var t = this;
	this.gridStore = new Ext.data.Store({
				url : 'system/sys/sysControlWsController/getGridStore.action',
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
									}]
						})
			});
	this.colModel = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
						header : '序号',
						width : 40
					}), new Ext.grid.CheckboxSelectionModel(), {
				// header : "描述",
				// sortable : true,
				// dataIndex : 'id',
				// hidden : true,
				// width : 140
				// }, {
				header : "接口名称",
				sortable : true,
				dataIndex : 'name',
				width : 200
			}, {
				header : "访问地址",
				sortable : true,
				dataIndex : 'url',
				hidden : true,
				width : 500
			}, {
				header : "状态",
				sortable : true,
				dataIndex : 'st',
				width : 500
			}, {
				header : "验证",
				sortable : true,
				dataIndex : 'url',
				hidden : false,
				renderer : function(value, metadata, record, rowIndex,
						colIndex, store) {
					// ' + value+',
					return '<a onclick=yanzheng("' + value + '","' + rowIndex
							+ '")>验证</a>';
				},
				width : 100
			}]);

	this.send = function() {
		var records = t.grid.getSelectionModel().getSelections();
		if (records.length > 0) {
			var userIds = "";
			for (var i = 0; i < records.length; i++) {
				var scUrl = records.getIndexById();
				alert(scUrl);

				// TODO ( 添加 调用接口的 action 请求 并处理 )
				// TODO ( 添加 调用接口的 action 请求 并处理 )
				// TODO ( 添加 调用接口的 action 请求 并处理 )
				// TODO ( 添加 调用接口的 action 请求 并处理 )

			}
		} else {
			msgShow("至少要选择一个接口进行查看!");
			return false;
		}
	}
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
										iconCls : 'icon-search',
										text : '查询',
										handler : function() {
											gridLoadStart(t.grid, 0,
													t.gridPageSize);
										}
									}, {
										xtype : 'tbseparator',
										ref : '../delUser_temp'
									}, {
										xtype : 'tbbutton',
										text : '验证',
										tooltip : '验证接口信息',
										// iconCls : 'icon-delete',
										ref : '../send',
										handler : t.send.createCallback()
									}, {
										xtype : 'tbseparator',
										ref : '../send_temp'
									}]
						}]
			});
	this.gridStore.on('beforeload', function(s) {
				// s.baseParams = t.getSearchParams();
			}, this);
	this.grid = new Ext.grid.EditorGridPanel({
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
							singleSelect : true
						}),
				viewConfig : {
	 forceFit : true
				}
			});

	// 初始化数据方法
	this.infoData = function() {
		// 按钮 权限 控制
		buttonPowerFn("1001_105_101", t.gridTbar.send);
		buttonPowerFn("1001_105_101", t.gridTbar.send_temp);
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

function yanzheng(value, rowIndex) {
	alert("rowIndex" + rowIndex + "][value" + value);
	var sbcData = PassOwner.RequestOfPost(v);
	if (null != sbcData) {
		//
	} else {
		//
	}
}


function ConfigDs() {
	var t = this;

	this.gridPageSize = pageInfoCfg.datagrid.pageSize;

	this.gridStore = new Ext.data.Store({
				url : 'system/sys/configDsController/getObjGridStore.action',
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
										name : 'updateTime',
										mapping : 'updateTime'
									}, {
										name : 'action',
										mapping : 'action'
									}]
						})
			});
	this.colModel = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
						header : '序号',
						width : 40
					}), new Ext.grid.CheckboxSelectionModel(), {
				header : '主键',
				sortable : true,
				width : 80,
				dataIndex : 'id'
			}, {
				header : '更新名称',
				sortable : true,
				width : 140,
				dataIndex : 'name'
			}, {
				header : '更新数据时间',
				sortable : true,
				width : 300,
				dataIndex : 'updateTime'
			}]);
	this.del = function(){
		var records = t.grid.getSelectionModel().getSelections();
		alert(records.length);
		if(records.length>1){
			msgShow("只能选择一条记录进行更新!");
			return false;
		}else if (records.length = 1){
			//var id =  records[0].get('id');
			var v = records[0].get('action');
			alert(v);
			ExtMsg.confirm('确定要更新所选项吗？', function(btn){
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : v,
								success:function(response,opts)
								{	
									var obj = Ext.decode(response.responseText);
									//alert(obj.execute);
									if(obj.execute == true){
										msgShow("数据同步成功!");
									}else{
										msgShow("数据同步失败!");
										
									}
									
								}
							})
						}
					}, 250);
		} else{
			msgShow("至少要选择一条记录进行更新!");
			return false;
		}
	};
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
						text : '更新',
						tooltip : '更新信息',
						iconCls : 'icon-edit',
						ref : '../editObj',
						handler : t.del.createCallback()
					}, {
						xtype : 'tbseparator',
						ref : '../editObj_temp'
					},
					{
						xtype : 'tbbutton',
						iconCls : 'icon-search',
						text : '刷新',
						handler : function() {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}]
				}]
			});
	this.getSearchParams = function() {
		var params;
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
						name : 'id'
					},{
						xtype : 'hidden',
						id : 'action',
						name : 'action'
					}]
		})]
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

		buttonPowerFn("1001_120_101", t.gridTbar.editObj);
		buttonPowerFn("1001_120_101", t.gridTbar.editObj_temp);
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
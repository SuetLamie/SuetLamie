function OffLineConfig() {
	var t = this;

	this.gridPageSize = pageInfoCfg.datagrid.pageSize;
	
	
	this.gridStore = new Ext.data.Store({
				url : 'system/sys/offLineConfigController/getGridStore.action',
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
										name : 'code',
										mapping : 'code'
									}, {
										name : 'path',
										mapping : 'path'
									}, {
										name : 'state',
										mapping : 'state'
									}, {
										name : 'time',
										mapping : 'time'
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
				header : '离线数据包',
				sortable : true,
				width : 140,
				dataIndex : 'name'
			}, {
				header : '代码',
				sortable : true,
				width : 300,
				dataIndex : 'code'
			}, {
				header : '数据文件地址',
				sortable : true,
				width : 300,
				dataIndex : 'path'
			}, {
				header : '状态',
				sortable : true,
				width : 300,
				dataIndex : 'state',
				renderer : function(v) {
					if ('1' == v) {
						return '<font color= "orange" >正在生成</font>';
					}else if('0' ==v){	
						return '<font color="red">数据文件不存在</font>';
					}else{
						return '<font color="green">下载</font>';
					}
				}
			},{
				header : '生成时间',
				sortable : true,
				width : 300,
				dataIndex : 'time'
			}]);
	this.del = function(){
		var records = t.grid.getSelectionModel().getSelections();
		if (records.length > 0){
			var ids = "";
			for (var i = 0; i < records.length; i++){
				if (i != 0) {
					ids += ",";
				}
				ids +=  records[i].get('id') ;
			}
			ExtMsg.confirm('确定要删除所选项吗？', function(btn){
						if (btn == 'yes') {
							var del = PassOwner
									.RequestOfPost(
											'system/sys/offLineConfigController/deleteOffLineConfig.action',
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
	this.make = function(){
		var records = t.grid.getSelectionModel().getSelections();
		if(records.length > 1){
			msgShow("只能选择一条记录进行生成!");
			return false;
		}else if (records.length = 1){
			var id = records[0].get('id') ;
			
			ExtMsg.confirm('确定要生成所选项吗？', function(btn){
						if (btn == 'yes') {
						var waitMsg = "正在生成数据...";
						Ext.MessageBox.wait(waitMsg, ' ');
						 Ext.Ajax.request({
								url : 'system/sys/offLineConfigController/make.action',
								params : {id : id},
								timeout : 0,
								success:function(response,opts)
								{	
									var obj = Ext.decode(response.responseText);
									//alert(obj.execute);
									if(obj.execute == true){
										Ext.MessageBox.hide();
										msgShow("数据生成成功...");
									}else{
										Ext.MessageBox.hide();
										msgShow("数据生成失败...");
										
									}
									
								}
							});
						}
					}, 250);

		}else{
			msgShow("请选择一条记录进行生成!");
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
						text : '增加',
						iconCls : 'icon-add',
						ref : '../addObj',
						tooltip : '增加离线数据配置',
						handler : function() {
							t.win.setTitle("增加信息");
							t.win.show();
							t.form.getForm().reset();
							t.form.getForm().findField('isAdd')
									.setValue("true");
							t.form.getForm().findField('id').setReadOnly(false);
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
						tooltip : '删除信息',
						iconCls : 'icon-delete',
						ref : '../delObj',
						handler : t.del.createCallback()
					}, {
						xtype : 'tbseparator',
						ref : '../delObj_temp'
					}, {
						xtype : 'tbbutton',
						text : '生成',
						tooltip : '生成信息',
						iconCls : 'icon-add',
						ref : '../makeObj',
						handler : t.make.createCallback()
					},{
						xtype : 'tbseparator',
						ref : '../makeObj_temp'
					}, {
						xtype : 'toolbar',
						items : [ {
									xtype : 'tbbutton',
									iconCls : 'icon-search',
									text : '刷新',
									handler : function() {
										gridLoadStart(t.grid, 0, t.gridPageSize);
									}
								}]
					}]
				}]
			});
	
	this.getSearchParams = function() {
		var params ;
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
						xtype : 'hidden',
						name : 'id'
					},{
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>离线数据包</font>',
									name : 'name',
									allowBlank : false,
									blankText : '离线数据包不能为空!',
									maxLength : 50,
									maxLengthText : '离线数据包不能超过50个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>代码</font>',
									name : 'code',
									allowBlank : false,
									blankText : '代码不能为空!',
									maxLength : 200,
									maxLengthText : '代码不能超过200个字符!',
									listeners : {
										scope : this
									}
								}]
					}, {
						layout : 'form',
						items : [{
									xtype : 'textfield',
									fieldLabel : '<font color=#467CD5>数据文件地址</font>',
									name : 'path',
									allowBlank : false,
									blankText : '数据文件地址不能为空!',
									maxLength : 100,
									maxLengthText : '数据文件地址不能超过100个字符!',
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
						url : 'system/sys/offLineConfigController/saveOrUpdate.action',
						waitTitle : pageInfoCfg.form.submit.waitTitle,
						waitMsg : pageInfoCfg.form.submit.waitMsg,
						method : 'POST',
						success : function(form, action){
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
						failure : function(form, action){
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
		buttonPowerFn("1001_119_101", t.gridTbar.addObj);
		buttonPowerFn("1001_119_101", t.gridTbar.addObj_temp);
		buttonPowerFn("1001_119_102", t.gridTbar.editObj);
		buttonPowerFn("1001_119_102", t.gridTbar.editObj_temp);
		buttonPowerFn("1001_119_103", t.gridTbar.delObj);
		buttonPowerFn("1001_119_103", t.gridTbar.delObj_temp);
		buttonPowerFn("1001_119_104", t.gridTbar.makeObj);
		buttonPowerFn("1001_119_104", t.gridTbar.makeObj_temp);
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
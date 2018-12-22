/*
 * If all uploads succeed: {"success":true} If an upload fails:
 * {"success":false,"error":"Reason for error!"}
 */
var keel = {};
keel.pGrid = null;
keel.pGridSize = 0;
keel.ZlUploadPanel = function(cfg) {
	var v = this;
	this.width = 510;
	this.height = 200;
	Ext.apply(this, cfg);
	this.gp = new Ext.grid.EditorGridPanel({
				border : false,
				store : new Ext.data.Store({
							fields : ['id', 'name', 'type', 'size', 'idx', 'state', 'percent']
						}),
				columns : [new Ext.grid.RowNumberer(), {
							header : '文件名',
							width : 100,
							sortable : true,
							dataIndex : 'name',
							menuDisabled : true
						}, {
							header : '类型',
							width : 70,
							sortable : true,
							dataIndex : 'type',
							menuDisabled : true
						}, {
							header : '大小',
							width : 100,
							sortable : true,
							dataIndex : 'size',
							menuDisabled : true,
							renderer : this.formatFileSize
						}, {
							header : '集数',
							width : 100,
							sortable : true,
							dataIndex : 'idx',
							menuDisabled : true,
							editor : new Ext.form.NumberField({
										allowBlank : false,
										allowNegative : false,
										minValue : 1,
										maxValue : 100000
									})
							// renderer : this.formatFileSize
					}	, {
							header : '进度',
							width : 150,
							sortable : true,
							dataIndex : 'percent',
							menuDisabled : true,
							renderer : this.formatProgressBar,
							scope : this
						}, {
							header : '状态',
							width : 70,
							sortable : true,
							dataIndex : 'state',
							menuDisabled : true,
							renderer : this.formatFileState,
							scope : this
						}, {
							header : '&nbsp;',
							width : 40,
							dataIndex : 'id',
							hidden : true,
							renderer : function(v) {
								return ""
							}
						}]
			});
	this.setting = {
		//upload_url : (this.uploadUrl + "&upPath=" + v.getNodeAllText.createCallback()),
		flash_url : this.flashUrl,
		file_size_limit : this.fileSize || (1024 * 50),// 上传文件体积上限，单位MB
		file_post_name : this.filePostName,
		file_types : this.fileTypes || "*.*", // 允许上传的文件类型
		file_types_description : "All Files", // 文件类型描述
		file_upload_limit : "0", // 限定用户一次性最多上传多少个文件，在上传过程中，该数字会累加，如果设置为“0”，则表示没有限制
		// file_queue_limit : "10",//上传队列数量限制，该项通常不需设置，会根据file_upload_limit自动赋值
		post_params : {},
		use_query_string : false,
		debug : false,
		button_cursor : SWFUpload.CURSOR.HAND,
		button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
		custom_settings : {// 自定义参数
			scope_handler : this
		},
		file_queued_handler : this.onFileQueued,
		swfupload_loaded_handler : function() {
		},// 当Flash控件成功加载后触发的事件处理函数
		file_dialog_start_handler : function() {
		},// 当文件选取对话框弹出前出发的事件处理函数
		file_dialog_complete_handler : this.onDiaogComplete,// 当文件选取对话框关闭后触发的事件处理
		upload_start_handler : this.onUploadStart,// 开始上传文件前触发的事件处理函数
		upload_success_handler : this.onUploadSuccess,// 文件上传成功后触发的事件处理函数
		swfupload_loaded_handler : function() {
		},// 当Flash控件成功加载后触发的事件处理函数
		upload_progress_handler : this.uploadProgress,
		upload_complete_handler : this.onUploadComplete,
		upload_error_handler : this.onUploadError,
		file_queue_error_handler : this.onFileError
	};
	keel.ZlUploadPanel.superclass.constructor.call(this, {
				tbar : [{
							text : '添加文件',
							iconCls : 'icon-add',
							ref : '../addBtn'
						}, '-', {
							text : '上传',
							ref : '../uploadBtn',
							iconCls : 'icon-submit',
							handler : this.startUpload,
							scope : this
						}, '-', {
							text : '停止上传',
							ref : '../stopBtn',
							iconCls : 'icon-stop',
							handler : this.stopUpload,
							scope : this,
							disabled : true
						}, '-', {
							text : '删除所有',
							ref : '../deleteBtn',
							iconCls : 'icon-delete',
							handler : this.deleteAll,
							scope : this
						}],
				layout : 'fit',
				items : [this.gp],
				listeners : {
					'afterrender' : function() {
						var em = this.getTopToolbar().get(0).el.child('em');
						var placeHolderId = Ext.id();
						em.setStyle({
									position : 'relative',
									display : 'block'
								});
						em.createChild({
									tag : 'div',
									id : placeHolderId
								});
						this.swfupload = new SWFUpload(Ext.apply(this.setting, {
									button_width : em.getWidth(),
									button_height : em.getHeight(),
									button_placeholder_id : placeHolderId
								}));
						this.swfupload.uploadStopped = false;
						Ext.get(this.swfupload.movieName).setStyle({
									position : 'absolute',
									top : 0,
									left : -2
								});
					},
					scope : this,
					delay : 100
				}
			});
}
Ext.extend(keel.ZlUploadPanel, Ext.Panel, {
	toggleBtn : function(bl) {
		this.addBtn.setDisabled(bl);
		this.uploadBtn.setDisabled(bl);
		this.deleteBtn.setDisabled(bl);
		this.stopBtn.setDisabled(!bl);
		this.gp.getColumnModel().setHidden(6, bl);
	},
	onUploadStart : function(file) {
		var post_params = this.settings.post_params;
		var me = this.customSettings.scope_handler;
		var ds = me.gp.store;
		var idx = 0;
		for (var i = 0; i < ds.getCount(); i++) {
			var rec = ds.getAt(i);
			if (rec.get('id') == file.id) {
				idx = rec.get('idx');
				break;
			}
		}
		Ext.apply(post_params, {// 处理中文参数问题
			'fileName' : encodeURIComponent(file.name),
			'idx' : idx
		});
		this.setPostParams(post_params);
	},
	startUpload : function() {
		if (this.swfupload) {
			if (this.swfupload.getStats().files_queued > 0) {
				this.swfupload.uploadStopped = false;
				this.toggleBtn(true);
				this.swfupload.startUpload();
			}
		}
	},
	formatFileSize : function(_v, celmeta, record) {
		return Ext.util.Format.fileSize(_v);
	},
	formatFileState : function(n) {// 文件状态
		switch (n) {
			case -1 :
				return '未上传';
				break;
			case -2 :
				return '正在上传';
				break;
			case -3 :
				return '<div style="color:red;">上传失败</div>';
				break;
			case -4 :
				return '上传成功';
				break;
			case -5 :
				return '取消上传';
				break;
			default :
				return n;
		}
	},
	formatProgressBar : function(v) {
		var progressBarTmp = this.getTplStr(v);
		return progressBarTmp;
	},
	getTplStr : function(v) {
		var bgColor = "orange";
		var borderColor = "#008000";
		return String
				.format(
						'<div>'
								+ '<div style="border:1px solid {0};height:10px;width:{1}px;margin:4px 0px 1px 0px;float:left;">'
								+ '<div style="float:left;background:{2};width:{3}%;height:10px;"><div></div></div>'
								+ '</div>'
								+ '<div style="text-align:center;float:right;width:40px;margin:3px 0px 1px 0px;height:10px;font-size:12px;">{3}%</div>'
								+ '</div>', borderColor, (90), bgColor, v);
	},
	onUploadComplete : function(file) {
		var me = this.customSettings.scope_handler;
		if (file.filestatus == -4) {
			var ds = me.gp.store;
			for (var i = 0; i < ds.getCount(); i++) {
				var record = ds.getAt(i);
				if (record.get('id') == file.id) {
					record.set('percent', 100);
					if (record.get('state') != -3) {
						record.set('state', file.filestatus);
					}
					record.commit();
				}
			}
		}

		if (this.getStats().files_queued > 0 && this.uploadStopped == false) {
			this.startUpload();
		} else {
			me.toggleBtn(false);
			me.linkBtnEvent();
		}
	},
	onFileQueued : function(file) {
		var me = this.customSettings.scope_handler;
		var rec = new Ext.data.Record({
					id : file.id,
					name : file.name,
					size : file.size,
					type : file.type,
					state : file.filestatus,
					idx : me.gp.getStore().getCount() + 1,
					percent : 0
				})
		me.gp.getStore().add(rec);
	},
	onUploadSuccess : function(file, serverData) {
		var me = this.customSettings.scope_handler;
		var ds = me.gp.store;
		if (Ext.util.JSON.decode(serverData).success) {
			for (var i = 0; i < ds.getCount(); i++) {
				var rec = ds.getAt(i);
				if (rec.get('id') == file.id) {
					rec.set('state', file.filestatus);
					rec.commit();
				}
			}
		} else {
			for (var i = 0; i < ds.getCount(); i++) {
				var rec = ds.getAt(i);
				if (rec.get('id') == file.id) {
					rec.set('percent', 0);
					rec.set('state', -3);
					rec.commit();
				}
			}
		}
		me.linkBtnEvent();
	},
	uploadProgress : function(file, bytesComplete, totalBytes) {// 处理进度条

		var me = this.customSettings.scope_handler;
		var percent = Math.ceil((bytesComplete / totalBytes) * 100);
		percent = percent == 100 ? 99 : percent;
		var ds = me.gp.store;
		for (var i = 0; i < ds.getCount(); i++) {
			var record = ds.getAt(i);
			if (record.get('id') == file.id) {
				record.set('percent', percent);
				record.set('state', file.filestatus);
				record.commit();
			}
		}
	},
	onUploadError : function(file, errorCode, message) {
		var me = this.customSettings.scope_handler;
		me.linkBtnEvent();
		var ds = me.gp.store;
		for (var i = 0; i < ds.getCount(); i++) {
			var rec = ds.getAt(i);
			if (rec.get('id') == file.id) {
				rec.set('percent', 0);
				rec.set('state', file.filestatus);
				rec.commit();
			}
		}
	},
	onFileError : function(file, n) {
		switch (n) {
			case -100 :
				tip('待上传文件列表数量超限，不能选择！');
				break;
			case -110 :
				tip('文件太大，不能选择！');
				break;
			case -120 :
				tip('该文件大小为0，不能选择！');
				break;
			case -130 :
				tip('该文件类型不可以上传！');
				break;
		}
		function tip(msg) {
			Ext.Msg.show({
						title : '提示',
						msg : msg,
						width : 280,
						icon : Ext.Msg.WARNING,
						buttons : Ext.Msg.OK
					});
		}
	},
	onDiaogComplete : function() {
		var me = this.customSettings.scope_handler;
		me.linkBtnEvent();
	},
	stopUpload : function() {
		if (this.swfupload) {
			this.swfupload.uploadStopped = true;
			this.swfupload.stopUpload();
		}
	},
	deleteAll : function() {
		var ds = this.gp.store;
		for (var i = 0; i < ds.getCount(); i++) {
			var record = ds.getAt(i);
			var file_id = record.get('id');
			this.swfupload.cancelUpload(file_id, false);
		}
		ds.removeAll();
		this.swfupload.uploadStopped = false;
	},
	formatDelBtn : function(v, m, r, idx) {
		// return "<a href='javascript:"+this.d+"' style='color:blue'
		// class='link-btn' ext:qtip='移除该文件'>移除</a>";
	},
	linkBtnEvent : function() {
		Ext.select('a.link-btn', false, this.gp.el.dom).on('click', function(o, e) {
					var ds = this.gp.store;
					for (var i = 0; i < ds.getCount(); i++) {
						var rec = ds.getAt(i);
						if (rec.get('id') == e.id) {
							ds.remove(rec);
						}
					}
					this.swfupload.cancelUpload(e.id, false);
				}, this);
	}
});
Ext.reg('zlUploadPanel', keel.ZlUploadPanel);
function Material() {
	var t = this;
	this.modeName = "视频批量上传";
	this.gridPageSize = 20;
	t.winWidth = 480;
	this.gridStore = new Ext.data.Store({
				url : 'admincp/course.do?method=getVideoNoCourseGridStore',
				reader : new Ext.data.JsonReader({
							root : 'list',
							totalProperty : 'totalProperty',
							fields : ['id', 'name', 'kcbh', 'idx', 'cflj', 'cj_time', 'gx_time', 'size', 'spgs', 'cj_time_tx', 'gx_time_tx']

						})
			});
	this.colModel = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer({
						header : '序号',
						width : 40
					}), new Ext.grid.CheckboxSelectionModel(), {
				header : "视频名称",
				sortable : true,
				width : 140,
				dataIndex : 'name'
			}, {
				header : "视频大小",
				sortable : true,
				dataIndex : 'size',
				width : 120
			}, {
				header : "集数",
				sortable : true,
				dataIndex : 'idx',
				width : 120
			}, {
				header : "视频格式",
				sortable : true,
				dataIndex : 'spgs',
				width : 120
			}, {
				header : "预览",
				sortable : true,
				dataIndex : 'cflj',
				width : 120,
				renderer : function(v, m, r) {
					return '<a  href="javascript:function(){}" onclick=\"VideoWatch(\'' + v + '\',\'' + r.get('name') + '\')\">预览</a>';
				}
			}, {
				header : "创建时间",
				sortable : true,
				width : 200,
				dataIndex : 'cj_time_tx'
			}, {
				header : "更新时间",
				sortable : true,
				width : 200,
				dataIndex : 'gx_time_tx'
			}]);
	this.delObj = function() {
		var records = t.grid.getSelectionModel().getSelections();
		if (records.length > 0) {
			var ids = "";
			for (var i = 0; i < records.length; i++) {
				ids += records[i].id + ",";
			}
			ids = ids.substring(0, ids.length - 1);
			ExtMsg.confirm('确定要删除所选项吗？' + t.modeName + '吗？', function(btn) {
						if (btn == 'yes') {
							var del = PassOwner.RequestOfPost('admincp/course.do?method=deleteVideoInfo', {
										'ids' : ids
									}, false);
							msgShow(del.msg);
							if (del.execute) {
								gridLoadDel(t.grid, t.gridPageSize);
							}
						}
					}, 250);
		} else {
			msgShow("请选择你要删除的记录!");
			return false;
		}
	}
	this.search_name = new Ext.form.TextField({
				width : 140,
				listeners : {
					specialkey : function(field, e) {
						if (e.getKey() == Ext.EventObject.ENTER) {
							gridLoadStart(t.grid, 0, t.gridPageSize);
						}
					}
				}
			});
	this.gridTbar = new Ext.Panel({
				// border : true,
				border : false,
				// frame : true,
				// bodyStyle : 'padding:5px 5px 0',
				layout : 'form',
				items : [{
							xtype : 'toolbar',
							// border : false,
							frame : true,
							margins : '0 0 0 0',
							// frame : true,
							items : [{
										xtype : 'tbbutton',
										text : '批量上传',
										iconCls : 'icon-add',
										ref : '../addObj',
										tooltip : '批量上传',
										handler : function() {
											alert();
											t.uploadWin.show();
										}
									}, {
										xtype : 'tbseparator',
										ref : '../editObj_temp'
									}, {
										xtype : 'tbbutton',
										text : '删除',
										tooltip : '删除' + t.modeName,
										iconCls : 'icon-delete',
										ref : '../delObj',
										handler : t.delObj.createCallback()
									}]
						}]
			});
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
	this.gridStore.on('beforeload', function(s) {
				s.baseParams = {
					name : t.search_name.getValue()
				};
			}, this);
	this.win = new Ext.Window({
				closeAction : 'hide',
				border : true,
				title:'资料管理',
				plain : true,
				constrain : true,
				resizable : false,
				width : 750,
				height : 420,
				modal : true,
				layout : 'anchor',
				items : t.grid
			});
	this.show = function(cfg) {
		t.win.show();
	}
	this.uploadPanel = new keel.ZlUploadPanel({
		// xtype : 'uploadPanel',
		border : false,
		fileSize : 1.5 * 1024 * 1024,// 1.5G 限制文件大小
		uploadUrl : basePath + '/admincp/course.do?method=saveVideoFile',
		flashUrl : basePath + '/plug/uploader/swfupload.swf',
		filePostName : 'file', // 后台接收参数
		fileTypes : '*.mp4;*.flv',// 可上传文件类型
		postParams : {}
			// 上传文件存放目录
		});
	this.uploadWin = new Ext.Window({
				closeAction : 'hide',
				width : 650,
				title : '视频批量上传',
				height : 300,
				layout : 'fit',
				modal : true,
				items : t.uploadPanel,
				listeners : {
					'beforehide' : function(p) {
						//gridLoad(t.grid, t.gridPageSize);
						//t.uploadPanel.deleteAll();
					},
					scope : this
				}
			});

	// 初始化数据方法
	this.infoData = function() {
		//gridLoadStart(t.grid, 0, t.gridPageSize);
	}
	t.infoData();
}

function SysAppVersion(config) {
	var t = this;
	// 得到查询的参数
//	this.tree = "";
	this.second=null;
	this.toolbarId = "toolbar";
	this.tableId = "tb_version";
	this.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			start : params.offset, // 页码 
			v_name : $("#search_v_name").val(),
			bz : $("#search_bz").val()
		};
		return temp;
	};
	
   
	// 初始化Table
	this.tableBind = function() {
		$('#' + t.tableId).bootstrapTable({
					url : 'system/sys/AppVersionController/getObjGridStore.action', // 请求后台的URL（*）
					method : 'post', // 请求方式（*）
					toolbar : '#' + t.toolbarId, // 工具按钮用哪个容器
					striped : true, // 是否显示行间隔色
					cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					pagination : true, // 是否显示分页（*）
					// sortable : false, // 是否启用排序
					sortOrder : "asc", // 排序方式
					queryParams : t.queryParams,// 传递参数（*）
					sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
					pageNumber : 1, // 初始化加载第一页，默认第一页
					pageSize : 10, // 每页的记录行数（*）
					pageList : [10, 25, 50, 100], // 可供选择的每页的行数（*）
					search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
					strictSearch : true,
					showColumns : true, // 是否显示所有的列
					showRefresh : true, // 是否显示刷新按钮
					minimumCountColumns : 2, // 最少允许的列数
					totalField : 'totalProperty',
					dataField : 'list',
					clickToSelect : true, // 是否启用点击选中行
					height : $(document).height() - $("#search_form").height() - (2 + 5), // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
					uniqueId : "id", // 每一行的唯一标识，一般为主键列
					showToggle : true, // 是否显示详细视图和列表视图的切换按钮
					cardView : false, // 是否显示详细视图
					detailView : false, // 是否显示父子表
					columns : [{
						checkbox : true
					} , {
						field : 'v_name',
						title : '版本名称'
					}, {
						field : 'v_code',
						title : '版本编号'
					}, {
						field : 'is_update',
						title : '是否强制更新',
						formatter:function(value,row,index){
							if("0"==value){
								return "非强制更新";
							}else if ("1"==value){
								return "强制更新";
							}
							return null;
							
						}
					},{
						field : 'path',
						title : '文件路径'
						
					}, {
						field : 'bz',
						singleLine : true,
						title : '版本备注'
					}, {
						field : 'create_time',
						title : '创建时间'
					}  ]
		});
	};
	this.btnAddBind = function(id) {
		if (SysPower.exist(id)) {
			$("#" + t.toolbarId)
					.append(' <button id="'
							+ id
							+ '" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>增加</button>');
			$("#" + id).click(function() {
				top.layer.open({
							type : 2,
							shade : 0.5,
							title : '新增版本',
							area : [ pageInfoCfg.window.singleColumnWidth + 'px', '420px'],
							maxmin : false,
							content : 'system/sys/requestPageController/queryMain.action?pagePath=system/sys/appVersion/appVersionAdd.jsp&modeId=101_110_101',
							zIndex : layer.zIndex, // 重点1
							end : function() { 
								$("#" + t.tableId).bootstrapTable('refresh');
							}
						});

			});
		}
	}
	this.btnEditBind = function(id) {
		if (SysPower.exist(id)) {
			$("#" + t.toolbarId)
					.append(' <button id="'
							+ id
							+ '" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button>');
			$("#" + id).click(function() {
				var arrselections = $("#" + t.tableId).bootstrapTable('getSelections');
				if (arrselections.length > 1) {
					top.toastr.warning('只能选择一行进行编辑');
					return;
				}else if (arrselections.length <= 0) {
					top.toastr.warning('请选择有效数据');
					return;
				}
				top.layer.open({
							type : 2,
							shade : 0.5,
							title : '修改版本 ',
							area : [ pageInfoCfg.window.singleColumnWidth + 'px', '420px'],
							maxmin : false,
							content : 'system/sys/requestPageController/queryMain.action?pagePath=system/sys/appVersion/appVersionEdit.jsp&modeId=101_110_102',
							zIndex : layer.zIndex, // 重点1
							success : function(layero, index) {
								var body = top.layer.getChildFrame('body', index); 	
								// 赋值
								setFormValuesByMap($("#appVersionForm", body)[0], arrselections[0]);
							},
							end : function() {
								$("#" + t.tableId).bootstrapTable('refresh');
							}
						});

			});
		}
	}
	
	this.btnDeleteBind = function(id) {
		if (SysPower.exist(id)) {
			$("#" + t.toolbarId)
					.append(' <button id="'
							+ id
							+ '" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button>');

			$("#" + id).click(function() {
						var arrselections = $("#" + t.tableId).bootstrapTable('getSelections');
						if (arrselections.length == 0) {
							top.toastr.warning('请选择有效数据');
							return;
						}
						top.layer.confirm('确认要删除选择的数据吗？', {
									title : '温馨提示',
									btn : ['确认', '取消']
								}, function(index) {
									// 确认
									var ids = "";
									for (var i = 0; i < arrselections.length; i++) {
										if (i != 0) {
											ids += ",";
										}
										ids += "'" + arrselections[i]['id'] + "'";
									}
									$.post("system/sys/AppVersionController/delete.action", {
											ids : ids
											}, function(data) {
												if (data.execute) {
													top.toastr.success(data.msg);
												} else {
													top.toastr.error(data.msg);
												}
												bootstrapTableDeleteRefresh($("#" + t.tableId));
												top.layer.close(index);
											}, "json");

								}, function() {
									// 取消
								});
					});
		}
	}
	


	
	// button事件绑定
	this.buttonBind = function() {
		// 增加
		t.btnAddBind("101_110_101");
		// 修改
		t.btnEditBind("101_110_102");
		//删除
		t.btnDeleteBind("101_110_103");
		// 查询
		$("#btn_query").click(function() {
			$("#" + t.tableId).bootstrapTable('refresh',{
				pageNumber : 1
			});
		});

	}
	this.init = function() {
		t.tableBind();
		t.buttonBind();
	}
}

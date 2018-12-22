function SysInterface(config) {
	var t = this;
	// 得到查询的参数
	this.toolbarId = "toolbar";
	this.tableId = "tb_sysInterface";
	this.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			start : params.offset, // 页码
			sort : params.sort, // 排序列名
			sortOrder : params.order,// 排位命令（desc，asc）
			name : $("#search_name").val(),
			path : $("#search_path").val(),
			type : $("#search_type").val()
		};
		return temp;
	};
	// 初始化Table
	this.tableBind = function() {
		$('#' + t.tableId).bootstrapTable({
					url : 'system/sys/sysInterfaceController/getSysInterfaceGridStore.action', // 请求后台的URL（*）
					method : 'post', // 请求方式（*）
					toolbar : '#' + t.toolbarId, // 工具按钮用哪个容器
					striped : true, // 是否显示行间隔色
					cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
					pagination : true, // 是否显示分页（*）
				//	sortable : false, // 是否启用排序
					sortOrder : "asc", // 排序方式
					queryParams : t.queryParams,// 传递参数（*）
					sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
					pageNumber : 1, // 初始化加载第一页，默认第一页
					pageSize : 10, // 每页的记录行数（*）
					pageList : [10, 20, 50, 100], // 可供选择的每页的行数（*）
					search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
					strictSearch : true,
					showColumns : true, // 是否显示所有的列
					showRefresh : true, // 是否显示刷新按钮
					minimumCountColumns : 2, // 最少允许的列数
					totalField : 'totalProperty',
					dataField : 'list',
					clickToSelect : true, // 是否启用点击选中行
					height : $(document).height() - $("#search_form").height() - (2 + 5), // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
					uniqueId : "ID", // 每一行的唯一标识，一般为主键列
					showToggle : true, // 是否显示详细视图和列表视图的切换按钮
					cardView : false, // 是否显示详细视图
					detailView : false, // 是否显示父子表
					columns : [{
								checkbox : true
							}, {
								field : 'name',
								// sortable : true,
								width : 140,
								singleLine:true,
								title : '接口名称'
							}, {
								field : 'path',
								width : 300,
							//	singleLine:true,
								title : '访问路径'
							}, {
								field : 'type',
								title : '功能类型',
								width : 76,
								formatter : function(value, row, index) {
									if (value == "1") {
										return "web端";
									}
									if (value == "2") {
										return "app端";
									}
									return value;
								}
							}, {
								field : 'is_upload_multipart_request',
								title : '文件上传',
								width : 76,
								formatter : function(value, row, index) {
									if (value == "1") {
										return "是";
									}
									if (value == "0") {
										return "否";
									}
									return value;
								}
							}, {
								field : 'is_log_write',
								title : '记录日志',
								width : 76,
								formatter : function(value, row, index) {
									if (value == "1") {
										return '<font color="green">是</font>';
									}
									if (value == "0") {
										return '<font color="red">否</font>';
									}
									return value;
								}
							}, {
								field : 'module_code',
								width : 100,
								title : '对应功能',
								formatter : function(value, row, index) {
									var comboBoxs = SysInterface.data;
									for (var comboBox in comboBoxs) {
										if(value == comboBoxs[comboBox].id) {
											return comboBoxs[comboBox].text;
										}
									}
									return null;
								}
							}, {
								field : 'is_not_log_param',
								width : 170,
								singleLine:true,
								title : '不写日志'
							}, {
								field : 'description',
								//单行显示，多余的用...代替
								singleLine:true,
								title : '功能描述'
							}]
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
					title : '增加功能',
					area : [pageInfoCfg.window.singleColumnWidth + 'px', '600px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/sys/sysInterface/sysInterfaceSave.jsp&modeId=1001_106_101',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						//初始化下拉列表框
						t.initModuleCodeSelect(SysInterface.data,index);
					},
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
				}
				if (arrselections.length == 0) {
					top.toastr.warning('请选择有效数据');
					return;
				}
				top.layer.open({
					type : 2,
					shade : 0.5,
					title : '修改功能',
					area : [pageInfoCfg.window.singleColumnWidth + 'px', '600px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/sys/sysInterface/sysInterfaceUpdate.jsp&modeId=1001_106_102',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						//初始化字典下拉列表框
						t.initModuleCodeSelect(SysInterface.data,index);
						var body = top.layer.getChildFrame('body', index);
						//赋值
						setFormValuesByMap($("#sysInterfaceForm", body)[0], arrselections[0]);
						// top.layer.setTop(layero); // 重点2
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
									var sysInterfaceIds = "";
									for (var i = 0; i < arrselections.length; i++) {
										if (i != 0) {
											sysInterfaceIds += ",";
										}
										sysInterfaceIds += "'" + arrselections[i]['id'] + "'";
									}
									$.post("system/sys/sysInterfaceController/deleteSysInterface.action", {
											sysInterfaceIds : sysInterfaceIds
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
		t.btnAddBind("1001_107_101");
		// 修改
		t.btnEditBind("1001_107_102");
		//删除
		t.btnDeleteBind("1001_107_103");
		// 查询
		$("#btn_query").click(function() {
			$("#" + t.tableId).bootstrapTable('refresh');
		});

	}
	this.getData = function(dict_parent_Id) {
		$.ajax({
			type:"post",
			url:"system/sys/dictdetail/getDictDetailByDictId.action",
			data:{dictId:dict_parent_Id},
			success:function(data){
				SysInterface.data = data;
			},
			dataType:"json",
			async:false
		})
	}	
	//初始化下拉列表
	this.initModuleCodeSelect=function(data,index) {
		var body = top.layer.getChildFrame('body', index);
		var html = "<option value=''>请选择</option>";
		for(var i = 0; i< data.length; i++) {
			html += "<option value='"+data[i].id+"'>"+data[i].text+"</option>";
		}
		$("[name='module_code']",body).html(html); 
	}
	
	this.init = function() {
		//初始化表格
		t.tableBind();
		//初始化按钮
		t.buttonBind();
		//拉取数据字典数据
		t.getData("1010");
	}
}





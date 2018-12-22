function SysUser(config) {
	var t = this;
	// 得到查询的参数
	this.toolbarId = "toolbar";
	this.tableId = "tb_sysUser";
	this.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			start : params.offset, // 页码
			sort : params.sort, // 排序列名
			sortOrder : params.order,// 排位命令（desc，asc）
			username : $("#search_username").val(),
			account : $("#search_account").val()
		};
		return temp;
	};
	// 初始化Table
	this.tableBind = function() {
		$('#' + t.tableId).bootstrapTable({
					url : 'system/sys/sysUserController/getUserGridStore.action', // 请求后台的URL（*）
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
					pageList : [10, 20, 50, 100], // 可供选择的每页的行数（*）
					search : false, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
					strictSearch : true,
					showColumns : true, // 是否显示所有的列
					showRefresh : true, // 是否显示刷新按钮
					minimumCountColumns : 2, // 最少允许的列数
					totalField : 'totalProperty',
					dataField : 'list',
					clickToSelect : true, // 是否启用点击选中行
					height : $(document).height() - $("#search_form").height() - (2 + pageInfoCfg.body.paddingTopButton), // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
					uniqueId : "ID", // 每一行的唯一标识，一般为主键列
					showToggle : true, // 是否显示详细视图和列表视图的切换按钮
					cardView : false, // 是否显示详细视图
					detailView : false, // 是否显示父子表
					columns : [{
								checkbox : true
							}, {
								field : 'id',
								// sortable : true,
								width : 80,
								singleLine : true,
								title : '序号'
							}, {
								field : 'account',
								width : 120,
								singleLine : true,
								title : '登录帐号'
							}, {
								field : 'username',
								width : 180,
								singleLine : true,
								title : '用户姓名'
							}, {
								field : 'sex',
								title : '性别',
								width : 80,
								formatter : function(value, row, index) {
									if (value == 1) {
										return '<font color="green">男</font>';
									} else if (value == 0) {
										return '<font color="red">女</font>';
									}
									return value;
								}
							}, {
								field : 'age',
								width : 80,
								singleLine : true,
								title : '年龄'
							}, {
								field : 'tel',
								width : 140,
								title : '电话'
							}, {
								field : 'cj_time',
								width : 140,
								title : '创建时间',
								formatter : function(value, row, index) {
									if (value) {
										return new Date(value).dateFormat('yyyy-MM-dd HH:mm:ss');
									} else {
										return "";
									}
								}
							}, {
								field : 'bz',
								singleLine : true,
								title : '备注'
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
					title : '增加用户',
					area : [pageInfoCfg.window.singleColumnWidth + 'px', '520px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/sys/sysUserSaveOrUpdate.jsp&modeId=1001_102_102',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
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
					title : '修改用户',
					area : [pageInfoCfg.window.singleColumnWidth + 'px', '520px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/sys/sysUserSaveOrUpdate.jsp&modeId=1001_102_102',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						var body = top.layer.getChildFrame('body', index);
						// 赋值
						setFormValuesByMap($("#sysUserForm", body)[0], arrselections[0]);
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
									var userIds = "";
									for (var i = 0; i < arrselections.length; i++) {
										if (i != 0) {
											userIds += ",";
										}
										userIds += "'" + arrselections[i]['id'] + "'";
									}
									$.post("system/sys/sysUserController/deleteUser.action", {
												userIds : userIds
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
	// 分配权限
	this.btnRoleToUserBind = function(id) {
		if (SysPower.exist(id)) {
			$("#" + t.toolbarId)
					.append(' <button id="'
							+ id
							+ '" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>用户角色</button>');
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
					title : '用户角色',
					area : ['485px', '450px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/sys/grantRoleToUser.jsp&modeId=1001_102_104',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						var body = top.layer.getChildFrame('body', index);
						// 赋值
						$("#user_id", body).val(arrselections[0].id);
						// setFormValuesByMap($("#roleForm", body)[0],
						// arrselections[0]);
						// top.layer.setTop(layero); // 重点2
					},
					end : function() {
						$("#" + t.tableId).bootstrapTable('refresh');
					}
				});
			});
		}
	}
	// button事件绑定
	this.buttonBind = function() {
		// 增加
		t.btnAddBind("101_101_101");
		// 修改
		t.btnEditBind("101_101_102");
		t.btnDeleteBind("101_101_103");
		t.btnRoleToUserBind("101_101_104");
		// 查询
		$("#btn_query").click(function() {
					$("#" + t.tableId).bootstrapTable('refresh', {
								pageNumber : 1
							});
				});
	}
	this.init = function() {
		t.tableBind();
		t.buttonBind();
	}
}

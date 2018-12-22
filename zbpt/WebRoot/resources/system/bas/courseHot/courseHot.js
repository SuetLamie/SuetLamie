function CourseHot(config) {
	var t = this;

	// 得到查询的参数
	// this.tree = "";
	this.second = null;
	this.toolbarId = "tb_course_hot_toolbar";
	this.tableId = "tb_course_hot";
	this.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			start : params.offset, // 页码
			title : $("#search_title").val()
		};
		return temp;
	};

	// 初始化Table
	this.tableBind = function() {
		$('#' + t.tableId).bootstrapTable({
			url : 'system/bas/coursehot/getObjGridStore.action', // 请求后台的URL（*）
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
			height : $(document).height() - $("#search_form").height()
					- (2 + 5), // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "id", // 每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			columns : [{
						checkbox : true
					}, {
						field : 'title',
						title : '课程标题'
					}, {
						field : 'level',
						width : 100,
						singleLine : true,
						title : '课程级别',
						formatter : function(v, row, index) {
							if (t.levelComBox.dataMap[v]) {
								return t.levelComBox.dataMap[v].text;
							} else {
								return v;
							}
						}
					}, {
						field : 'type',
						width : 100,
						singleLine : true,
						title : '课程类别',
						formatter : function(v, row, index) {
							if (t.typeComBox.dataMap[v]) {
								return t.typeComBox.dataMap[v].text;
							} else {
								return v;
							}
						}
					}, {
						field : 'status',
						title : '状态',
						width : 80,
						formatter : function(value, row, index) {
							if (value == 1) {
								return '<font color="green">启用</font>';
							}
							if (value == 0) {
								return '<font color="red">禁用</font>';
							}
							return value;
						}
//					}, {
//						field : 'path',
//						title : '课程路径'
//					}, {
//						field : 'img',
//						title : '课程封面'
//					}, {
//						field : 'index',
//						title : '排序'
					}, {
						field : 'cjsj',
						title : '创建时间'
					}, {
						field : 'recommend_author',
						title : '推荐人'
					}, {
						field : 'recommend_time',
						title : '推荐时间'
					}]
		});
	};
	this.btnAddBind = function(id) {
		if (SysPower.exist(id)) {
			$("#" + t.toolbarId)
					.append(' <button id="'
							+ id
							+ '" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加推荐</button>');
			$("#" + id).click(function() {
				top.layer.open({
					type : 2,
					shade : 0.5,
					title : '添加推荐',
					area : ['900px','550px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/bas/courseHot/courseHotAdd.jsp&modeId=101_106_101',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						var body = top.layer.getChildFrame('body', index);

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
				var arrselections = $("#" + t.tableId)
						.bootstrapTable('getSelections');
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
							debugger;
							for (var i = 0; i < arrselections.length; i++) {
								if (i != 0) {
									ids += ",";
								}
								ids += "'" + arrselections[i]['tj_id'] + "'";
							}
							$.post("system/bas/coursehot/delete.action", {
										ids : ids
									}, function(data) {
										if (data.execute) {
											top.toastr.success(data.msg);
										} else {
											top.toastr.error(data.msg);
										}
										bootstrapTableDeleteRefresh($("#"
												+ t.tableId));
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
		t.btnAddBind("101_106_101");
		// 删除
		t.btnDeleteBind("101_106_103");
		// 查询
		$("#btn_query").click(function() {
					$("#" + t.tableId).bootstrapTable('refresh', {
								pageNumber : 1
							});
				});

	}

	this.init = function() {
		// 初始化课程子类别
		t.typeComBox = new ComBoxData.courseTypeComBox(null);
		// 初始化课程类别
		t.levelComBox = new ComBoxData.courseLevelComBox();
		t.tableBind();
		t.buttonBind();
	}
}

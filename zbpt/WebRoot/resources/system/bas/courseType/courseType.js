function CourseType(config) {
	var t = this;

	// 得到查询的参数
	// this.tree = "";
	this.second = null;
	this.levelComBox = null;
	this.toolbarId = "tb_course_type_toolbar";
	this.tableId = "tb_course_type";
	this.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			start : params.offset, // 页码
			dict_detail_name : $("#search_title").val(),
			dict_parent_id : $("#search_level").val()
		};
		return temp;
	};
	// 初始化Table
	this.tableBind = function() {
		$('#' + t.tableId).bootstrapTable({
			url : 'system/bas/coursetype/getObjGridStore.action', // 请求后台的URL（*）
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
			uniqueId : "dict_detail_id", // 每一行的唯一标识，一般为主键列
			showToggle : true, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			columns : [{
						checkbox : true
					}, {
						field : 'dict_parent_id',
						width : 150,
						singleLine : true,
						title : '课程类别编码'
					}, {
						field : 'dict_parent_name',
						width : 150,
						title : '课程类别'
					}, {
						field : 'dict_detall_code',
						title : '子类别编号'
					}, {
						field : 'dict_detail_name',
						title : '子类别名称'
					}, {
						field : 'dict_detail_order',
						title : '排序'
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
					title : '新增',
					area : [pageInfoCfg.window.singleColumnWidth + 'px',
							'250px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/bas/courseType/courseTypeAdd.jsp&modeId=101_108_101',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						var body = top.layer.getChildFrame('body', index);
						ComBoxData.autoCreateSelectOptionsPage(body, "dict_parent_id",
								ComBoxData.pleaseSelect.concat(t.levelComBox.data),
								null);

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
				var arrselections = $("#" + t.tableId)
						.bootstrapTable('getSelections');
				if (arrselections.length > 1) {
					top.toastr.warning('只能选择一行进行编辑');
					return;
				} else if (arrselections.length <= 0) {
					top.toastr.warning('请选择有效数据');
					return;
				}
				top.layer.open({
					type : 2,
					shade : 0.5,
					title : '修改 ',
					area : [pageInfoCfg.window.singleColumnWidth + 'px',
							'250px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/bas/courseType/courseTypeEdit.jsp&modeId=101_108_102',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						var body = top.layer.getChildFrame('body', index);

						// 赋值
						setFormValuesByMap($("#courseTypeForm", body)[0],
								arrselections[0]);
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
							for (var i = 0; i < arrselections.length; i++) {
								if (i != 0) {
									ids += ",";
								}
								ids += "'" + arrselections[i]['dict_detail_id'] + "'";
							}
							$.post("system/bas/coursetype/delete.action", {
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
		t.btnAddBind("101_108_101");
		// 修改
		t.btnEditBind("101_108_102");
		// 删除
		t.btnDeleteBind("101_108_103");
		// 查询
		$("#btn_query").click(function() {
					$("#" + t.tableId).bootstrapTable('refresh', {
								pageNumber : 1
							});
				});

	}

	this.init = function() {
		// 初始化课程类别
		t.tableBind();
		t.buttonBind();
		t.levelComBox = new ComBoxData.courseLevelComBox();
		console.log(t.levelComBox);
		
		ComBoxData.autoCreateSelectOptions("search_level", ComBoxData.empty
						.concat(t.levelComBox.data), null);
	}
}

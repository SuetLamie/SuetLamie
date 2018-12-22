function TeacherOpus(config) {
	var t = this;

	// 得到查询的参数
	// this.tree = "";
	this.second = null;
	this.toolbarId = "tb_toolbar";
	this.tableId = "tb_teacherOpus";
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
			url : 'system/bas/teacherOpus/getObjGridStoreByTeacher.action', // 请求后台的URL（*）
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
					}, {
						field : 'title',
						title : '作品标题'
					}, {
						field : 'teachernane',
						title : '教师名称'
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
					}, {
						field : 'img',
						title : '作品封面',
						width : 105,
						formatter : function(value, row, index) {

							return '<img src="' + config + value
									+ '" height="50" width="100" />';
						}
					}, {
						field : 'path',
						title : '作品',
						width : 105,
						formatter : function(value, row, index) {
							var func = 'ckplayer("' + value + '","' + row.name + '");';
							return "<a href='javascript:void(0);' onclick='" + func + "'>查看 </a>";
						}
					}, {
						field : 'index',
						title : '排序'
					}, {
						field : 'synopsis',
						width : 150,
						singleLine : true,
						title : '详情'
					}, {
						field : 'teacherid',
						title : '教师编号'
					}, {
						field : 'createdate',
						title : '创建时间'
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
					area : ['1100px',
							'550px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/bas/teacherOpus/teacheropusAdd.jsp&modeId=101_105_101',
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
	
	// button事件绑定
	this.buttonBind = function() {
		// 增加
		t.btnAddBind("101_105_101");
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

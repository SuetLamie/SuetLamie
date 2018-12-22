function Member(config) {
	var t = this;
	// 得到查询的参数
	this.toolbarId = "toolbar";
	this.tableId = "tb_member";
	this.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			start : params.offset, // 页码
			name : $("#search_name").val()
		};
		return temp;
	};
	// 初始化Table
	this.tableBind = function() {
		$('#' + t.tableId).bootstrapTable({
			url : 'system/sys/memberController/getMemberGridStore.action', // 请求后台的URL（*）
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
						field : 'id',
						width : 10,
						singleLine : true,
						title : '序号'
					},{
						field : 'name',
						width : 40,
						singleLine : true,
						title : '会员姓名'
					}, {
						field : 'level',
						width : 20,
						title : '会员身份',
						formatter : function(value, row, index) {
							if (value == 0) {
								return '<font color="black">普通会员</font>';
							} else if (value == 1) {
								return '<font color="blue">超级会员</font>';
							}else if (value == 2) {
								return '<font color="green">白金会员</font>';
							}else if (value == 3) {
								return '<font color="red">代理商</font>';
							}
							return value;
						}
					}, {
						field : 'cardnumber',
						width : 80,
						singleLine : true,
						title : '证件号码'
					}, {
						field : 'mobilephone',
						width : 80,
						singleLine : true,
						title : '手机号'
					}, {
						field : 'commendno',
						width : 40,
						title : '推荐码'
					},{
						field : 'begintime',
						width : 80,
						title : '会员卡开始时间',
						formatter: function (value, row, index) {
					        return changeDateFormat(value)
					    }
					},{
						field : 'endtime',
						width : 80,
						title : '会员卡到期时间',
						formatter: function (value, row, index) {
					        return changeDateFormat(value)
					    }
					}, {
						field : 'personno',
						width : 40,
						title : '邀请码'
					},{
						field : 'ways',
						width : 40,
						title : '注册方式',
						formatter : function(value, row, index) {
							if (value == 0) {
								return '<font color="black">平台注册</font>';
							} else if (value == 1) {
								return '<font color="black">微信注册</font>';
							}else if (value == 2) {
								return '<font color="black">人工注册</font>';
							}
							return value;
						}
					}]
		});
	};
	
	//转换日期格式(时间戳转换为datetime格式)
	function changeDateFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            
            var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            
            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }
		
	// 充值记录
	this.btnGetMemberRechargeBind = function(id) {
		if (SysPower.exist(id)) {
			$("#" + t.toolbarId)
					.append(' <button id="'
							+ id
							+ '" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>充值记录</button>');
			$("#" + id).click(function() {
				var arrselections = $("#" + t.tableId)
						.bootstrapTable('getSelections');
				if (arrselections.length > 1) {
					top.toastr.warning('只能选择一行进行查看');
					return;
				} else if (arrselections.length <= 0) {
					top.toastr.warning('请选择有效数据');
					return;
				}
				top.layer.open({
					type : 2,
					shade : 0.5,
					title : '充值记录 ',
					area : ['1000px', '600px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/memberrecharge/memberrecharge.jsp&modeId=103_101_102',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						
						var body = top.layer.getChildFrame('body', index);
						// 赋值
						$("#search_memberid", body).val(arrselections[0].id);
					},
					end : function() {
						// $("#" + t.tableId).bootstrapTable('refresh');
					}
				});
			});
		}
	}
	
	// button事件绑定
	this.buttonBind = function() {
		
		// 充值记录
		t.btnGetMemberRechargeBind("103_101_102");
		
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

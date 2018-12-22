function MemberRecharge(config) {
	var t = this;
	// 得到查询的参数
	this.toolbarId = "toolbar";
	this.tableId = "tb_memberrecharge";
	this.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			start : params.offset, // 页码
			type : $("#search_name").val(),
			memberid : $("#search_memberid").val()
		};
		return temp;
	};
	// 初始化Table
	this.tableBind = function() {
		$('#' + t.tableId).bootstrapTable({
			url : 'system/bas/memberRechargeController/getMemberRechargeGridStore.action', // 请求后台的URL（*）
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
			showColumns : false, // 是否显示所有的列
			showRefresh : true, // 是否显示刷新按钮
			minimumCountColumns : 2, // 最少允许的列数
			totalField : 'totalProperty',
			dataField : 'list',
			clickToSelect : true, // 是否启用点击选中行
			height : $(document).height() - $("#search_form").height()
					- (2 + 5), // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "id", // 每一行的唯一标识，一般为主键列
			showToggle : false, // 是否显示详细视图和列表视图的切换按钮
			cardView : false, // 是否显示详细视图
			detailView : false, // 是否显示父子表
			columns : [{
						field : 'id',
						width : 100,
						singleLine : true,
						title : '编号'
					},{
						field : 'memberid',
						width : 10,
						singleLine : true,
						title : '会员编号'
					}, {
						field : 'status',
						width : 20,
						title : '充值状态',
						formatter : function(value, row, index) {
							if (value == 0) {
								return '<font color="black">失败</font>';
							} else if (value == 1) {
								return '<font color="blue">成功</font>';
							}
							return value;
						}
					}, {
						field : 'money',
						width : 40,
						singleLine : true,
						title : '充值金额'
					},{
						field : 'createtime',
						width : 40,
						title : '充值时间',
						formatter: function (value, row, index) {
					        return changeDateFormat(value)
					    }
					},{
						field : 'type',
						width : 40,
						title : '充值类型',
						formatter : function(value, row, index) {
							if (value == 1) {
								return '<font color="red">办卡</font>';
							} else if (value == 2) {
								return '<font color="black">充值</font>';
							} else if (value == 3) {
								return '<font color="blue">划拨</font>';
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

	
	// button事件绑定
	this.buttonBind = function() {
		
		
		// 查询
		$("#btn_query").click(function() {
					$("#" + t.tableId).bootstrapTable('refresh', {
								pageNumber : 1
							});
				});

	}
	this.refreshTableData = function() {
		$("#" + t.tableId).bootstrapTable('refresh');
	}
	this.init = function() {
		t.tableBind();
		t.buttonBind();
	}
}

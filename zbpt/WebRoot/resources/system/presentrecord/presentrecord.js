function PresentRecord(config) {
	var t = this;
	// 得到查询的参数
	this.toolbarId = "toolbar";
	this.tableId = "tb_presentrecord";
	this.queryParams = function(params) {
		var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			limit : params.limit, // 页面大小
			start : params.offset, // 页码
			status : $("#search_status").val()
		};
		return temp;
	};
	// 初始化Table
	this.tableBind = function() {
		$('#' + t.tableId).bootstrapTable({
			url : 'system/bas/presentRecordController/getPresentRecordGridStore.action', // 请求后台的URL（*）
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
					},{
						field : 'memberid',
						width : 10,
						singleLine : true,
						title : '会员编号'
					}, {
						field : 'type',
						width : 40,
						title : '提现类型',
						formatter : function(value, row, index) {
							if (value == 1) {
								return '<font color="black">银行卡</font>';
							} else if (value == 2) {
								return '<font color="blue">支付宝</font>';
							}
							return value;
						}
					}, {
						field : 'money',
						width : 40,
						singleLine : true,
						title : '提现金额'
					},{
						field : 'cashtime',
						width : 40,
						title : '提现时间',
						formatter: function (value, row, index) {
					        return changeDateFormat(value)
					    }
					},{
						field : 'dealtime',
						width : 40,
						title : '受理时间',
						formatter: function (value, row, index) {
					        return changeDateFormat(value)
					    }
					},{
						field : 'status',
						width : 40,
						title : '受理状态',
						formatter : function(value, row, index) {
							if (value == 1) {
								return '<font color="green">待处理</font>';
							} else if (value == 2) {
								return '<font color="black">已处理</font>';
							} else if (value == 3) {
								return '<font color="blue">已驳回</font>';
							}else if (value == 4) {
								return '<font color="red">已拒绝</font>';
							}
							return value;
						}
					},{
						field : 'reason',
						width : 100,
						title : '拒绝理由',
					}]
		});
	};
	this.btnEditBind = function(id) {
		if (SysPower.exist(id)) {
			$("#" + t.toolbarId)
					.append(' <button id="'
							+ id
							+ '" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>受理</button>');
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
				if(arrselections[0].status=='2'){
					top.toastr.warning('提现记录已受理,请选择其他未受理的提现');
					return;
				}
				if(arrselections[0].status=='4'){
					top.toastr.warning('提现记录拒绝,请选择其他未受理的提现');
					return;
				}
				if(arrselections[0].status=='3'){
					top.toastr.warning('提现记录已驳回,请选择其他未受理的提现');
					return;
				}
				top.layer.open({
					type : 2,
					shade : 0.5,
					title : '受理 ',
					area : [pageInfoCfg.window.singleColumnWidth + 'px',
							'420px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/presentrecord/presentrecordEdit.jsp&modeId=103_102_101',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						var body = top.layer.getChildFrame('body', index);
						setFormValuesByMap($("#presentrecordForm", body)[0],
								arrselections[0]);
						if (arrselections[0].type == '1') {
							console.log($("#alipayaccount"));
							$("#presentrecordForm", body).find("#alipayaccount").hide();
							$("#presentrecordForm", body).find("#banknumber").show();
							$("#presentrecordForm", body).find("#username").show();
							$("#presentrecordForm", body).find("#bankaddress").show();
							$("#presentrecordForm", body).find("#bankname").show();
							
						}else{
							console.log($('#presentrecordForm'));
							$("#presentrecordForm", body).find("#alipayaccount").show();
							$("#presentrecordForm", body).find("#banknumber").hide();
							$("#presentrecordForm", body).find("#username").hide();
							$("#presentrecordForm", body).find("#bankaddress").hide();
							$("#presentrecordForm", body).find("#bankname").hide();
						}
					},
					end : function() {
						$("#" + t.tableId).bootstrapTable('refresh');
					}
				});

			});
		}
	}
	this.btnRefuseBind = function(id) {
		if (SysPower.exist(id)) {
			$("#" + t.toolbarId)
					.append(' <button id="'
							+ id
							+ '" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>拒绝</button>');

			$("#" + id).click(function() {
				var arrselections = $("#" + t.tableId)
						.bootstrapTable('getSelections');
				if (arrselections.length == 0) {
					top.toastr.warning('请选择有效数据');
					return;
				}else if (arrselections.length > 1) {
					top.toastr.warning('只能选择一行进行编辑');
					return;
				}
				if(arrselections[0].status=='4'){
					top.toastr.warning('提现记录已拒绝,请选择其他未受理的提现');
					return;
				}
				if(arrselections[0].status=='3'){
					top.toastr.warning('提现记录已驳回,请选择其他未受理的提现');
					return;
				}
				if(arrselections[0].status=='2'){
					top.toastr.warning('提现记录已受理,请选择其他未受理的提现');
					return;
				}
				top.layer.open({
					type : 2,
					shade : 0.5,
					title : '拒绝 ',
					area : [pageInfoCfg.window.singleColumnWidth + 'px',
							'420px'],
					maxmin : false,
					content : 'system/sys/requestPageController/queryMain.action?pagePath=system/presentrecord/presentrecordrefuse.jsp&modeId=103_102_103',
					zIndex : layer.zIndex, // 重点1
					success : function(layero, index) {
						var body = top.layer.getChildFrame('body', index);
						setFormValuesByMap($("#presentrecordForm", body)[0],
								arrselections[0]);
					},
					end : function() {
						$("#" + t.tableId).bootstrapTable('refresh');
					}
				});
			});
		}
	}
	this.btnReturnBind = function(id) {
		if (SysPower.exist(id)) {
			$("#" + t.toolbarId)
					.append(' <button id="'
							+ id
							+ '" type="button" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>驳回</button>');

			$("#" + id).click(function() {
				var arrselections = $("#" + t.tableId)
						.bootstrapTable('getSelections');
				if (arrselections.length == 0) {
					top.toastr.warning('请选择有效数据');
					return;
				}else if (arrselections.length > 1) {
					top.toastr.warning('只能选择一行进行编辑');
					return;
				}
				if(arrselections[0].status=='4'){
					top.toastr.warning('提现记录已拒绝,请选择其他未受理的提现');
					return;
				}
				if(arrselections[0].status=='3'){
					top.toastr.warning('提现记录已驳回,请选择其他未受理的提现');
					return;
				}
				if(arrselections[0].status=='2'){
					top.toastr.warning('提现记录已受理,请选择其他未受理的提现');
					return;
				}
				top.layer.confirm('确认要驳回选择的数据吗？', {
							title : '温馨提示',
							btn : ['确认', '取消']
						}, function(index) {
							// 确认
							$.post("system/bas/presentRecordController/revoke.action", {
									id : arrselections[0].id,
									memberid:arrselections[0].memberid,
									money:arrselections[0].money
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
		// 受理
		t.btnEditBind("103_102_101");
		// 驳回
		t.btnReturnBind("103_102_102");
		// 拒绝
		t.btnRefuseBind("103_102_103");
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

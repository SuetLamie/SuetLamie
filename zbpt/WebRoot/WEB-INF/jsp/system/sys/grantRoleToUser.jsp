<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">
<title>为用户赋予角色</title>
<!--bootstrap 配置-->
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
<!-- bootstrap-table 配置-->
<link rel="stylesheet" type="text/css" href="resources/plugins/bootstrap-table/bootstrap-table.min.css">
<script type="text/javascript" src="resources/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="resources/plugins/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/toastr/toastr.min.css">
<script type="text/javascript" src="resources/plugins/toastr/toastr.min.js"></script>
<!-- 引入tree-view -->
<!-- 业务 配置 -->
<script type="text/javascript" src="resources/system/common/js/communal.js"></script>
<script type="text/javascript" src="resources/system/common/js/pageInfoCfg.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
<!-- bootstrap表单验证 -->
<link rel="stylesheet" href="resources/plugins/bootstrapValidator/css/bootstrapValidator.css" />
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/bootstrapValidator.local.js"></script>

<!-- 下拉列表 -->
<script type="text/javascript" src="resources/plugins/multiselect/multiselect.js"></script>
<script type="text/javascript">
	var user_id = "";
	var oldRoleId = "";
	var addRoleId = "";
	var delRoleId = "";
	/*
	 * 绑定数据
	 */
	function queryUserRoleData(url, multiselectId) {
		var multiselect = $('#' + multiselectId).multiselect({
			keepRenderingSort : true
		});
		$.post(url, function(data) {
			multiselect.empty();
			for (index in data) {
				opt = $('<option />', {
					value : data[index].id,
					text : data[index].text
				});
				if ("keepRenderingSort_to" == multiselectId) {
					if (index != 0) {
						oldRoleId += ",";
					}
					oldRoleId += data[index].id;
				}

				opt.appendTo(multiselect);
			}
			multiselect.multiselect('refresh');
		}, "json");
	};

	function bindNoUserOfRoleData(user_id) {
		var url = "system/sys/sysUserController/getNoUserOfRole.action?user_id="
				+ user_id;
		queryUserRoleData(url, "keepRenderingSort");

	};
	function bindUserOfRoleData(user_id) {
		var url = "system/sys/sysUserController/getUserOfRole.action?user_id="
				+ user_id;
		queryUserRoleData(url, "keepRenderingSort_to");

	};

	function loadData() {
		user_id = "";
		user_id = $("#user_id").val();
		if (user_id) {
			bindNoUserOfRoleData(user_id);
			bindUserOfRoleData(user_id);
		} else {
			setTimeout(function() {
				loadData()
			}, 200);
		}
	};
	$(document).ready(function() {
		loadData();
	});

	/*
	 * 整理提交的数据
	 */
	function setSumbitData() {
		delRoleId = "";
		addRoleId = "";
		var newRoleId = "";
		$("#keepRenderingSort_to > option").each(function(i) {
			if (i != 0) {
				newRoleId += ","
			}
			newRoleId += $(this).val();
		});
		var newArr = newRoleId.split(',');
		var oleArr = oldRoleId.split(',');
		if (newArr.length == 0) {
			delRoleId = oldRoleId;
			return;
		}
		if (oleArr == 0) {
			addRoleId = newRoleId;
			return;
		}
		for ( var i = 0; i < newArr.length; i++) {
			if ((',' + oldRoleId + ',').indexOf(',' + newArr[i] + ',') == -1) {
				if (addRoleId != "") {
					addRoleId += ",";
				}
				addRoleId += newArr[i];
			}
		}
		for ( var i = 0; i < oleArr.length; i++) {
			if ((',' + newRoleId + ',').indexOf(',' + oleArr[i] + ',') == -1) {
				if (delRoleId != "") {
					delRoleId += ",";
				}
				delRoleId += oleArr[i];
			}
		}
	};

	function submitUserRoleForm() {
		setSumbitData();
		$.post("system/sys/sysUserController/changeUserRole.action", {
			"user_id" : user_id,
			"addIds" : addRoleId,
			"delIds" : delRoleId
		}, function(data) {
			if (data.execute) {
				top.toastr.success(data.msg);
				closeParentLayer();
			} else {
				if (data.data) {
					var fieldName = data.data;
					$("#userForm").bootstrapValidator("addField", fieldName, {
						validators : {
							customValid : {
								message : data.msg,
								oldValue : $("#userForm")[0][fieldName].value
							}
						}
					});
					$("#userForm").data("bootstrapValidator").validateField(
							fieldName);
				} else {
					top.toastr.error(data.msg);
				}
			}
		}, "json");

	};
</script>
<style type="text/css">
.div_main {
	
}
</style>
</head>
<body>
	<div class="div_main">
		<input type="hidden" name="user_id" id="user_id">
		<div style="margin-bottom: 5px">
			<div class="col-xs-5">
				<label class="control-label form-lable">未拥有角色</label>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-5">
				<label class="control-label form-lable">已拥有角色</label>
			</div>
		</div>
		<div>
			<div class="col-xs-5">
				<select name="from" id="keepRenderingSort" class="form-control" size="16" multiple="multiple" style=" height: 302px;">
				</select>
			</div>
			<div class="col-xs-2" style="padding-top: 70px;">
				<button type="button" id="keepRenderingSort_rightAll" class="btn btn-block">
					<i class="glyphicon glyphicon-forward"></i>
				</button>
				<button type="button" id="keepRenderingSort_rightSelected" class="btn btn-block">
					<i class="glyphicon glyphicon-chevron-right"></i>
				</button>
				<button type="button" id="keepRenderingSort_leftSelected" class="btn btn-block">
					<i class="glyphicon glyphicon-chevron-left"></i>
				</button>
				<button type="button" id="keepRenderingSort_leftAll" class="btn btn-block">
					<i class="glyphicon glyphicon-backward"></i>
				</button>
			</div>
			<div class="col-xs-5">
				<select name="to" id="keepRenderingSort_to" class="form-control" size="16" multiple="multiple" style=" height: 302px;"></select>
			</div>
		</div>
		<div>
			<div class="col-xs-4"></div>
			<div class="col-xs-2" style="margin-top: 15px;">
				<button type="button" onclick="submitUserRoleForm()" class="btn btn-primary btn-sm">保存</button>
			</div>
			<div class="col-xs-2" style="margin-top: 15px;">
				<button type="button" onclick="closeParentLayer()" class="btn btn-primary btn-sm">取消</button>
			</div>
			<div class="col-xs-4"></div>
		</div>
	</div>
</body>
</html>

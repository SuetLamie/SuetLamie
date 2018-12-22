<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>

<head>
<base href="<%=basePath%>">
<title>系统管理==》修改密码</title>
<!--UI 配置-->
<script type="text/javascript" src="resources/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/toastr/toastr.min.css">
<script type="text/javascript" src="resources/plugins/toastr/toastr.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/bootstrapValidator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/bootstrapValidator.local.js"></script>
<script type="text/javascript" src="resources/plugins/layer/layer.js"></script>
<script type="text/javascript" src="resources/system/common/js/communal.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
<!-- 业务 配置 -->
<script type="text/javascript">
	//form验证初始化
	function formValidatorInit() {
		$("#pswForm").bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				old_pwd : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '旧密码不能为空'
						},
						stringLength : {
							max : 10,
							message : '旧密码不能大于10个字符'
						}
					}
				},
				new_pwd : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '新密码不能为空'
						},
						stringLength : {
						    max : 10,
							min : 4,
							message : '新密码不能小于4个字符大于10个字符'
						}
					}
				},
				rpt_pwd : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '确认密码不能为空'
						},
						identical : {
							field : 'new_pwd',
							message : '两次输入密码不同'
						}
					}
				}
			}
		});
	}
	//提交数据
	function submitForm() {
		$("#pswForm").bootstrapValidator("validate"); //验证
		if ($("#pswForm").data("bootstrapValidator").isValid()) {
			$.post("system/sys/sysUserController/updatePwd.action", $("#pswForm").serialize(), function(data) {
				if (data.execute) {
					top.layer.confirm(data.msg+"点击确认退出重新登录", {
									title : '温馨提示',
									btn : ['确认']
								}, function(index) {
									$('a[href="system/sys/loginController/logout.action"]', top.document).attr("href",
									"system/sys/loginController/logout.action?clearCookie=true");
									$('a[href^="system/sys/loginController/logout.action"]', top.document)[0].click();//退出登录
									closeParentLayer();
								});
				} else {
					if (data.data) {
						var fieldName = data.data;
						$("#pswForm").bootstrapValidator("addField", fieldName, {
							validators : {
								customValid : {
									message : data.msg,
									oldValue : $("#pswForm")[0][fieldName].value
								}
							}
						});
						$('#pswForm').data("bootstrapValidator").validateField(fieldName);
					} else {
						top.toastr.error(data.msg);
					}
				}
			}, "json");
		}
	}
	// 回车登录事件
	document.onkeydown = function(event) {
		var e = event || window.event;
		if (e && e.keyCode == 13) {
			$("#xiugai").click();
		}
	};
	$(document).ready(function() {
		formValidatorInit();
	
	});
</script>
</head>

<body>
	<div class="bootstrap-form">
		<form id="pswForm" name="pswForm" class="form-horizontal">
			<input type="hidden" name="r_id">
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">旧密码：</label>
				<div class="col-xs-9" id="ss">
					<input type="password" name="old_pwd" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">新密码：</label>
				<div class="col-xs-9">
					<input type="password" class="form-control" name="new_pwd">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">确认密码：</label>
				<div class="col-xs-9">
					<input type="password" class="form-control" name="rpt_pwd">
				</div>
			</div>
			<div class="form-group form-group-button">
				<span class="col-xs-4"></span>
				<div class="col-xs-2">
					<button type="button" id="xiugai" onclick="submitForm()" class="btn btn-primary btn-sm">修改</button>
				</div>
				<div class="col-xs-2">
					<button type="button" onclick="closeParentLayer()" class="btn btn-primary btn-sm">取消</button>
				</div>
				<span class="col-xs-4"></span>
			</div>
		</form>
	</div>
</body>

</html>
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
<title>系统管理==》编辑用户</title>
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
<script type="text/javascript" src="resources/system/common/js/json2.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
<!-- bootstrap-spinner -->
<link rel="stylesheet" type="text/css" href="resources/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="resources/plugins/bootstrap-spinner/bootstrap-spinner.css">
<script type="text/javascript" src="resources/plugins/bootstrap-spinner/jquery.spinner.min.js"></script>

<!-- 业务 配置 -->
<script type="text/javascript">
	//form验证初始化
	function formValidatorInit() {
		$("#sysUserForm").bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				account : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '登录帐号不能为空'
						},
						stringLength : {
							max : 20,
							message : '登录帐号不能大于20个字符'
						}
					}
				},
				username : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '用户姓名不能为空'
						},
						stringLength : {
							max : 20,
							message : '用户姓名不能大于20个字符'
						}
					}
				},
				password : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '密码不能为空'
						},
						stringLength : {
							max : 50,
							message : '密码不能大于50个字符'
						}
					}
				},
				tel : {
					validators : {
						notEmpty : {
							message : '手机号码不能为空'
						},
						stringlength : {
							min : 11,
							max : 11,
							message : '请输入11位手机号码'
						},
						regexp : {
							regexp : /^1[3|5|8]{1}[0-9]{9}$/,
							message : '请输入正确的手机号码'
						}
					}
				},
				bz : {
					group : '.col-xs-9',
					validators : {
						stringLength : {
							max : 200,
							message : '备注不能大于200个字符'
						}
					}
				}
			}
		});
	}
	//提交数据
	function submitForm() {
		$("#sysUserForm").bootstrapValidator("validate"); //验证
		if ($("#sysUserForm").data("bootstrapValidator").isValid()) {
			$
					.post(
							"system/sys/sysUserController/saveOrUpdateUser.action",
							$("#sysUserForm").serialize(),
							function(data) {
								if (data.execute) {
									top.toastr.success(data.msg);
									closeParentLayer();
								} else {
									if (data.data) {
										var fieldName = data.data;
										$("#sysUserForm")
												.bootstrapValidator(
														"addField",
														fieldName,
														{
															validators : {
																customValid : {
																	message : data.msg,
																	oldValue : $("#sysUserForm")[0][fieldName].value
																}
															}
														});
										$('#sysUserForm').data(
												"bootstrapValidator")
												.validateField(fieldName);
									} else {
										top.toastr.success(data.msg);
									}
								}
							}, "json");
		}
	}
	$(document).ready(function() {
		formValidatorInit();
		setTimeout(function() {
			if ($("#_u_id").val()) {
				$("#account").attr("disabled", "disabled");
			}
		}, 200);
	});
</script>
</head>
<body>
	<div class="bootstrap-form">
		<form id="sysUserForm" name="sysUserForm" class="form-horizontal">
			<input type="hidden" name="id" id="_u_id">
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">登录帐号：</label>
				<div class="col-xs-9">
					<input type="text" id="account" name="account" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">用户姓名：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="username">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">用户密码：</label>
				<div class="col-xs-9">
					<input type="password" class="form-control" name="password">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">性别：</label>
				<div class="col-xs-9">
					<select class="form-control" name="sex">
						<option value="1" selected="selected">男</option>
						<option value="0">女</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">年龄：</label>
				<div class="col-xs-9">
					<div class="input-group spinner" data-trigger="spinner">
						<input type="text" name="age" class="form-control" value="18" data-rule="quantity" style="text-align: left !important;">
						<div class="input-group-addon">
							<a href="javascript:;" class="spin-up" data-spin="up"><i class="fa fa-caret-up"></i> </a> <a href="javascript:;" class="spin-down"
								data-spin="down"><i class="fa fa-caret-down"></i> </a>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">电话：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="tel">
				</div>
			</div>
			<div class="form-group form-group-textarea">
				<label class="control-label col-xs-3 form-lable">备 注：</label>
				<div class="col-xs-9">
					<textarea rows="3" cols="30" class="form-control" name="bz" style="height: 70px;"></textarea>
				</div>
			</div>
			<div class="form-group form-group-button">
				<span class="col-xs-4"></span>
				<div class="col-xs-2">
					<button type="button" onclick="submitForm()" class="btn btn-primary btn-sm">保存</button>
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
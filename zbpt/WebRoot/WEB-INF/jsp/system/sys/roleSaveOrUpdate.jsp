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
<title>系统管理==》编辑角色</title>
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
<script type="text/javascript" src="resources/system/common/js/ComBoxData.js"></script>
<script type="text/javascript" src="resources/system/common/js/json2.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
<!-- 业务 配置 -->
<script type="text/javascript">
	//form验证初始化
	function formValidatorInit() {
		$("#roleForm").bootstrapValidator({
			message : 'This value is not valid',
			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'
			},
			fields : {
				r_name : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '角色名称不能为空'
						},
						stringLength : {
							max : 20,
							message : '角色名称不能大于20个字符'
						}
					}
				},
				r_code : {
					group : '.col-xs-9',
					validators : {
						notEmpty : {
							message : '角色编码不能为空'
						},
						stringLength : {
							max : 50,
							message : '角色编码不能大于50个字符'
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
		$("#roleForm").bootstrapValidator("validate"); //验证
		if ($("#roleForm").data("bootstrapValidator").isValid()) {
			$
					.post(
							"system/sys/roleController/saveOrUpdateRole.action",
							$("#roleForm").serialize(),
							function(data) {
								if (data.execute) {
									top.toastr.success(data.msg);
									closeParentLayer();
								} else {
									if (data.data) {
										var fieldName = data.data;
										$("#roleForm")
												.bootstrapValidator(
														"addField",
														fieldName,
														{
															validators : {
																customValid : {
																	message : data.msg,
																	oldValue : $("#roleForm")[0][fieldName].value
																}
															}
														});
										$('#roleForm').data(
												"bootstrapValidator")
												.validateField(fieldName);
									} else {
										top.toastr.success(data.msg);
									}
								}
							}, "json");
		}
	}
	$(document).ready(
			function() {

				formValidatorInit();
				var userTypeComBox = new ComBoxData.userTypeComBox();
				// 动态创建查询信息
				ComBoxData.autoCreateSelectOptions("user_type",
						userTypeComBox.data, 1);
				setTimeout(function() {
					if ($("#_r_id").val()) {
						$("#user_type").attr("disabled", "disabled");
					}
				}, 200);
			});
</script>
</head>

<body>
	<div class="bootstrap-form">
		<form id="roleForm" name="roleForm" class="form-horizontal">
			<input type="hidden" name="r_id" id="_r_id">
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">角色名称：</label>
				<div class="col-xs-9">
					<input type="text" name="r_name" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">角色编码：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="r_code">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">角色类型：</label>
				<div class="col-xs-9">
					<select class="form-control" name="user_type" id="user_type">
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">状 态：</label>
				<div class="col-xs-9">
					<select class="form-control" name="state">
						<option value="1" selected="selected">启用</option>
						<option value="0">禁用</option>
					</select>
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
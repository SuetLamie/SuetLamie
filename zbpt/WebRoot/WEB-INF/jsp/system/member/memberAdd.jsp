<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">
<!--bootstrap 配置-->
<script type="text/javascript" src="resources/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery_form.js"></script>
<script type="text/javascript" src="resources/plugins/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/bootstrap-table/bootstrap-table.min.css">
<script type="text/javascript" src="resources/plugins/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/toastr/toastr.min.css">
<script type="text/javascript" src="resources/plugins/toastr/toastr.min.js"></script>

<link rel="stylesheet" type="text/css" href="resources/plugins/bootstrapValidator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/bootstrapValidator.local.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/language/zh_CN.js"></script>
<!-- 业务 配置 -->
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
<script type="text/javascript" src="resources/system/common/js/communal.js"></script>
<script type="text/javascript" src="resources/system/common/js/ComBoxData.js"></script>
<script type="text/javascript" src="resources/system/common/js/json2.js"></script>
<script type="text/javascript" src="resources/system/common/js/sysPower.js"></script>
<script type="text/javascript" src="resources/system/common/js/pageInfoCfg.js"></script>

<script type="text/javascript" src="resources/system/member/memberAdd.js"></script>

</head>
<body>
	<div class="bootstrap-form">
		<form role="form" class="form-horizontal" id="memberForm" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">会员姓名：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="name">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">证件号码：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="cardnumber">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">头像:</label>
				<div class="col-xs-9">
					<input type="file" id="headImgFile" name="headImgFile">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">身份证正面照:</label>
				<div class="col-xs-9">
					<input type="file" id="pathFile" name="pathFile">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">身份证反面照:</label>
				<div class="col-xs-9">
					<input type="file" id="imgFile" name="imgFile">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">手机号:</label>
				<div class="col-xs-9">
					<input type="number" class="form-control" name="mobilephone">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">邀请码:</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="personno">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">登录密码:</label>
				<div class="col-xs-9">
					<input type="password" class="form-control" name="password">
				</div>
			</div>
			<input type="hidden" value="0" name="level">
			<input type="hidden" value="2" name="ways">
			<input type="hidden" value="1" name="status">
		<!-- 	<div class="form-group">
				<label class="control-label col-xs-3 form-lable">会员级别：</label>
				<div class="col-xs-9">
					<select class="form-control" name="level">
						<option value="0" selected="selected">普通会员</option>
						<option value="1">白金卡</option>
						<option value="2">代理商</option>
						<option value="3">普通会员</option>
					</select>
				</div>
			</div> --> 
			<div class="form-group form-group-button">
				<span class="col-xs-4"></span>
				<div class="col-xs-2">
					<button type="button" id="submitForm" class="btn btn-primary btn-sm">保存</button>
				</div>
				<div class="col-xs-2">
					<button type="button" onclick="member.closeLayer()" class="btn btn-primary btn-sm">取消</button>
				</div>
				<span class="col-xs-4"></span>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		var member;
		$(function() {
			member = new MemberAdd();
			member.init();
		});
	</script>

</body>
</html>

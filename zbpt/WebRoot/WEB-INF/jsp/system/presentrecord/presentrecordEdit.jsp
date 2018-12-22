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

<title>My JSP 'departmentEdit.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!--bootstrap 配置-->
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
<script type="text/javascript" src="resources/system/presentrecord/presentrecordEdit.js"></script>

</head>

<body>
	<div class="bootstrap-form">
		
		<form role="form" class="form-horizontal" id="presentrecordForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id">
			<input type="hidden" name="memberid" id="memberid">
			<input type="hidden" name="status" value="1">
			<input type="hidden" name="type" id="type">
			<div class="form-group" id="banknumber">
				<label class="control-label col-xs-3 form-lable">银行卡号：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="banknumber" readonly >
				</div>
			</div>
			<div class="form-group" id="username">
				<label class="control-label col-xs-3 form-lable">户名：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="username" readonly >
				</div>
			</div>
			<div class="form-group" id="bankaddress">
				<label class="control-label col-xs-3 form-lable">地区支行地址：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="bankaddress" readonly >
				</div>
			</div>
			<div class="form-group" id="bankname">
				<label class="control-label col-xs-3 form-lable">开户银行：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="bankname" readonly >
				</div>
			</div>
			<div class="form-group" id="alipayaccount">
				<label class="control-label col-xs-3 form-lable">支付宝账号：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="alipayaccount" readonly >
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">手机号：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="mobilephone" readonly id="mobilephone">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">提现金额：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="money" readonly id="money">
				</div>
			</div>
			<div class="form-group form-group-button">
				<span class="col-xs-4"></span>
				<div class="col-xs-2">
					<button type="button" id="submitForm" class="btn btn-primary btn-sm">已受理</button>
				</div>
				<div class="col-xs-2">
					<button type="button" onclick="presentrecord.closeLayer()" class="btn btn-primary btn-sm">取消</button>
				</div>
				<span class="col-xs-4"></span>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var presentrecord;
		$(function() {
			presentrecord = new PresentRecordEdit();
			presentrecord.init();
		});
	</script>
</body>
</html>

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

<script type="text/javascript" src="resources/system/bas/appResourcesConfig/appResourcesConfigEdit.js"></script>

</head>

<body>
	<div class="bootstrap-form">
		
		<form role="form" class="form-horizontal" id="appResourcesConfigForm" >
			<input type="hidden" name="id" id="id">
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">名称：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="name">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">优先级：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="level">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">页面标记：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="page">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">状态：</label>
				<div class="col-xs-9">
					<select class="form-control" name="status">
						<option value="1" selected="selected">启用</option>
						<option value="0">禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group form-group-button">
				<span class="col-xs-4"></span>
				<div class="col-xs-2">
					<button type="button" id="submitForm" class="btn btn-primary btn-sm">保存</button>
				</div>
				<div class="col-xs-2">
					<button type="button" onclick="obj.closeLayer()" class="btn btn-primary btn-sm">取消</button>
				</div>
				<span class="col-xs-4"></span>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
		var obj;
		$(function() {
			obj = new AppResourcesConfigAddEdit();
			obj.init();
		});
	</script>
	
</body>
</html>

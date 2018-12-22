<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	// 资源访问前缀
		String imgip = request.getServerName().trim();
		if("127.0.0.1".equals(imgip)){
			imgip = "118.89.240.123";
		}
		// 资源访问前缀
		String imgBasePath = request.getScheme() + "://" + imgip + ":80/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>系统管理==》教师管理</title>
<!--UI 配置-->
<script type="text/javascript" src="resources/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="resources/plugins/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/bootstrap-table/bootstrap-table.min.css">
<script type="text/javascript" src="resources/plugins/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/toastr/toastr.min.css">
<script type="text/javascript" src="resources/plugins/toastr/toastr.min.js"></script>
<!-- 业务 配置 -->
<script type="text/javascript" src="resources/system/common/js/communal.js"></script>
<script type="text/javascript" src="resources/system/common/js/ComBoxData.js"></script>
<script type="text/javascript" src="resources/system/common/js/json2.js"></script>
<script type="text/javascript" src="resources/system/common/js/sysPower.js"></script>
<script type="text/javascript" src="resources/system/common/js/pageInfoCfg.js"></script>
<script type="text/javascript" src="resources/system/sys/teacher/teacher.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
</head>
<body>
	<div class="panel-body">
		<div id="search_form" class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="query-form">
					<label class="control-label col-sm-1">教师姓名</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="search_username">
					</div>
					<div class="col-sm-1" style="text-align:left;">
						<button type="button" id="btn_query" class="btn btn-primary btn-sm">查询</button>
					</div>
				</form>
			</div>
		</div>
		<div id="toolbar"></div>
		<table id="tb_teacher"></table>
	</div>
	<script type="text/javascript">
		$(function() {
			var imgBasePath = "<%=imgBasePath%>";
			var teacherInfo = new TeacherInfo(imgBasePath);
			teacherInfo.init();
		});
	</script>
</body>
</html>
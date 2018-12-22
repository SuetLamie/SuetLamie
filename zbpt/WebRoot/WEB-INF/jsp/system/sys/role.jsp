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
<title>系统管理==》角色管理</title>
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
<script type="text/javascript" src="resources/system/sys/js/role.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
</head>
<body>
	<div class="panel-body">
		<div id="search_form" class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="query-form">
					<label class="control-label col-sm-1">角色名称</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="search_r_name">
					</div>
					<label class="control-label col-sm-1">角色代码</label>
					<div class="col-sm-1">
						<input type="text" class="form-control" id="search_r_code">
					</div>
					<label class="control-label col-sm-1">系统类型</label>
					<div class="col-sm-2">
						<select class="form-control" id="search_sysType">
						</select>
					</div>
					<label class="control-label col-sm-1">角色状态</label>
					<div class="col-sm-2">
						<select class="form-control" id="search_state">
							<option value="" selected="selected">全部</option>
							<option value="1">启用</option>
							<option value="0">禁用</option>
						</select>
					</div>
					<div class="col-sm-1" style="text-align:left;">
						<button type="button" id="btn_query" class="btn btn-primary btn-sm">查询</button>
					</div>
				</form>
			</div>
		</div>
		<div id="toolbar"></div>
		<table id="tb_sysRole"></table>
	</div>
	<script type="text/javascript">
		$(function() {
			var role = new SysRole();
			role.init();
		});
	</script>
</body>
</html>
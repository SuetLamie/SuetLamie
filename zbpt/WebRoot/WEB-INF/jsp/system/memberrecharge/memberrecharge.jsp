<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>系统管理==》充值记录管理</title>
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
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
<script type="text/javascript" src="resources/system/common/js/communal.js"></script>
<script type="text/javascript" src="resources/system/common/js/ComBoxData.js"></script>
<script type="text/javascript" src="resources/system/common/js/json2.js"></script>
<script type="text/javascript" src="resources/system/common/js/sysPower.js"></script>
<script type="text/javascript" src="resources/system/common/js/pageInfoCfg.js"></script>
<script type="text/javascript" src="resources/system/memberrecharge/memberrecharge.js"></script>
</head>
<body>
	<div class="panel-body">
		<div id="search_form" class="panel panel-default">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="query-form">
						<label class="control-label col-sm-1">充值类型:</label>
						
						<input type="hidden" class="form-control" id="search_memberid" name="search_memberid">
						<div class="col-sm-2">
							<select class="form-control" id="search_name"> 
								<option value="" >全部类型</option>
								<option value="1" >办卡</option>
								<option value="2" >充值</option>
							</select>
						</div>
							<!-- 旧标签 -->							
							<!-- <input type="text" class="form-control" id="search_name"> -->

						<div class="col-sm-1" style="text-align:left;">
							<button type="button" id="btn_query" class="btn btn-primary btn-sm">查询</button>
						</div>
				</form>
			</div>
		</div>
		<div id="toolbar"></div>
		<table id="tb_memberrecharge"></table>
	</div>
	<script type="text/javascript">
		$(function() {
			var memberrecharge = new MemberRecharge();
			memberrecharge.init();
			setTimeout(function() {//半秒后加载
				memberrecharge.refreshTableData();
			}, 500);
		});
	</script>
</body>
</html>
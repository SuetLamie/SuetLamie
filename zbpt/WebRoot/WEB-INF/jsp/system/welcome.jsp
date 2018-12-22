<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>

<head>
<base href="<%=basePath%>">
<title>欢迎页</title>
<script type="text/javascript" src="resources/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="resources/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="resources/plugins/highcharts/highcharts-more.js"></script>
<script type="text/javascript" src="resources/plugins/highcharts/modules/exporting.js"></script>
<script type="text/javascript" src="resources/plugins/highcharts/plugins/highcharts-zh_CN.js"></script>
<script type="text/javascript" src="resources/js/system/chart/common/chartUtils.js"></script>

<script type="text/javascript" src="resources/homeFrame/js/welcome.js"></script>
<style type="text/css">
.north {
	height: 150px;
}

.icon_div {
	height: 90px;
	margin-top: 20px;
}

.center {
	
}

.div_center {
	text-align: center;
}

.icon_text {
	font-weight: bold;
	font-size: 15px;
}

.toolButton { //
	background-color: red;
	position: absolute;
	width: 200px;
	height: 30px;
	left: 80px;
	top: 180px;
	position: absolute;
	z-index: 100;
}

.toolButton .btn_base {
	color: #fff;
	background-color: #5cb85c;
	border-color: #4cae4c;
	display: inline-block;
	padding: 3px 6px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-ms-touch-action: manipulation;
	touch-action: manipulation;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
}

.toolButton .active {
	color: #fff;
	background-color: #d9534f;
	border-color: #d43f3a
}
</style>
</head>

<body>
	<div class="north" id="quickEnter"></div>
	<div class="center">
		<div class="toolButton">
			<button id="plain" class="active btn_base">普通</button>
			<button id="inverted" class="btn_base">反转</button>
			<button id="polar" class="btn_base">极地图</button>
		</div>
		<div id="container" style="min-width: 400px;height:100%"></div>
	</div>
</body>

</html>
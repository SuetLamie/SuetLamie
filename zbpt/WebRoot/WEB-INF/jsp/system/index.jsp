<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<title>直播平台后台-->首页</title>
<base href="<%=basePath%>">
<!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/><![endif]-->
<link rel="stylesheet" type="text/css" href="resources/homeFrame/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/homeFrame/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="resources/homeFrame/css/style.min.css">
<style type="text/css">
.left_top {
	text-align: center;
	font-size: 18px;
	font-weight: 600;
	font-family: 微软雅黑;
	color: #fff;
	background-color: #1ab394;
	color: #fff;
	background-color: #1ab394;
	padding: 8px 0;
	color: #fff;
	background-color: #1ab394;
	color: #fff;
	background-color: #1ab394;
}
</style>
</head>
<body class="fixed-sidebar full-height-layout gray-bg fixed-nav" style="overflow:hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="navbar-minimalize_my left_top">
				<span class="open_menu" style="display: none;">展开</span><span class="close_menu">收缩</span>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu"></ul>
			</div>
		</nav>
		<!--左侧导航结束-->
		<!-- 右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-fixed-top" role="navigation" style="margin-bottom: 0">
					<div class="navbar-header">
						<div style="padding: 10px">
							<span style="font-size: 28px;font-family: 微软雅黑">直播平台</span>
						</div>
						<%--						<img alt="" sign="org_name_img" src="">--%>
						<%--						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>--%>
						<%--						<form role="search" class="navbar-form-custom" method="post" action="search_results.html">--%>
						<%--							<div class="form-group">--%>
						<%--								<input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">--%>
						<%--							</div>--%>
						<%--						</form>--%>
					</div>

					<ul class="nav navbar-top-links navbar-right">
						<li class="dropdown"><span id="user_show_span">用户信息</span></li>
						<li class="dropdown hidden-xs"><a class="right-sidebar-toggle" href="javascript:updatePwd();" aria-expanded="false"><i class="fa fa-lock"></i>修改密码</a></li>
						<li class="dropdown"><img src="resources/homeFrame/images/people.png" height="40px">
						</li>
					</ul>
				</nav>
			</div>
			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab" data-id="home">首页</a>
					</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						操作<span class="caret"></span>
					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<!--                     <li class="J_tabShowActive"> -->
						<!--                         <a>定位当前选项卡</a> -->
						<!--                     </li> -->
						<!--                     <li class="divider"></li> -->
						<li class="J_tabReloadPage"><a> 刷新当前</a></li>
						<li class="J_tabCloseAll"><a>关闭全部</a></li>
						<li class="J_tabCloseOther"><a>关闭其他</a></li>
					</ul>
				</div>
				<a href="system/sys/loginController/logout.action" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="" frameborder="0" data-id="home" seamless></iframe>
			</div>
			<div class="footer">
				<!-- 				<div class="pull-right">版本</div> -->
				<div align="center">疯狂工作室</div>
			</div>
		</div>
		<!--右侧部分结束-->
		<!--右侧边栏开始-->
		<!--右侧边栏结束-->
		<!--mini聊天窗口开始-->
	</div>
	<script type="text/javascript" src="resources/homeFrame/jquery/2.1.4/jquery.min.js"></script>
	<script type="text/javascript" src="resources/homeFrame/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/homeFrame/metisMenu/jquery.metisMenu.js"></script>
	<script type="text/javascript" src="resources/homeFrame/slimscroll/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="resources/homeFrame/js/hplus.min.js?v=4.1.0"></script>
	<script type="text/javascript" src="resources/homeFrame/js/contabs.min.js"></script>
	<script type="text/javascript" src="resources/homeFrame/pace/pace.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/plugins/toastr/toastr.min.css">
	<script type="text/javascript" src="resources/plugins/toastr/toastr.min.js"></script>
	<script type="text/javascript" src="resources/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="resources/system/common/js/communal.js"></script>
	<script type="text/javascript" src="resources/homeFrame/js/index.js"></script>
</body>
</html>

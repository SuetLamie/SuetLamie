<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>业务管理-->通讯录管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="resources/ext/ux/DateTimeField/Spinner.css" />
<script type="text/javascript" src="resources/js/system/pageInit.js"></script>
<script type="text/javascript" src="resources/ext/ux/Spinner.js"></script>
<link rel="stylesheet" type="text/css" href="resources/ext/ux/css/Spinner.css"/>
<script type="text/javascript" src="resources/ext/ux/SpinnerField.js"></script>
<script type="text/javascript" src="resources/ext/ux/DateTimeField.js"></script>
<script type="text/javascript" src="resources/js/system/sys/sysLog.js"></script>

<script type="text/javascript">
	Ext.onReady(function() {
			var sl = new SysLog();
			var vp = new Ext.Viewport({
						layout : 'border',
						items : [{
									region : 'center',
									//autoScroll : true,
									margins : '5 5 5 5',
									border : true,
									layout : 'anchor',
									items : [sl.grid]
								}]
					});
		});
</script>
</head>
<body>
	<div></div>
</body>
</html>

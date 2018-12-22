<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'run.jsp' starting page</title>
</head>
<body>
	<%
		double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
		double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);
		double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);
		double useMemory = (int) ((total - free) * 100) / 100.0;
		double freeMemory = (int) ((max - useMemory) * 100) / 100.0;
	%>
	已用内存：<%=useMemory%>MB
	<br> 剩余内存：<%=freeMemory%>MB
	<br> 最大内存：<%=max%>MB
	<br>
</body>
</html>

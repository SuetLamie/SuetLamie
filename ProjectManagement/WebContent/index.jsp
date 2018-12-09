<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
		body{
			width: 400px;
			margin: 200px auto;
			border:2px solid red;
			text-align: center;
		}	
</style>
</head>
<body>
	<h1>项目合同管理</h1>
	<p><a href="<%=path %>/page/info">需求1</a></p>
	<p><a href="<%=path %>/page/collection">需求2</a></p>
	<p><a href="<%=path %>/page/visit">需求3</a></p>
</body>
</html>
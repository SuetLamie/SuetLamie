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
</head>
<body>
	<section>
		<h1>小商城</h1>
		<h1>${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }</h1>
		<form action="<%=path %>/page/login" method="post">
			<p>
				账号：<input type="text" value="" name="user" placeholder="请输入账号" />
			</p>
			<p>
				密码：<input type="text" value="" name="password" placeholder="请输入密码" />
			</p>
			<p>
				<input type="reset" value="重置" />
				<input type="submit" value="提交" />
			</p>
		</form>
	</section>
</body>
</html>
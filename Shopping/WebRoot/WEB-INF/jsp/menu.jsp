<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path=request.getContextPath();
%>
<c:if test="${login!='success' }">
	<c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<p>
			<a href="<%=path %>/page/productsmanagement">商品管理</a>
		</p>
		<p>
			<a href="<%=path %>/page/ordermanagement">订单管理</a>
		</p>
		<p>
			<a href="<%=path %>/page/usermanagement">用户管理</a>
		</p>
		<p>
			<a href="<%=path %>/page/shoppingcat">购物车</a>
		</p>
	</section>
</body>
</html>
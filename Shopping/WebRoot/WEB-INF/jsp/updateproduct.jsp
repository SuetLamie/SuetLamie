<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spmvc" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="product" class="linfei.pojo.Product" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<c:if test="${login!='success' }">
	<c:redirect url="/index.jsp"/>
</c:if>
<body>
		<h1>修改商品</h1>
		<spmvc:form modelAttribute="product" action="/Shopping/product/updateproductbyid" method="post">
			<p>				
				<spmvc:hidden path="id" />
			</p>
			<p>
				<spmvc:label path="name">商品名称:</spmvc:label>
				<spmvc:input path="name" />
			</p>
			<p>
				<spmvc:label path="price">商品价格:</spmvc:label>
				<spmvc:input path="price" />
			</p>
			<p>
				<spmvc:label path="num">商品数量:</spmvc:label>
				<spmvc:input path="num" />
			</p>
			<p>
				<spmvc:label path="info">商品详情:</spmvc:label>
				<spmvc:input path="info" />
			</p>
			<p>
				<input type="reset" value="重置" />
				<input type="submit" value="保存" />
			</p>			
		</spmvc:form>		
</body>
</html>
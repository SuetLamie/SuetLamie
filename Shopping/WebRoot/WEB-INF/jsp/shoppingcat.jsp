<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	th,td{
		width:100px;
	}
</style>
</head>
<body>
	<section>
		<h1>购物车</h1>
		<table border="1px">
			<tr>
				<th>编号</th><th>名称</th><th>详情</th><th>总数量</th><th>总价格</th>
			</tr>
			<c:forEach items="${ShoppingCat.shoppingItemlist }" var="ShoppingItem">
				<tr>
					<td>${ShoppingItem.product.id }</td><td>${ShoppingItem.product.name }</td><td>${ShoppingItem.product.info }</td><td>${ShoppingItem.num }</td><td>${ShoppingItem.totalprice }</td>
				</tr>
			</c:forEach>
		</table>
		<p>
			<span>总数量：<span id="span_1">${ShoppingCat.shoppingtotalnum }</span></span><span>总价格：${ShoppingCat.shoppingtotalprice }</span>
		</p>
		<p>
			<button class="suborder">提交订单</button>
		</p>		
	</section>
	<script type="text/javascript" src="<%=path %>/statics/js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".suborder").click(function(){
			if($("#span_1").html()==""){
				alert("无法提交订单，请购买");
			}else{
				window.location.href="<%=path %>/page/orderPage";
			}			
		})
	})
	</script>
</body>
</html>
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
<style>
	.div_1{
		display: none;
	}	
</style>
</head>
<c:if test="${login!='success' }">
	<c:redirect url="/index.jsp"/>
</c:if>
<body>
	<section>
		<h1>商品管理</h1>
		<table border="1px" class="table">
			<tr>
				<th>编号</th><th>名称</th><th>价格</th><th>库存</th><th>详细信息</th><th colspan="3">操作</th>
			</tr>
			<c:forEach items="${productlist }" var="product">
				<tr>
					<td>${product.id }</td><td>${product.name }</td><td>${product.price }</td><td>${product.num }</td><td>${product.info }</td><td><button class="del">删除</button></td><td><button class="update">修改</button></td><td><button class="addshoppingcat">添加购物车</button></td>
				</tr>
			</c:forEach>			
		</table>
	</section>
		<p>
			<button class="addproduct">添加商品</button>
		</p>
	<div class="div_1">				
		<spmvc:form modelAttribute="product" action="" method="post">
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
				<button class="add">保存</button>
				<button class="cancel">取消</button>
			</p>			
		</spmvc:form>
	</div>
	<script type="text/javascript" src="<%=path %>/statics/js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".del").click(function(){
				var productid=$(this).parent().prev().prev().prev().prev().prev().html();
				var Jtr=$(this).parent().parent();
				//alert(productid);
				$.ajax({
					"url":"<%=path %>/product/delproductbyid",
					"data":"id="+productid,
					"type":"post",
					"success":function(cun){
						if(cun>0){
							alert("删除成功。");
						}else{
							alert("删除失败。");
						}
						Jtr.remove();
					}
				})
			})
			$(".update").click(function(){
				var productid=$(this).parent().prev().prev().prev().prev().prev().prev().html();
				window.location.href="<%=path %>/page/updateproduct?id="+productid;
			})
			$(".addproduct").click(function(){
				$(".div_1").slideDown();
			})
			$(".cancel").click(function(){
				$(".div_1").slideUp();
			})
			$(".add").click(function(){
				$.ajax({
					"url":"/Shopping/product/addProduct",
					"data":$("form").serialize(),
					"type":"post",
					"success":function(product){
						var content="<tr>";
						content+="<td>"+product.id +"</td>";
						content+="<td>"+product.name +"</td>";
						content+="<td>"+product.price +"</td>";
						content+="<td>"+product.num +"</td>";
						content+="<td>"+product.info +"</td>";
						content+="</tr>";
						$(".table").append(content);
					}
				})
			})
			$(".addshoppingcat").click(function(){
				var productid=$(this).parent().prev().prev().prev().prev().prev().prev().prev().html();
				var productnum=$(this).parent().prev().prev().prev().prev().html();
				var Jproduct=$(this).parent().prev().prev().prev().prev();
				//alert(productnum);
				$.ajax({
					"url":"<%=path %>/ShoppingCat/addproduct",
					"data":"id="+productid,
					"type":"post",
					"success":function(cun){
						if(cun>0){
							alert("添加成功。");
							Jproduct.html(productnum-1);
						}else{
							alert("添加失败,库存不足");
						}							
					}
				})
			})
		})
	</script>
</body>
</html>
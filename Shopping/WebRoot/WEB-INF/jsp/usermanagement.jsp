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
</head>
<body>
<c:if test="${login!='success' }">
	<c:redirect url="/index.jsp"/>
</c:if>
	<section>
		<h1>用户管理</h1>
		<table border="1px">
			<tr>
				<th>ID</th><th>NAME</th><th>PASSWORD</th><th>操作</th>
			</tr>
			<c:forEach items="${userlist }" var="user">
				<tr>
					<td>${user.id }</td><td>${user.name }</td><td>${user.password }</td><td><button class="del">删除</button></td>
				</tr>
			</c:forEach>			
		</table>
	</section>
	<script type="text/javascript" src="<%=path %>/statics/js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".del").click(function(){
				var userid=$(this).parent().prev().prev().prev().html();
				var Jtr=$(this).parent().parent();
				//alert(userid);
				$.ajax({
					"url":"<%=path %>/user/deluserbyid",
					"data":"id="+userid,
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
		})
	</script>
</body>
</html>
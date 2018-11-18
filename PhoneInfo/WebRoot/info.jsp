<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${informations.title}</h1>
	<p>发表于：<fmt:formatDate value="${informations.reportTime}" pattern="yyyy-MM-dd hh:mm:ss" /></p>
	<p>${informations.content}</p>
	<h3>读者回应</h3>
	<p id="ppp">
	<c:forEach items="${informations.replies}" var="replies">		
		<div style="background-color: gray">
			<p>发表于：<fmt:formatDate value="${replies.replyTime}" pattern="yyyy-MM-dd hh:mm:ss" /></p>
			<p>${replies.ucontent}</p>
		</div>		
	</c:forEach>
	</p>
	<p>快速回复（字数200字以内）：</p>
		<p><textarea rows="5" cols="50"></textarea></p>
		<p><button value="提交">提交</button>&nbsp;<a href="<%=request.getContextPath() %>/index">返回首页</a></p>
	<script type="text/javascript" src="statics/js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("button").click(function(){
				var textarea=$("textarea").val();
				var informationsid=${informations.id};
				$.ajax({
					"url":"submitreplies",
					"data":"textarea="+textarea+"&"+"informationsid="+informationsid,
					"type":"post",
					"success":function(replies){
						//alert(replies.replyTime);
						$("#ppp").prepend("<div style='background-color: gray'><p>发表于："+replies.replyTime+"</p><p>"+replies.ucontent+"</p></div>");
					}
				})
			})
		})
	</script>	
</body>
</html>
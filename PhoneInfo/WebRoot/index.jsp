<%@page import="linfei.pojo.Pages"%>
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
	<h1>手机资讯</h1>
	<table id="table">
		<tr style="background-color:gray;">
			<th>序号</th><th>标题</th><th>回复/查看</th><th>发表时间</th><th>最新回复</th>
		</tr>
			<c:forEach items="${pages.informations}" var="informations">
			<tr>
			<td>${informations.id }</td><td><a href="<%=request.getContextPath() %>/info?id=${informations.id }">${informations.title }</a></td><td>${informations.replyCount }/${informations.viewCount }</td><td><fmt:formatDate value="${informations.reportTime }" pattern="yyyy-MM-dd hh:mm:ss" /></td><td><fmt:formatDate value="${informations.lastPostTime }" pattern="yyyy-MM-dd hh:mm:ss" /></td>
			</tr>
			</c:forEach>		
	</table>
	<p>
		<button id="index" >首页</button >
		<button id="pre" >上一页</button>
		<button id="next" >下一页</button>
		<button id="last" >末页</button>
		第<span id="span1">${pages.currentPageNo}</span>页/共<span id="span2">${pages.totalPageCount}</span>页
	</p>
	<script type="text/javascript" src="statics/js/jquery-3.3.1.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			fun();
			$("button").click(function(){
				$.ajax({
					"url":"limit",
					"data":"id="+$(this).html(),
					"type":"post",
					"success":function(pages){
						$("#table tr:first").siblings().remove();					
						var result=$(pages.informations);							
						var options = "";
						for(var i = 0; i < result.length; i++){
								options +="<tr>";
								options += "<td>"+result[i].id+"</td>";
								options += "<td>"+"<a href='<%=request.getContextPath() %>/info?id="+result[i].id+"'>"+result[i].title+"</td>";
								options += "<td>"+result[i].replyCount+"/"+result[i].viewCount+"</td>";
								options += "<td>"+result[i].reportTime+"</td>";
								options += "<td>"+result[i].lastPostTime+"</td>";
								options +="</tr>";
						}
						$("#table").append(options);
						//alert(pages.currentPageNo);
						$("tr:odd").css("background-color","green");
						$("#span1").html(pages.currentPageNo);
						$("#span2").html(pages.totalPageCount);
						fun();
					}
				})
			})
		})
		function fun(){
			$("tr:odd").css("background-color","green");
			if($("#span1").html()=="1"){
				$("#index").attr({"disabled":"disabled"});
				$("#pre").attr({"disabled":"disabled"});
			}else{
				$("#index").removeAttr("disabled")
				$("#pre").removeAttr("disabled")
			}
			if($("#span1").html()==$("#span2").html()){
				$("#last").attr({"disabled":"disabled"});
				$("#next").attr({"disabled":"disabled"});
			}else{
				$("#last").removeAttr("disabled")
				$("#next").removeAttr("disabled")
			}
		}
	</script>
</body>
</html>
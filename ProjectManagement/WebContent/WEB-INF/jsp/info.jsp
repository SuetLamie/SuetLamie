<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spmvc" uri="http://www.springframework.org/tags/form" %>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.section_1{
		width: 1000px;
		margin: 200px auto;
	}
	.p_1{
		text-align: center;
	}
	.form{
		position: relative;
	}
	.p_2{
		line-height: 50px;
		width: 400px;
		display: inline-block;
		position: absolute;
		right: 0px;
		text-align: right;
	}
	.p_3{
		line-height: 50px;
		width: 400px;
		display: inline-block;
	}
	spmvc:input,spmvc:select{
		width: 200px;
		height: 30px;	
	}
	th,td{
		text-align: left;
		width: 120px;
	}
	th:last-child,td:last-child{
		text-align: center;
	}
</style>
</head>
<body>
	<div class="section_1">
		<h3 style="color: blue;">项目合同信息</h3>
		<hr />
		<table>
			<tr>
				<th>项目名称</th><th>签订日期</th><th>付款方式</th><th>合同编号</th><th>合同总金额</th><th>免费质保年限</th><th>发票计划</th><th colspan="2">操作</th>
			</tr>
			<tr>
				<td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td><td><button value="修改">修改</button><button value="删除">删除</button></td>
			</tr>
		</table>
		<h3 style="color: blue;">基本信息</h3>
		<hr />
		<spmvc:form class="form" action="<%=path %>/InfoManagement/insertInfoManagement" modelAttribute="linfei.pojo.InfoManagement" method="post">
			<p class="p_3">
				项目名称：
				<spmvc:input path="projectName" placeholder="请输入项目名称" />*<spmvc:errors path="projectName"></spmvc:errors><br />			
				签订日期：
				<spmvc:input path="signTime" placeholder="请输入签订日期" />*<spmvc:errors path="signTime"></spmvc:errors><br />
				付款方式：
				<spmvc:select path="payment">
					<spmvc:option value="一次性">一次性</spmvc:option>
					<spmvc:option value="分期付款">分期付款</spmvc:option>
				</spmvc:select>			
			</p>
			<p class="p_2">
					合同编号：
					<spmvc:input path="contractNo" placeholder="请输入合同编号"/>*<spmvc:errors path="contractNo"></spmvc:errors><br />
					合同总金额：
					<spmvc:input path="contractMonery" placeholder="请输入合同总金额"/>*<spmvc:errors path="contractMonery"></spmvc:errors><br />
					免费质保年限：
					<spmvc:input path="warrantyTime" placeholder="请输入质保年限"/>*<spmvc:errors path="warrantyTime"></spmvc:errors><br />
			</p>
			<p>
				发票计划：
				<spmvc:textarea path="Invoice"/>
			</p>
			<p class="p_1">
				<spmvc:button class="button_1" value="保存">保存</spmvc:button>
				<spmvc:button class="button_2" value="返回">返回</spmvc:button>
			</p>
		</spmvc:form>
	</div>
	<script src="../../statics/js/jquery-3.3.1.js"></script>
	<script src="../../statics/js/jquery.validate.js"></script>
	<script src="../../statics/js/messages_zh.js"></script>
	<script>
		$().ready(function() {
		  $(".button_1").click(function(){
		  	 $(".form").Submit();
		  })
		  $(".button_2").click(function(){
		  	 history.back();
		  })
		  /*$(".form").validate({
		    rules: {
		      projectName: "required",
		      signTime: "required",
		      contractNo: "required",
		      contractMonery: "required",
		      warrantyTime: "required",
		      Invoice: "required"
		  }
		})*/
	})	  
	</script>
</body>
</html>
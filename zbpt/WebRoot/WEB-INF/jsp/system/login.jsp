<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 让页面的宽度跟设备的宽度一致，不出现横向滚动条-->
<!-- 让页面的宽度跟设备的宽度一致，不出现横向滚动条-->
<title>直播平台--》登录</title>
<link rel="stylesheet" href="resources/system/login/css/login.min.css">
<link rel="stylesheet" href="resources/system/login/iconfont/iconfont.css">
<script type="text/javascript" src="resources/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="resources/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
	function login() {
		var fm = document.loginForm;
		var account = fm.account.value;
		var password = fm.password.value;
		if (account.trim().length == 0) {
			$("#msg").html("用户名不能为空！！！");
			return false;
		}
		if (password.trim().length == 0) {
			$("#msg").html("密码不能为空！！！");
			return false;
		}
		$
				.ajax({
					type : "POST",
					url : "system/sys/loginController/login.action",
					dataType : "json",
					data : {
						account : account,
						password : password
					},
					success : function(data) {
						if (data.execute) {
							window.location.href = 'system/sys/loginController/queryMain.action';
						} else {
							alert(data.msg);
						}
					}
				});
	}
</script>
</head>
<body>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form name="loginForm" class="form form-horizontal" action="#" method="post">
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i> </label>
					<div class="formControls col-xs-8">
						<input id="account" name="account" type="text" placeholder="账户" class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i> </label>
					<div class="formControls col-xs-8">
						<input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<input name="" onclick="login()" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
						<input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;重&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;">
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
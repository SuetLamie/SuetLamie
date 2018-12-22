<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String imgip = request.getServerName().trim();
	if ("127.0.0.1".equals(imgip)) {
		imgip = "118.89.240.123";
	}

	String resourcesBasePath = request.getScheme() + "://" + imgip + ":80/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>视频预览</title>
</head>
<script type="text/javascript" src="resources/plugins/ckplayer6.8/ckplayer/ckplayer.js" charset="utf-8"></script>

<body style="background-color:#5E5E5E">
	<div id="a1"></div>
	<script type="text/javascript">

		var npath = '<%=resourcesBasePath + request.getParameter("path")%>';

		var flashvars = {
			f : npath,
			c : 0,
			b : 1,
			i : ''
		};
		var video = [ npath + '->video/mp4' ];
		CKobject.embed('plug/ckplayer6.8/ckplayer/ckplayer.swf', 'a1', 'ckplayer_a1', '600', '400', false, flashvars, video)
		function closelights() {//关灯
			alert(' 本演示不支持开关灯');
		}
		function openlights() {//开灯
			alert(' 本演示不支持开关灯');
		}
	</script>
</body>
</html>
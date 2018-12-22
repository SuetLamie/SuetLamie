<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>upload test</title>
		<link href="ext/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src="ext/adapter/ext/ext-base.js"></script>
		<script type='text/javascript' src="ext/ext-all.js"></script>
		<script type="text/javascript" src="plug/uploader/swfupload.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
		<script type="text/javascript" src="js/comm/communal.js"></script>
		<script type="text/javascript">
		var basePath="<%=path%>/";
		</script>
		<script type="text/javascript" src="plug/uploader/uploaderPanel.js"></script>
		<script type="text/javascript">
Ext.onReady(function(){  
    Ext.QuickTips.init();  
    new Ext.Window({  
        width : 650,  
        title : 'swfUpload demo',  
        height : 300,  
        layout : 'fit',  
        items : [  
            {  
                xtype:'uploadPanel',  
                border : false,  
                fileSize : 1024*50,//限制文件大小  
                uploadUrl : 'uploadFiles.action',  
                flashUrl : 'plug/uploader/swfupload.swf',  
                filePostName : 'file', //后台接收参数  
                fileTypes : '*.*',//可上传文件类型  
                postParams : {savePath:'upload\\'} //上传文件存放目录  
            }  
        ]  
    }).show();  
});  
		</script>
	</head>

	<body>
		<div></div>
	</body>
</html>

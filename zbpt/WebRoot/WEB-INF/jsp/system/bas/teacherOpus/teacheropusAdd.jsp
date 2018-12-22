<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String htmlData = request.getParameter("synopsis") != null ? request.getParameter("synopsis") : "";
%>

<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'teacheropusAdd.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--富文本框 配置-->
<link rel="stylesheet" type="text/css" href="resources/plugins/kindeditor/themes/default/default.css">
<link rel="stylesheet" type="text/css" href="resources/plugins/kindeditor/plugins/code/prettify.css">
<script type="text/javascript" charset="utf-8" src="resources/plugins/kindeditor/kindeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="resources/plugins/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="resources/plugins/kindeditor/plugins/code/prettify.js"></script>

<!--bootstrap 配置-->
<script type="text/javascript" src="resources/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery_form.js"></script>
<script type="text/javascript" src="resources/plugins/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/bootstrap-table/bootstrap-table.min.css">
<script type="text/javascript" src="resources/plugins/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/toastr/toastr.min.css">
<script type="text/javascript" src="resources/plugins/toastr/toastr.min.js"></script>

<link rel="stylesheet" type="text/css" href="resources/plugins/bootstrapValidator/css/bootstrapValidator.min.css">
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/bootstrapValidator.local.js"></script>
<script type="text/javascript" src="resources/plugins/bootstrapValidator/js/language/zh_CN.js"></script>
<!-- 业务 配置 -->
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
<script type="text/javascript" src="resources/system/common/js/communal.js"></script>
<script type="text/javascript" src="resources/system/common/js/ComBoxData.js"></script>
<script type="text/javascript" src="resources/system/common/js/json2.js"></script>
<script type="text/javascript" src="resources/system/common/js/sysPower.js"></script>
<script type="text/javascript" src="resources/system/common/js/pageInfoCfg.js"></script>

<script type="text/javascript" src="resources/system/bas/teacherOpus/teacherOpusAdd.js"></script>
<script>
		KindEditor.ready(function(K) {
			var editor = K.create('textarea[name="synopsis"]', {
				cssPath : 'resources/plugins/kindeditor/plugins/code/prettify.css',
				uploadJson : 'resources/plugins/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'resources/plugins/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function(){ //kindeditor创建后，将编辑器的内容设置到原来的textarea控件里
		    	        this.sync();   
		    	},
		   	 	afterChange: function(){ //编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
		    	        this.sync();   
		    	},
				afterBlur: function () {  //编辑器聚焦后，将编辑器的内容设置到原来的textarea控件里
					this.sync(); 
				}
				});
			prettyPrint();
		});
</script>
</head>
<body>
<%=htmlData%>
	<div class="bootstrap-form">
		<form role="form" class="form-horizontal" id="teacherOpusForm" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">作品标题：</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="title">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">作品封面：</label>
				<div class="col-xs-9">
					<input type="file" id="imgFile" name="imgFile">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">作品：</label>
				<div class="col-xs-9">
					<input type="file" id="pathFile" name="pathFile">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">排序：</label>
				<div class="col-xs-9">
					<input type="number" class="form-control" name="index">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3 form-lable">详情：</label>
				<div class="col-xs-9">
					<textarea rows="3" cols="30" id="synopsis" class="form-control" name="synopsis" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
				</div>
			</div>
			<div class="form-group form-group-button">
				<span class="col-xs-4"></span>
				<div class="col-xs-2">
					<button type="button" id="submitForm" class="btn btn-primary btn-sm">保存</button>
				</div>
				<div class="col-xs-2">
					<button type="button" onclick="obj.closeLayer()" class="btn btn-primary btn-sm">取消</button>
				</div>
				<span class="col-xs-4"></span>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		var obj;
		$(function() {
			obj = new TeacherOpusAdd();
			obj.init();
		});
	</script>

</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
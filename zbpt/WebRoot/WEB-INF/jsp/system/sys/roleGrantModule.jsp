<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>

<head>
<base href="<%=basePath%>">
<title>系统管理==》编辑角色</title>
<!--UI 配置-->
<script type="text/javascript" src="resources/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/plugins/toastr/toastr.min.css">
<script type="text/javascript" src="resources/plugins/toastr/toastr.min.js"></script>
<script type="text/javascript" src="resources/plugins/layer/layer.js"></script>
<link rel="stylesheet" href="resources/zTree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="resources/zTree/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="resources/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="resources/system/common/js/communal.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.my.css">
<!-- 业务 配置 -->
<script type="text/javascript">
	function getAsyncUrl(treeId, treeNode) {
		var node = treeNode == undefined ? $("#user_type").val() : treeNode.id;
		return "system/sys/roleController/getRoleModuleTree.action?node="
				+ node;
	};
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for ( var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].text.replace(/\.n/g, '.');
			childNodes[i].node = childNodes[i].id;
			if (!childNodes[i].leaf) {
				childNodes[i].isParent = true;
			}
		}
		return childNodes;
	}
	//授权
	function grantPower() {
		var addId = "", delId = "";
		var treeObj = $.fn.zTree.getZTreeObj("moduleTree");
		debugger;
		$(treeObj.getChangeCheckedNodes()).each(function(i) {
			if (this.checked) {
				if (addId != "") {
					addId += ",";
				}
				addId += this.id;
			} else {
				if (delId != "") {
					delId += ",";
				}
				delId += this.id;
			}
		});
		$.post("system/sys/roleController/changeRoleModule.action", {
			delId : delId,
			addId : addId,
			roleId : $("#roleId").val()
		}, function(data) {
			if (data.execute) {
				top.toastr.success(data.msg);
			} else {
				top.toastr.success(data.msg);
			}
		}, "json");
	}
	function initTree() {
		var setting = {
			async : {
				enable : true,
				url : getAsyncUrl,
				autoParam : [],
				otherParam : {
					"roleId" : $("#roleId").val()
				},
				dataFilter : filter
			},
			check : {
				enable : true
			}

		};
		$.fn.zTree.init($("#moduleTree"), setting);
	}
	$(function() {
		setTimeout(function() {
			initTree();
		}, 200);
	});
</script>
</head>
<body>
	<input id="roleId" type="hidden">
	<input id="user_type" type="hidden">
	<div class="form-group" style="height: 34px;margin-top: 5px;">
		<%--		<div class="col-xs-2">--%>
		<%--			<button type="button" onclick="expandAll()" class="btn btn-primary btn-sm">展开</button>--%>
		<%--		</div>--%>
		<%--		<div class="col-xs-2">--%>
		<%--			<button type="button" onclick="collapseAll()" class="btn btn-primary btn-sm">折叠</button>--%>
		<%--		</div>--%>
		<div class="col-xs-2">
			<button type="button" onclick="grantPower()" class="btn btn-primary btn-sm">授权</button>
		</div>
		<div class="col-xs-2">
			<button type="button" onclick="initTree();" class="btn btn-primary btn-sm">重置</button>
		</div>
	</div>
	<div style="position:absolute; height:400px;width:280px; overflow:auto;">
		<div id="moduleTree" class="ztree" style="margin-left: 15px;"></div>
	</div>
</body>
</html>
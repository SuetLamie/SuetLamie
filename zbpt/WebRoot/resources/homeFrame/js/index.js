var hf = null;
function updatePwd() {
	top.layer.open({
				type : 2,
				shade : 0.5,
				title : '修改密码',
				area : ['500px', '280px'],
				maxmin : false,
				content : 'system/sys/requestPageController/queryMain.action?pagePath=system/sys/updatePassword.jsp&modeId=1001_102_102',
				zIndex : layer.zIndex, // 重点1
				success : function(layero, index) {
				},
				end : function() {
				}
			});
}
function HomeFrame() {
	this.loadLeftTree = function(node) {
		if ($("#" + node).html()) {
			// 三级菜单bug修复
			var thirdUl = $("#" + node);
			if (thirdUl.attr("aria-expanded") == undefined || thirdUl.attr("aria-expanded") == "true") {
				// 折叠
				thirdUl.parent().attr("class", "");
				thirdUl.attr("class", "nav nav-third-level collapse").attr("aria-expanded", false).attr("style", "height:0px;");
			} else {
				// 展开
				thirdUl.parent().attr("class", "active");
				thirdUl.attr("class", "nav nav-third-level collapse in").attr("aria-expanded", true).attr("style", "");
			};
			return;
		}
		$.ajax({
					type : "POST",
					dataType : "json",
					url : "system/sys/sysController/getMenuTreeList.action",
					data : {
						node : node == "side-menu" ? "1_1" : node
					},
					success : function(msg) {
						var htmlStr = "";
						$(msg).each(function(i) {
							if (this.leaf == false) {
								if (node == 'side-menu') {
									htmlStr += '<li><a title="' + this.text + '" href="javascript:hf.loadLeftTree(\'' + this.id
											+ '\')"><i class="fa ' + this.iconCls + '"></i> <span class="nav-label">' + this.text
											+ '</span><span class="fa arrow"></span> </a><ul class="nav nav-second-level" id="' + this.id
											+ '"></ul></li>'
								} else {
									htmlStr += '<li><a href="javascript:hf.loadLeftTree(\'' + this.id + '\')">' + this.text
											+ '<span class="fa arrow"></span> </a><ul class="nav nav-third-level" id="' + this.id
											+ '"></ul></li>'
								}
							} else {
								if (node == 'side-menu') {
									htmlStr += '<li ><a class="J_menuItem" id="' + this.id + '"  data-index="' + this.id + '" href="'
											+ this.page_path + '"><i class="fa ' + this.iconCls + '"></i><span class="nav-label">'
											+ this.text + '</span></a></li>';
								} else {
									htmlStr += '<li ><a class="J_menuItem" id="' + this.id + '"  data-index="' + this.id + '" href="'
											+ this.page_path + '">' + this.text + '</a></li>';
								}

							}
						});
						$("#" + node).html(htmlStr);
						if (node == "side-menu") {
							$("#side-menu").metisMenu();
						}
					}
				});
	}
}
function initHomePageInfo() {
	var loginUser = CommUtil.sysInfo.loginUser;
	$("#user_show_span").text("欢迎你," + loginUser.username);
}
$(function($) {
			// 配置首页信息
			initHomePageInfo();
			hf = new HomeFrame();
			// $("#side-menu").metisMenu();
			hf.loadLeftTree('side-menu');

		});


/**
 * @author fangshuqiang
 * @description 给js String 添加trim()方法
 */
String.prototype.trim = function() {
	// 用正则表达式将前后空格
	// 用空字符串替代。
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
/**
 * num表示要四舍五入的数,v表示要保留的小数位数。
 * 
 * @param {}
 *            num
 * @param {}
 *            v
 * @return {}
 */
function decimal(num, v) {
	var vv = Math.pow(10, v + "");
	return Math.round(num * vv) / vv;
}
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
}
/*******************************************************************************
 * 获取客户端 操作系统
 * 
 * @return {}
 */
function GetOSInfo() {
	var _pf = navigator.platform;
	var appVer = navigator.userAgent;
	if (_pf == "Win32" || _pf == "Windows") {
		if (appVer.indexOf("WOW64") > -1) {
			_bit = "64位";
		} else {
			_bit = "32位";
		}
		if (appVer.indexOf("Windows NT 6.0") > -1 || appVer.indexOf("Windows Vista") > -1) {
			if (_bit == '64位' || appVer.indexOf("Windows Vista") > -1) {
				return 'Windows_vista ' + _bit;
			} else {
				return "Unknow1";
			}
		} else if (appVer.indexOf("Windows NT 6.1") > -1 || appVer.indexOf("Windows 7") > -1) {
			if (_bit == '32位' || appVer.indexOf("Windows 7") > -1) {
				return 'Windows_7 ' + _bit;
			} else {
				return "Unknow";
			}
		} else {
			try {
				var _winName = Array('2000', 'XP', '2003');
				var _ntNum = appVer.match(/Windows NT 5.\d/i).toString();
				return 'Windows_' + _winName[_ntNum.replace(/Windows NT 5.(\d)/i, "$1")] + " " + _bit;
			} catch (e) {
				return 'Windows';
			}
		}
	} else if (_pf == "Mac68K" || _pf == "MacPPC" || _pf == "Macintosh") {
		return "Mac";
	} else if (_pf == "X11") {
		return "Unix";
	} else if (String(_pf).indexOf("Linux") > -1) {
		return "Linux";
	} else {
		return "Unknow";
	}
}
/**
 * 获取公共下拉数据
 * 
 * @param data
 *            参数
 * @param url
 *            访问地址
 * @returns
 */
function getComboboxData(data, url) {
	if (!url)
		url = 'system/sys/sysController/getDictList.action';
	return JSON.parse($.ajax({
				url : url,
				async : false,
				data : {
					jsonData : JSON.stringify(data)
				}
			}).responseText);
}
function jsonArrayToMap(jsonArr, key) {
	var json = {};
	for (var i = 0; i < jsonArr.length; i++) {
		json[jsonArr[i][key]] = jsonArr[i];
	}
	return json;
}
/**
 * 
 * @param {}
 *            idStr eg:1,2
 * @param {}
 *            textStr eg: 男,女
 * @param {}
 *            colStr eg: id,text
 * @return {} eg:[{id:'1',text:'男'},{id:'2',text:'女'}]
 */
function strToJsonArray(idStr, textStr, colStr) {
	var idArr = idStr.split(',');
	var textArr = textStr.split(',');
	var colArr = colStr.split(',');
	if (colArr.length == 2 && idArr.length == textArr.length) {
		var rtJsonArr = [];
		for (var i = 0; i < idArr.length; i++) {
			var item = {};
			item[colArr[0]] = idArr[i];
			item[colArr[1]] = textArr[i];
			rtJsonArr.push(item);
		}
		return rtJsonArr;
	} else {
		return null;
	}
}
/**
 * 设置form值
 * 
 * @param {}
 *            fm fm元素
 * @param {}
 *            obj jsonObj 对象
 */
function setFormValuesByMap(fm, obj) {
	if (fm && obj) {
		for (var key in obj) {
			if (fm[key] && obj[key] != null) {
				fm[key].value = obj[key];
			}
		}
	}
}
/*******************************************************************************
 * 关闭父级Layer窗口
 */
function closeParentLayer() {
	var index = parent.layer.getFrameIndex(window.name); // 获取当前窗体索引
	parent.layer.close(index); // 执行关闭
}
/**
 * 删除数据事调用的方法
 * 
 * @param {}
 *            tb
 */
function bootstrapTableDeleteRefresh(tb) {
	var options = tb.bootstrapTable('getOptions');
	if ((options.totalPages == options.pageNumber) && (tb.bootstrapTable('getData').length == tb.bootstrapTable('getSelections').length)) {
		tb.bootstrapTable('refresh', {
					pageNumber : options.totalPages - 1
				});
	} else {
		tb.bootstrapTable('refresh');
	}
}
function CommUtil(cfg) {
}
CommUtil.init = function() {
	this.initSysInfo = function() {
		$.ajax({
					url : 'system/sys/sysController/getSysInfo.action',
					async : false,
					type : "POST",
					dataType : 'json',
					success : function(result) {
						CommUtil.sysInfo = result;
					}
				});
	}
	this._init = function() {
		// 初始化系统信息
		this.initSysInfo();
	}
	this._init();
}
CommUtil.init();

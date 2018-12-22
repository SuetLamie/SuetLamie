/**
 * @author 方曙强
 */
function ComBoxData() {
};
ComBoxData.initData = function(dict_id, dict_parent_id, state) {
	return function() {
		var cfg = {
			dict_id : dict_id
		};
		if (dict_parent_id)
			cfg.dict_parent_id = dict_parent_id;
		if (state)
			cfg.state = state;
		this.data = getComboboxData(cfg);
		this.dataMap = jsonArrayToMap(this.data, 'id');
	};
};
/**
 * 查询 全部 项
 * 
 * @type
 */
ComBoxData.empty = [{
			id : "",
			text : "--全部--"
		}];
/**
 * 添加/修改 请选择 项
 * @type
 */
ComBoxData.pleaseSelect = [{
			id : "",
			text : "--请选择--"
		}];
/** 用户类型 */
ComBoxData.userTypeComBox = function() {
	var t = this;
	this.data = JSON.parse($.ajax({
				url : "system/sys/frmUserTypeController/getUserTypeComboxData.action",
				async : false,
				dataType : "json",
				data : {}
			}).responseText);
	this.dataMap = jsonArrayToMap(this.data, 'id');
}
/** 状态信息 启用的 */
ComBoxData.stateComBox = function() {
	ComBoxData.initData('1001', null, 1).apply(this);
};
/** 状态信息 全部的 */
ComBoxData.stateAllComBox = function() {
	ComBoxData.initData('1001', null, null).apply(this);
};

/** 性别信息 */
ComBoxData.sexComBox = function() {
	ComBoxData.initData('1002', null, 1).apply(this);
};

/**
 * pfwang ******************************************************* /
 * 
 * /** 是否有主机码信息
 */
/** 是否存在主机码 */
ComBoxData.sfyzjmComBox = function() {
	ComBoxData.initData('1005', null, 1).apply(this);
};
/** 合同类型 */
ComBoxData.htlxComBox = function() {
	ComBoxData.initData('1003', null, 1).apply(this);
};

/** 出库状态 */
ComBoxData.ckztComBox = function() {
	ComBoxData.initData('1007', null, 1).apply(this);
};
/** 入库状态 */
ComBoxData.rkztComBox = function() {
	ComBoxData.initData('1008', null, 1).apply(this);
};

/** 课程类别 */
ComBoxData.courseLevelComBox = function() {
	ComBoxData.initData('1010', null, 1).apply(this);
};

/** 课程子类别 */
ComBoxData.courseTypeComBox = function(level) {
	ComBoxData.initData('1009', level, 1).apply(this);
};

ComBoxData.autoCreateSelectOptions = function(selectId, dataList, selected) {
	$(dataList).each(function(i) {
				var optionHtml = '<option value="' + this.id + '"';
				if (selected && this.id == selected)
					optionHtml += ' selected="selected"';
				optionHtml += ('>' + this.text + '</option>');
				$("#" + selectId).append(optionHtml);
			});
};

ComBoxData.autoCreateSelectOptionsPage = function(body, selectName, dataList,
		selected) {
	var optionHtml = "";
	$(dataList).each(function(i) {
				optionHtml += '<option value="' + this.id + '"';
				if (selected && this.id == selected)
					optionHtml += ' selected="selected"';
				optionHtml += ('>' + this.text + '</option>');
			});
	$("[name='" + selectName + "']", body).html(optionHtml);
};
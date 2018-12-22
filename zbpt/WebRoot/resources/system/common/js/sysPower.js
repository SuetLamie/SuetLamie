/*******************************************************************************
 * 创建人： 方曙强 <br>
 * 创建时间：Aug 30, 2011 <br>
 * 修改人： 方曙强 <br>
 * 修改时间：Aug 30, 2011<br>
 * 修改备注： <br>
 * 
 * @version 1.0 Aug 30, 2011 <br>
 */
// 为按钮添加权限的方法
function SysPower() {
};
SysPower.init = function() {
	$.ajax({
				url : 'system/sys/sysController/getSysPower.action',
				async : false,
				type : "POST",
				dataType : 'json',
				success : function(result) {
					SysPower.powerStr = "," + result.powerIds + ",";
				}
			});
}
SysPower.exist = function(id) {
	if (SysPower.powerStr.indexOf("," + id + ",") == -1) {
		return false;
	} else {
		return true;
	}

}
$(function() {
			SysPower.init();
		});
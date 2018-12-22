package com.surpass.mvc.system.sys.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;

import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.model.DictDetail;
import com.surpass.mvc.system.sys.model.SysMenu;

public interface SysMapper {

	/**
	 * 
	 * 方法名称：getMenuTreeList<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param setMap
	 * @return
	 */
	List<SysMenu> getMenuTreeList(Map<String, String> setMap);

	/**
	 * 
	 * 方法名称：getSysPower<br>
	 * 方法说明: 获取页面按钮权限<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param user_id
	 * @return
	 */
	String getSysPower(@Param(value = "user_id") String user_id);

	List<ComboBox> getDictList(JSONObject jsonData);
	
	List<DictDetail> getDictDetailList(DictDetail dictDetail);

}
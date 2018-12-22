/**
 * 
 */
package com.surpass.mvc.system.sys.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.model.DictDetail;
import com.surpass.mvc.system.sys.model.SysMenu;

/**
 * 类名称： SysService<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2016-4-5<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2016-4-5
 */
public interface SysService {

	/**
	 * 
	 * 方法名称：getMenuTreeList<br>
	 * 方法说明: 获取菜单信息<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-4-5<br>
	 * 
	 * @param setMap
	 * @return
	 */
	List<SysMenu> getMenuTreeList(Map<String, String> setMap);

	/**
	 * 
	 * 方法名称：getSysPower<br>
	 * 方法说明: 获取 按钮权限<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-4-6<br>
	 * 
	 * @param user_id
	 * @return
	 */
	String getSysPower(String user_id);

	/**
	 * 
	 * 方法名称：getDictList<br>
	 * 方法说明:获取数据字典 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-16<br>
	 * 
	 * @param jsonData
	 * @return
	 */
	List<ComboBox> getDictList(JSONObject jsonData);
	
	/**
	 * 
	* 方法名称：getDictList<br>
	* 方法说明: 获取数字字典<br>
	* 创建人: 王鹏飞<br>
	* 创建日期: 2018-5-28<br>
	* @param dictDetail
	* @return
	 */
	List<DictDetail> getDictDetailList(DictDetail dictDetail);

}

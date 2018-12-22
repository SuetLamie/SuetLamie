/**
*
*/
package com.surpass.mvc.system.bas.service;

import java.util.List;
import java.util.Map;

import com.surpass.mvc.system.bas.model.AppResourcesConfig;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： AppResourcesConfigService<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-7-14<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-7-14
 */
public interface AppResourcesConfigService {
	
	/**
	 * 查询 信息
	 */
	List<AppResourcesConfig> query(AppResourcesConfig obj, int start, int limit, String orderBy) throws Exception;

	/**
	 * 查询
	 */
	ResultGridStore getGridStore(AppResourcesConfig obj, int start, int limit, String orderBy) throws Exception;
	
	/**
	 * 查询 信息
	 */
	List<String> getAppResourcesConfigPageItem() throws Exception;

	/**
	 * 删除
	 */
	int deleteByKey(String ids) throws Exception;

	/**
	 * 新增
	 */
	int insert(AppResourcesConfig record) throws Exception;

	/**
	 * 根据ID查询
	 */
	Course selectByKey(Long id) throws Exception;

	/**
	 * 根据ID修改
	 */
	int updateByKey(AppResourcesConfig record) throws Exception;
	
}

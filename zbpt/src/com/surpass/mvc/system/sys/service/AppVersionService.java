/**
 * 
 */
package com.surpass.mvc.system.sys.service;

import java.util.List;

import com.surpass.mvc.system.sys.model.AppVersion;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： AppVersionService<br>
 * 类描述： <br>
 * 创建人：pfwang<br>
 */
public interface AppVersionService {

	
	/**
	 * 查询
	 */
	ResultGridStore getGridStore(AppVersion obj, int start, int limit, String orderBy) throws Exception;

	/**
	 * 数据验证
	 */
	int validate(String v_code);

	/**
	 * 添加
	 */
	void insert(AppVersion obj) throws Exception;

	/**
	 * 修改
	 */
	void update(AppVersion obj) throws Exception;

	/**
	 * 删除
	 */
	int delete(String ids) throws Exception;

	String getNewCode() throws Exception;

	List<String> getPaths(String ids)throws Exception;
	
}

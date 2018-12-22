/**
 *
 */
package com.surpass.mvc.system.bas.service;

import com.surpass.mvc.system.sys.model.DictDetail;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： CourseTypeService<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-7-7<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-7-7
 */
public interface CourseTypeService {

	/**
	 * 查询
	 */
	ResultGridStore getGridStore(DictDetail obj, int start, int limit, String orderBy) throws Exception;

	/**
	 * 添加
	 */
	void insert(DictDetail obj) throws Exception;

	/**
	 * 修改
	 */
	void update(DictDetail obj) throws Exception;

	/**
	 * 删除
	 */
	int delete(String ids) throws Exception;
	
}

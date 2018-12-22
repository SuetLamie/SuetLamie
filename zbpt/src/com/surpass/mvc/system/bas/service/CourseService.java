/**
 *
 */
package com.surpass.mvc.system.bas.service;

import com.surpass.mvc.system.bas.model.Course;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： CourseService<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-23<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-23
 */
public interface CourseService {

	/**
	 * 查询
	 */
	ResultGridStore getGridStore(Course obj, int start, int limit, String orderBy) throws Exception;

	/**
	 * 添加
	 */
	void insert(Course obj) throws Exception;

	/**
	 * 修改
	 */
	void update(Course obj) throws Exception;

	/**
	 * 删除
	 */
	int delete(String ids, String actUserId) throws Exception;
	
	Course getcourse(Integer id)throws Exception;

}

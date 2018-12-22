/**
 *
 */
package com.surpass.mvc.system.bas.service;

import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.CourseHot;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： CourseHotService<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-7-7<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-7-7
 */
public interface CourseHotService {
	/**
	 * 查询
	 */
	ResultGridStore getGridStore(Course obj, int start, int limit, String orderBy) throws Exception;

	/**
	 * 添加
	 */
	void insert(CourseHot obj) throws Exception;

	/**
	 * 删除
	 */
	int delete(String ids) throws Exception;

}

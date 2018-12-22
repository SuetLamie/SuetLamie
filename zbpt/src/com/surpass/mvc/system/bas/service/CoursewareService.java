/**
*
*/
package com.surpass.mvc.system.bas.service;

import com.surpass.mvc.system.bas.model.Courseware;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： CoursewareService<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-8-7<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-8-7
 */
public interface CoursewareService {
	
	/**
	 * 查询
	 */
	ResultGridStore getGridStore(Courseware obj) throws Exception;

	/**
	 * 添加
	 */
	void insert(Courseware obj) throws Exception;

	/**
	 * 修改
	 */
	void update(Courseware obj) throws Exception;

	/**
	 * 删除
	 */
	int delete(String ids, String actUserId) throws Exception;
}

/**  
* @Title: SysLog.java 
* @Package com.mvc.sys.service 
* @author pfwang
* @date 2016-1-7 上午11:09:57 
* @version V1.0  
*/
package com.surpass.mvc.system.sys.service;

import com.surpass.mvc.system.sys.model.SysLog;
import com.surpass.utils.ResultGridStore;

/**   
 * 
 * 类名称： SysLogService<br>
 * 类描述： 系统日志 <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2016-4-1<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2016-4-1
 */
public interface SysLogService {

	/**
	 * 获取 系统日志
	 * 
	 * @param setMap
	 * @return
	 */
	ResultGridStore getGridStore(SysLog sLog, Integer start, Integer limit, String orderBy);

	/**
	 * 新增 系统日志
	 * 
	 * @param sl
	 * @throws Exception
	 */
	void insert(SysLog sl) throws Exception;

}

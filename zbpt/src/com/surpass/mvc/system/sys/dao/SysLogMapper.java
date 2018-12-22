package com.surpass.mvc.system.sys.dao;

import java.util.List;
import java.util.Map;

import com.surpass.mvc.system.sys.model.SysLog;

/**
 * 
 * 类名称： SysLogMapper<br>
 * 类描述：系统日志 <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2016-4-1<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2016-4-1
 */
public interface SysLogMapper {

	/**
	 * 
	 * 方法名称：insert<br>
	 * 方法说明: 添加系统日志<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-4-1<br>
	 * @param record
	 * @return
	 */
    int insert(SysLog record);

	/**
	 * 
	 * 方法名称：query<br>
	 * 方法说明: 查询  角色信息 <br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-3-30<br>
	 * @param build
	 * @return
	 */
	List<SysLog> query(Map<String, Object> build);

	/**
	 * 
	 * 方法名称：total<br>
	 * 方法说明: 查询 角色信息 记录数<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-3-30<br>
	 * @param role
	 * @return
	 */
	Integer total(SysLog sysLog);
    
    
}
/**
 * 
 */
package com.surpass.mvc.system.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.surpass.mvc.system.sys.model.AppVersion;

/**
 * 类名称： AppVersionMapper<br>
 * 类描述： <br>
 * 创建人：pfwang<br>
 */
public interface AppVersionMapper {

	/**
	 * 
	 * 方法名称：updateByPrimaryKey<br>
	 * 方法说明: 修改<br>
	 * 创建人: pfwang<br>
	 */
	int updateByPrimaryKey(AppVersion record) throws Exception;

	/**
	 * 
	 * 方法名称：deleteByPrimaryKey<br>
	 * 方法说明:删除 <br>
	 * 创建人: pfwang<br>
	 */
	int deleteByPrimaryKey(@Param(value = "ids") String ids) throws Exception;

	/**
	 * 
	 * 方法名称：insert<br>
	 * 方法说明: 添加<br>
	 * 创建人: pfwang<br>
	 */
	int insert(AppVersion record) throws Exception;

	/**
	 * 
	 * 方法名称：query<br>
	 * 方法说明: 查询 信息 <br>
	 * 创建人: 王鹏飞<br>
	 * @return
	 */
	List<AppVersion> query(Map<String, Object> build) throws Exception;

	/**
	 * 
	 * 方法名称：total<br>
	 * 方法说明: 查询 记录数<br>
	 * 创建人: 王鹏飞<br>
	 * @return
	 */
	Integer total(AppVersion hmhpBmd) throws Exception;
	
	
	public AppVersion getAppVersion();
	
	int validate(@Param(value = "v_code") String v_code);

	String getNewCode()throws Exception;

	List<String> getPaths(@Param(value = "ids") String ids)throws Exception;
	
}

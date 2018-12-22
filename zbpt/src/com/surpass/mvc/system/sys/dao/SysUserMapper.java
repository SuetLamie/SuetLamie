package com.surpass.mvc.system.sys.dao;

import java.util.List;
import java.util.Map;

import com.surpass.mvc.system.sys.model.SysUser;
import com.surpass.mvc.system.sys.model.SysUserAccount;

public interface SysUserMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_sys_user
	 * 
	 * @mbggenerated Fri Dec 16 10:09:00 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_sys_user
	 * 
	 * @mbggenerated Fri Dec 16 10:09:00 CST 2016
	 */
	int insert(SysUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_sys_user
	 * 
	 * @mbggenerated Fri Dec 16 10:09:00 CST 2016
	 */
	int insertSelective(SysUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_sys_user
	 * 
	 * @mbggenerated Fri Dec 16 10:09:00 CST 2016
	 */
	SysUser selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_sys_user
	 * 
	 * @mbggenerated Fri Dec 16 10:09:00 CST 2016
	 */
	int updateByPrimaryKeySelective(SysUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_sys_user
	 * 
	 * @mbggenerated Fri Dec 16 10:09:00 CST 2016
	 */
	int updateByPrimaryKey(SysUser record);

	List<?> query(Map<String, Object> build);

	Integer total(SysUserAccount user);

	SysUserAccount selectSysUserAccountById(String id);
}
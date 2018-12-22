package com.surpass.mvc.system.sys.service;

import com.surpass.mvc.system.sys.model.FrmAccount;
import com.surpass.mvc.system.sys.model.SysUser;
import com.surpass.mvc.system.sys.model.SysUserAccount;
import com.surpass.utils.ResultGridStore;

public interface SysUserService {
	/**
	 * 
	 * 方法名称：getUserGridStore<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-16<br>
	 * 
	 * @param user
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return
	 */
	ResultGridStore getUserGridStore(SysUserAccount user, Integer start, Integer limit, String orderBy);

	/**
	 * 
	 * 方法名称：insert<br>
	 * 方法说明: 增加用户<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-19<br>
	 * 
	 * @param sysUser
	 * @param account
	 * @throws Exception
	 */
	void insert(SysUser sysUser, FrmAccount account) throws Exception;

	/**
	 * 
	 * 方法名称：updateSysUser<br>
	 * 方法说明: 修改用户<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-19<br>
	 * 
	 * @param sysUser
	 * @param account
	 * @throws Exception
	 */
	void updateSysUser(SysUser sysUser, FrmAccount account) throws Exception;

	/**
	 * 
	 * 方法名称：findAccount<br>
	 * 方法说明:查询帐号 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-19<br>
	 * 
	 * @param account
	 * @return
	 */
	FrmAccount findAccount(String account);

	/***
	 * 
	 * 方法名称：changeUserState<br>
	 * 方法说明: 更改用户状态<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-22<br>
	 * 
	 * @param userIds
	 * @param state
	 * @throws Exception
	 */
	void changeUserState(String userIds, String state) throws Exception;

	/***
	 * 
	 * 方法名称：selectSysUserAccountById<br>
	 * 方法说明:通过主键获取用户 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-30<br>
	 * 
	 * @param id
	 * @return
	 */
	SysUserAccount selectSysUserAccountById(String id);
}

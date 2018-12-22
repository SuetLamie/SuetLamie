package com.surpass.mvc.system.sys.service;

import com.surpass.mvc.system.sys.model.LoginAccount;

/***
 * 
 * 类名称： AccountLoginService<br>
 * 类描述：帐号登录 service <br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-12-12<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-12-12
 */
public interface AccountLoginService {
	/**
	 * 
	 * 方法名称：login<br>
	 * 方法说明:用户登录 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param account
	 * @param string2md5
	 * @param sysuser
	 * @return
	 */
	LoginAccount login(String account, String string2md5, int sysuser);

}

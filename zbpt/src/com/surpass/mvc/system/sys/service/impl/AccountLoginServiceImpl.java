package com.surpass.mvc.system.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.system.sys.dao.FrmAccountUserMapper;
import com.surpass.mvc.system.sys.model.LoginAccount;
import com.surpass.mvc.system.sys.service.AccountLoginService;

/**
 * 
 * 类名称： AccountLoginServiceImpl<br>
 * 类描述： 帐号登录 serviceImpl<br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-12-12<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-12-12
 */
@Service("accountLoginServiceImpl")
public class AccountLoginServiceImpl implements AccountLoginService {
	@Autowired
	private FrmAccountUserMapper frmAccountUserMapper;

	@Override
	public LoginAccount login(String account, String string2md5, int sysuser) {
		try {
			LoginAccount parAccount = new LoginAccount();
			parAccount.setAccount(account);
			parAccount.setPassword(string2md5);
			parAccount.setUserType(sysuser);
			return frmAccountUserMapper.findLoginAccountByBean(parAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

package com.surpass.mvc.system.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.system.sys.dao.FrmAccountUserMapper;
import com.surpass.mvc.system.sys.model.FrmAccountUser;
import com.surpass.mvc.system.sys.service.FrmAccountUserService;

@Service("frmAccoutUserServiceImpl")
public class FrmAccountUserServiceImpl implements FrmAccountUserService{
	
	@Autowired
	private FrmAccountUserMapper accountUserMapper;
	
	@Override
	public FrmAccountUser getFrmAccoutUserById(String act_user_id) throws Exception {
		// TODO Auto-generated method stub
		return accountUserMapper.selectByPrimaryKey(act_user_id);
	}

}

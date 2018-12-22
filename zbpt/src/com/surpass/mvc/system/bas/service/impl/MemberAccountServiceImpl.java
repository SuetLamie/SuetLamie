package com.surpass.mvc.system.bas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.system.bas.dao.MemberAccountMapper;
import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.service.MemberAccountService;
@Service("memberAccountService")
public class MemberAccountServiceImpl implements MemberAccountService{
	
	
	@Autowired
	private MemberAccountMapper dao;
	
	@Override
	public void insert(MemberAccount memberAccount) throws Exception {
		dao.insertSelective(memberAccount);
	}

	@Override
	public void updateMemberAccount(MemberAccount memberAccount) throws Exception {
		dao.updateByPrimaryKeySelective(memberAccount);
	}

	@Override
	public MemberAccount getMemberAccountByMemberId(Integer id) throws Exception {
		return dao.selectByMemberId(id);
	}

}

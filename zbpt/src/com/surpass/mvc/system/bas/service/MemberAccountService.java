package com.surpass.mvc.system.bas.service;

import com.surpass.mvc.system.bas.model.MemberAccount;

public interface MemberAccountService {
	/**
	 * 
	 * 方法名称：insert<br>
	 * 方法说明: 会员账户<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-3<br>
	 * 
	 * @param memberAccount
	 * @throws Exception
	 */
	void insert(MemberAccount memberAccount) throws Exception;
	/**
	 * 
	 * 方法名称：updateMemberAccount<br>
	 * 方法说明: 修改会员账户<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-3<br>
	 * 
	 * @param memberAccount
	 * @throws Exception
	 */
	void updateMemberAccount(MemberAccount memberAccount) throws Exception;
	
	MemberAccount getMemberAccountByMemberId(Integer id)throws Exception;
}

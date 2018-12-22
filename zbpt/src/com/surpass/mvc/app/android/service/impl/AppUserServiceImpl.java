package com.surpass.mvc.app.android.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.app.android.dao.AppUserMapper;
import com.surpass.mvc.app.android.service.AppUserService;
import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.model.Member;
import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberBrowsing;
import com.surpass.mvc.system.bas.model.MemberIdentity;

@Service("appUserService")
public class AppUserServiceImpl implements AppUserService{
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	@Autowired
	private AppUserMapper dao;
	
	@Override
	public Member login(Member member) {
		return dao.login(member);
	}

	@Override
	public void updatePassword(Member member) throws Exception {
		dao.updateByPhoneSelective(member);
	}

	@Override
	public int addAccount(MemberAccount ma) throws Exception {
		return dao.addAccount(ma);
	}

	@Override
	public int addIdentity(MemberIdentity mi) {
		return dao.addIdentity(mi);
	}

	@Override
	public int updateIdentity(MemberIdentity mi) {
		return dao.updateIdentity(mi);
	}

	@Override
	public MemberIdentity getMemberIdentity(Integer memberid) {
		return dao.getMemberIdentity(memberid);
	}

	@Override
	public List<MemberIdentity> getMemberIdentityList(MemberIdentity mi) {
		return dao.getMemberIdentityList(mi);
	}

	@Override
	public int totalByPartnerIdentity(MemberIdentity mi) {
		return dao.totalByPartnerIdentity(mi);
	}

	@Override
	public List<Member> selectByPersonNO(String commendNo) {
		return dao.selectByPersonNO(commendNo);
	}

	@Override
	public Member selectByCommendNO(String commendNo) {
		return dao.selectByCommendNO(commendNo);
	}

	@Override
	public Member getMemberById(Integer id) {
		return dao.getMemberById(id);
	}

	@Override
	public int addBrowsingHistory(MemberBrowsing mb) {
		return dao.addBrowsingHistory(mb);
	}

	@Override
	public List<MemberBrowsing> getBrowsingHistoryByMember(MemberBrowsing obj, int start, int limit, String orderBy) {
		return dao.getBrowsingHistoryByMember(parametersMapBuilder.build(start, start + limit, orderBy, obj));
	}

	@Override
	public int updateBrowsingHistory(MemberBrowsing mb) {
		return dao.updateBrowsingHistory(mb);
	}

	@Override
	public MemberBrowsing checkBrowsingHistoryByMember(MemberBrowsing mb) {
		return dao.checkBrowsingHistoryByMember(mb);
	}
	
}

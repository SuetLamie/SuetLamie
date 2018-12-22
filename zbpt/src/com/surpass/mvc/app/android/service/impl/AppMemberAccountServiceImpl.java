package com.surpass.mvc.app.android.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.app.android.dao.AppMemberAccountMapper;
import com.surpass.mvc.app.android.service.AppMemberAccountService;
import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberCard;
import com.surpass.mvc.system.bas.model.MemberIntegral;
import com.surpass.mvc.system.bas.model.MemberPresentRecord;
import com.surpass.mvc.system.bas.model.MemberRecharge;

@Service("appMemberAccountService")
public class AppMemberAccountServiceImpl implements AppMemberAccountService{
	
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	@Autowired
	private AppMemberAccountMapper dao;
	
	@Override
	public void deleteCard(MemberCard mc) throws Exception {
		dao.deleteCard(mc);
	}

	@Override
	public List<MemberCard> getListCard(MemberCard mc) throws Exception {
		return dao.getListCard(mc);
	}

	@Override
	public void insertCard(MemberCard obj) throws Exception {
		dao.insertCard(obj);
	}

	@Override
	public int getIntegral(Integer memberid) throws Exception {
		return dao.getIntegral(memberid);
	}

	@Override
	public MemberAccount getAccountInfo(Integer memberid) throws Exception {
		return dao.getAccountInfo(memberid);
	}

	@Override
	public int updateAccountByMemberId(MemberAccount ma) throws Exception {
		return dao.updateAccountByMemberId(ma);
	}

	@Override
	public int insertPresentRecord(MemberPresentRecord mrp) throws Exception {
		String preUuid =UUID.randomUUID().toString();
		mrp.setId(preUuid);
		return dao.insertPresentRecord(mrp);
	}

	@Override
	public List<MemberPresentRecord> getListAccountByMemberId(MemberPresentRecord mpr, int start, int limit,
			String orderBy) throws Exception {
		return dao.getListPresentRecordByMemberId(parametersMapBuilder.build(start, start + limit, orderBy, mpr));
	}

	@Override
	public int insertRecharge(MemberRecharge mr) throws Exception {
		String preUuid =UUID.randomUUID().toString();
		mr.setId(preUuid);
		return dao.insertRecharge(mr);
	}

	@Override
	public List<MemberRecharge> getListRechargeByMemberId(MemberRecharge mr, int start, int limit, String orderBy)
			throws Exception {
		return dao.getListMemberRechargeByMemberId(parametersMapBuilder.build(start, start + limit, orderBy, mr));
	}

	@Override
	public List<MemberIntegral> getListMemberIntegralByMemberId(MemberIntegral mi, int start, int limit, String orderBy)
			throws Exception {
		return dao.getListMemberIntegralByMemberId(parametersMapBuilder.build(start, start + limit, orderBy, mi));
	}

	@Override
	public int insertMemberIntegral(MemberIntegral mi) throws Exception {
		return dao.insertMemberIntegral(mi);
	}

	@Override
	public MemberIntegral getMemberIntegralBySignDate(Integer memberid) throws Exception {
		return dao.getMemberIntegralBySignDate(memberid);
	}

}

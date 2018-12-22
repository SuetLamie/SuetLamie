package com.surpass.mvc.system.bas.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.MemberRechargeMapper;
import com.surpass.mvc.system.bas.model.MemberRecharge;
import com.surpass.mvc.system.bas.service.MemberRechargeService;
import com.surpass.utils.ResultGridStore;

@Service("memberRechargeService")
public class MemberRechargeServiceImpl implements MemberRechargeService{
	@Autowired
	private MemberRechargeMapper memberRechargeMapper;
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	

	@Override
	public void insert(MemberRecharge memberRecharge) throws Exception {
		// TODO Auto-generated method stub
		try {
			memberRechargeMapper.insertSelective(memberRecharge);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<MemberRecharge> getMemberRechargeByMemberId(Integer memberId,int start, int limit, String orderBy) throws Exception {
		// TODO Auto-generated method stub
		return memberRechargeMapper.getRechargeByMemberId(memberId,start,limit,orderBy);
	}

	@Override
	public int updateMemberRecharge(MemberRecharge memberRecharge) throws Exception {
		// TODO Auto-generated method stub
		return memberRechargeMapper.updateByPrimaryKeySelective(memberRecharge);
	}

	@Override
	public ResultGridStore getMemberRechargeGridStore(MemberRecharge memberRecharge, int start, int limit,
			String orderBy) {
		// TODO Auto-generated method stub
		ResultGridStore rg = new ResultGridStore();
		try {
			rg.setList(memberRechargeMapper.query(parametersMapBuilder.build(start, limit, orderBy, memberRecharge)));
			rg.setTotalProperty(memberRechargeMapper.totalbyquery(parametersMapBuilder.build(start, limit, orderBy, memberRecharge)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	@Override
	public int totalbymemberId(Integer memberId) {
		// TODO Auto-generated method stub
		return memberRechargeMapper.totalbymemberId(memberId);
	}
}

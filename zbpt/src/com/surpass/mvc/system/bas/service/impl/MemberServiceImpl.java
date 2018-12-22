package com.surpass.mvc.system.bas.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.MemberMapper;
import com.surpass.mvc.system.bas.model.Member;
import com.surpass.mvc.system.bas.service.MemberService;
import com.surpass.utils.ResultGridStore;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper memberMapper;
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	@Override
	public ResultGridStore getMemberGridStore(Member member, int start, int limit, String orderBy) {
		// TODO Auto-generated method stub
		ResultGridStore rg = new ResultGridStore();
		try {
			rg.setList(memberMapper.query(parametersMapBuilder.build(start, limit, orderBy, member)));
			rg.setTotalProperty(memberMapper.total(member));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	@Override
	public void insert(Member member) throws Exception {
		// TODO Auto-generated method stub
		try {
			memberMapper.insertSelective(member);
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public void updateMember(Member member) throws Exception {
		// TODO Auto-generated method stub
		memberMapper.updateByPrimaryKeySelective(member);
	}

	@Override
	public int deleteMember(String ids)throws Exception{
		return memberMapper.deleteByPrimaryKey(ids);
	}

	@Override
	public int verifyMember(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.verifyMember(member);
	}

	@Override
	public Member getMemberById(Integer id) {
		// TODO Auto-generated method stub
		return memberMapper.selectByPrimaryKey(id);
	}

	@Override
	public Member selectByCommendNO(String commendNo) {
		// TODO Auto-generated method stub
		return memberMapper.selectByCommendNO(commendNo);
	}

	@Override
	public List<Member> selectByPersonNO(String commendNo) {
		// TODO Auto-generated method stub
		return memberMapper.selectByPersonNO(commendNo);
	}

	@Override
	public int selectByTopLevel(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.selectByTopLevel(id);
	}

	@Override
	public Member selectByMobilePhone(String mobilePhone) {
		// TODO Auto-generated method stub
		return memberMapper.selectByMobilePhone(mobilePhone);
	}

	@Override
	public Member getMemberByOpenId(String openId) {
		// TODO Auto-generated method stub
		return memberMapper.getMemberByopenId(openId);
	}

}

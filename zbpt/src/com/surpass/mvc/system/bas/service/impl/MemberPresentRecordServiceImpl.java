package com.surpass.mvc.system.bas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.MemberPresentRecordMapper;
import com.surpass.mvc.system.bas.model.MemberPresentRecord;
import com.surpass.mvc.system.bas.service.MemberPresentRecordService;
import com.surpass.utils.ResultGridStore;
@Service("memberPresentRecordService")
public class MemberPresentRecordServiceImpl implements MemberPresentRecordService{
	
	/**
	 * 请求参数绑定器
	 */
	@Autowired
	private ParametersMapBuilder parametersMapBuilder;
	
	@Autowired
	private MemberPresentRecordMapper dao;

	
	@Override
	public ResultGridStore getPresentRecordGridStore(MemberPresentRecord presentRecord, int start, int limit,
			String orderBy) {
		ResultGridStore rg = new ResultGridStore();
		rg.setList(dao.query(parametersMapBuilder.build(start, start + limit, orderBy, presentRecord)));
		rg.setTotalProperty(dao.total(presentRecord));
		return rg;
	}

	@Override
	public void insert(MemberPresentRecord presentRecord) throws Exception {
		dao.insertSelective(presentRecord);
	}

	@Override
	public void update(MemberPresentRecord presentRecord) throws Exception {
		dao.updateByPrimaryKeySelective(presentRecord);
	}

	@Override
	public int delete(String ids) throws Exception {
		return dao.deleteByPrimaryKey(ids);
	}

	@Override
	public List<MemberPresentRecord> getPresentRecordByMemberId(Integer memberId, int start, int limit, String orderBy)
			throws Exception {
		return dao.getPresentRecordByMemberId(memberId, start, limit, orderBy);
	}

	@Override
	public int totalbymemberId(Integer memberId) {
		return dao.totalbymemberId(memberId);
	}

}

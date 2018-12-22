package com.surpass.mvc.system.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.sys.dao.RackBackSetMapper;
import com.surpass.mvc.system.sys.model.RackBackSet;
import com.surpass.mvc.system.sys.service.RackBackSetService;
import com.surpass.utils.ResultGridStore;
@Service("rackBackSetService")
public class RackBackSetServiceImpl implements RackBackSetService{
	@Autowired
	private RackBackSetMapper dao;
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	@Override
	public RackBackSet getRackBackSetInfo(String rackBackSetId) {
		return dao.getRackBackSetInfo(rackBackSetId);
	}

	@Override
	public int updateInfo(RackBackSet rbs) {
		return dao.updateByPrimaryKeySelective(rbs);
	}

	@Override
	public ResultGridStore getGridStore(RackBackSet rbs, Integer start, Integer limit, String orderBy) {
		ResultGridStore rg = new ResultGridStore();
		try {
			rg.setList(dao.query(parametersMapBuilder.build(start, limit, orderBy, rbs)));
			rg.setTotalProperty(dao.total(rbs));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

}

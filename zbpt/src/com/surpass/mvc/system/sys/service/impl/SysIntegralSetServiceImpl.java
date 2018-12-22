package com.surpass.mvc.system.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.sys.dao.SysIntegralSetMapper;
import com.surpass.mvc.system.sys.model.SysIntegralSet;
import com.surpass.mvc.system.sys.service.SysIntegralSetService;
import com.surpass.utils.ResultGridStore;

@Service("sysIntegralSetService")
public class SysIntegralSetServiceImpl implements SysIntegralSetService{
	
	@Autowired
	private SysIntegralSetMapper dao;
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	@Override
	public SysIntegralSet getPoint(String integralNo) {
		return dao.getPoint(integralNo);
	}

	@Override
	public int updatePoint(SysIntegralSet sis) {
		return dao.updateByPrimaryKeySelective(sis);
	}

	@Override
	public ResultGridStore getGridStore(SysIntegralSet sis, Integer start, Integer limit, String orderBy) {
		ResultGridStore rg = new ResultGridStore();
		try {
			rg.setList(dao.query(parametersMapBuilder.build(start, limit, orderBy, sis)));
			rg.setTotalProperty(dao.total(sis));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

}

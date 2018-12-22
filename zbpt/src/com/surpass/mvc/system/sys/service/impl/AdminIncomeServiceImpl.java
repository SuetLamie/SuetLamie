package com.surpass.mvc.system.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.sys.dao.AdminIncomeMapper;
import com.surpass.mvc.system.sys.model.AdminIncome;
import com.surpass.mvc.system.sys.service.AdminIncomeService;
import com.surpass.utils.ResultGridStore;
@Service("adminIncomeService")
public class AdminIncomeServiceImpl implements AdminIncomeService{
	
	@Autowired
	private AdminIncomeMapper dao;
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	@Override
	public int addAdminIncome(AdminIncome ai) {
		return dao.insertSelective(ai);
	}

	@Override
	public ResultGridStore getGridStore(AdminIncome ai, Integer start, Integer limit, String orderBy) {
		ResultGridStore rg = new ResultGridStore();
		try {
			rg.setList(dao.query(parametersMapBuilder.build(start, limit, orderBy, ai)));
			rg.setTotalProperty(dao.total(ai));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

}

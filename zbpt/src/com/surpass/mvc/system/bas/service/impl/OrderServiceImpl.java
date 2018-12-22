package com.surpass.mvc.system.bas.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.OrderMapper;
import com.surpass.mvc.system.bas.model.Order;
import com.surpass.mvc.system.bas.service.OrderService;
import com.surpass.utils.ResultGridStore;
@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper dao;
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	
	@Override
	public ResultGridStore getGridStore(Order order, Integer start, Integer limit, String orderBy) {
		ResultGridStore rg = new ResultGridStore();
		try {
			rg.setList(dao.query(parametersMapBuilder.build(start, limit, orderBy, order)));
			rg.setTotalProperty(dao.total(order));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	@Override
	public int updateInfo(Order order) {
		return dao.updateByPrimaryKeySelective(order);
	}

	@Override
	public int addOrder(Order order) {
		return dao.insertSelective(order);
	}

}

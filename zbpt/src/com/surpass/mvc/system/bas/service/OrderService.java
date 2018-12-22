package com.surpass.mvc.system.bas.service;

import com.surpass.mvc.system.bas.model.Order;
import com.surpass.utils.ResultGridStore;

public interface OrderService {
	
	ResultGridStore getGridStore(Order order, Integer start, Integer limit, String orderBy);
	
	int updateInfo(Order order);
	
	int addOrder(Order order);
}

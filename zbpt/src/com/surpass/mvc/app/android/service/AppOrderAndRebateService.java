package com.surpass.mvc.app.android.service;

import java.util.List;

import com.surpass.mvc.system.bas.model.MemberRakeBack;
import com.surpass.mvc.system.bas.model.Order;


public interface AppOrderAndRebateService {
	/**增加订单*/
	int insertOrder(Order order)throws Exception;
	/**获取用户订单记录*/
	List<Order> getOrderByMemberId(Order o, int start, int limit, String orderBy) throws Exception;
	/**更新订单*/
	int updateOrder(Order order);
	/** 取消订单*/
	void deleteOrder(Order order)throws Exception;
	/**增加返佣记录*/
	int insertRakeBack(MemberRakeBack mrb);
	/**获取用户获得的返佣记录*/
	List<MemberRakeBack> getRakeBackByMemberId(MemberRakeBack mrb, int start, int limit, String orderBy) throws Exception;
	/**获取用户购买物品返佣给谁的记录*/
	List<MemberRakeBack> getRakeBackByPersonId(MemberRakeBack mrb, int start, int limit, String orderBy) throws Exception;
}

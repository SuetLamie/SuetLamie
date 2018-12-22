package com.surpass.mvc.app.android.dao;

import java.util.List;
import java.util.Map;

import com.surpass.mvc.system.bas.model.MemberRakeBack;
import com.surpass.mvc.system.bas.model.Order;

public interface AppOrderAndRebateMapper {
	/**增加订单*/
	int addOrder(Order order);
	/**更新订单*/
	int updateOrder(Order order);
	/**获取会员订单*/
	List<Order> getListOrderByMemberId(Map<String, Object> build) throws Exception;
	/**删除取消订单*/
	int deleteOrder(Order order);
	/**增加返佣记录*/
	int insertRakeBack(MemberRakeBack mrb);
	/**获取用户获得的返佣记录*/
	List<MemberRakeBack> getRakeBackByMemberId(Map<String, Object> build) throws Exception;
	/**获取用户购买物品返佣给谁的记录*/
	List<MemberRakeBack> getRakeBackByPersonId(Map<String, Object> build) throws Exception;
	
}

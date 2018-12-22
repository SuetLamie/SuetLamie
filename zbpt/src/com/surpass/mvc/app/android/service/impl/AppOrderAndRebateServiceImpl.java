package com.surpass.mvc.app.android.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.app.android.dao.AppOrderAndRebateMapper;
import com.surpass.mvc.app.android.service.AppOrderAndRebateService;
import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.MyCourseMapper;
import com.surpass.mvc.system.bas.model.MemberRakeBack;
import com.surpass.mvc.system.bas.model.MyCourse;
import com.surpass.mvc.system.bas.model.Order;
import com.surpass.utils.StringUtil;
@Service("appOrderAndRebateService")
public class AppOrderAndRebateServiceImpl implements AppOrderAndRebateService{
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;
	
	@Autowired
	private AppOrderAndRebateMapper dao;
	
	@Autowired
	private MyCourseMapper mdao;
	@Override
	public int insertOrder(Order order) throws Exception {
		//增加我购买的课程信息
		if(!StringUtil.isEmpty(order.getCourseid())){
			MyCourse mc = new MyCourse();
			String uuid = UUID.randomUUID().toString();
			mc.setId(uuid);
			mc.setCourse_id(order.getCourseid());
			mc.setMemberid(order.getMemberid());
			mc.setBegindate(new Date());
			mc.setType(2);
			Calendar cal = Calendar.getInstance();
			cal.setTime(mc.getBegindate());
			cal.add(Calendar.YEAR, 1);
			mc.setEnddate(cal.getTime());
			mdao.insert(mc);
		}
		return dao.addOrder(order);
	}

	@Override
	public List<Order> getOrderByMemberId(Order o, int start, int limit, String orderBy) throws Exception {
		return dao.getListOrderByMemberId(parametersMapBuilder.build(start, start + limit, orderBy, o));
	}

	@Override
	public int updateOrder(Order order) {
		return dao.updateOrder(order);
	}

	@Override
	public void deleteOrder(Order order) throws Exception {
		dao.deleteOrder(order);
	}

	@Override
	public int insertRakeBack(MemberRakeBack mrb) {
		return dao.insertRakeBack(mrb);
	}

	@Override
	public List<MemberRakeBack> getRakeBackByMemberId(MemberRakeBack mrb, int start, int limit, String orderBy)
			throws Exception {
		return dao.getRakeBackByMemberId(parametersMapBuilder.build(start, start + limit, orderBy, mrb));
	}

	@Override
	public List<MemberRakeBack> getRakeBackByPersonId(MemberRakeBack mrb, int start, int limit, String orderBy)
			throws Exception {
		return dao.getRakeBackByPersonId(parametersMapBuilder.build(start, start + limit, orderBy, mrb));
	}

}

package com.surpass.mvc.system.sys.scheduling;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.surpass.mvc.system.sys.model.Role;
import com.surpass.mvc.system.sys.service.RoleService;

/**
 * 
 * 类名称： OilCardScheduler<br>
 * 类描述： 油卡计划调度<br>
 * 创建人： 方曙强<br>
 * 创建时间：2018-8-23<br>
 * 修改人： 方曙强<br>
 * 修改时间：2018-8-23
 */
public class OilCardScheduler {
	private static final Logger log4j = LoggerFactory.getLogger(OilCardScheduler.class);
	@Autowired
	private RoleService roleService;

	/***
	 * 
	 * 方法名称：oilCardRebate<br>
	 * 方法说明: 返利调度方法<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2018-8-23<br>
	 */
	public void oilCardRebate() {
		log4j.info("【油卡返利】开始");
		// 调用具体业务处理
		System.err.println(JSONObject.fromObject(roleService.getRoleGridStore(new Role(), 0, 10, "")).toString());
		log4j.info("【油卡返利】结束");
	}
}

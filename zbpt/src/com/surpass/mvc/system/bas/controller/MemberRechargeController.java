package com.surpass.mvc.system.bas.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.MemberRecharge;
import com.surpass.mvc.system.bas.service.MemberRechargeService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultGridStore;

@Controller
@RequestMapping("system/bas/memberRechargeController")
public class MemberRechargeController extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberRechargeService memberRechargeService;
	
	
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "memberrecharge/memberrecharge.jsp";
	}	
	@RequestMapping("getMemberRechargeGridStore")
	@ResponseBody
	public ResultGridStore getMemberRechargeGridStore(MemberRecharge memberRecharge, Integer start, Integer limit) {
		String orderBy = getParameter("orderBy", "");
		return memberRechargeService.getMemberRechargeGridStore(memberRecharge, start, limit, orderBy);
	}
}

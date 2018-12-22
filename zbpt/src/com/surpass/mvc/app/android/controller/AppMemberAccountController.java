package com.surpass.mvc.app.android.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.app.android.service.AppMemberAccountService;
import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberCard;
import com.surpass.mvc.system.bas.model.MemberIntegral;
import com.surpass.mvc.system.bas.model.MemberPresentRecord;
import com.surpass.mvc.system.bas.model.MemberRecharge;
import com.surpass.mvc.system.sys.model.SysIntegralSet;
import com.surpass.mvc.system.sys.service.SysIntegralSetService;
import com.surpass.utils.Constants.systemSet;
import com.surpass.utils.ResultApp;
import com.surpass.utils.StringUtil;
@Controller
@RequestMapping("app/bas/account")
public class AppMemberAccountController extends BaseController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AppMemberAccountService service;
	
	@Autowired
	private SysIntegralSetService sysIntegralSetService;
	/**
	 * 获取用户的卡片
	 * **/
	@RequestMapping("getMemberCard")
	@ResponseBody
	public ResultApp getMemberCard(MemberCard mc){
		ResultApp msg = new ResultApp();
		try {
			List<MemberCard> list =service.getListCard(mc);
			msg.setPageData(list);
			msg.setSuccess(true);
			msg.setMsg("获取用户卡片成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取用户卡片失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 获取用户金额和已提现金额和电子币
	 * **/
	@RequestMapping("getAccountInfo")
	@ResponseBody
	public ResultApp getAccountInfo(MemberAccount ma){
		ResultApp msg = new ResultApp();
		try {
			MemberAccount memberAccount =service.getAccountInfo(ma.getMemberid());
			HashMap<Object, Object> param =new HashMap<>();
			param.put("hasmoney",memberAccount.getHasmoney());
			param.put("usedmoney",memberAccount.getUsedmoney());
			param.put("electronicmoney",memberAccount.getElectronicmoney());
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("获取用户账户信息成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取用户账户信息失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 用户增加卡片
	 * **/
	@RequestMapping("insertCard")
	@ResponseBody
	public ResultApp insertCard(MemberCard mc){
		ResultApp msg = new ResultApp();
		try {
			service.insertCard(mc);
			msg.setSuccess(true);
			msg.setMsg("增加成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("增加失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 用户删除卡片
	 * **/
	@RequestMapping("deleteCard")
	@ResponseBody
	public ResultApp deleteCard(MemberCard mc){
		ResultApp msg = new ResultApp();
		try {
			service.deleteCard(mc);
			msg.setSuccess(true);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("删除失败"+e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 用户提现
	 * **/
	@RequestMapping("presentMoney")
	@ResponseBody
	public ResultApp presentMoney(MemberPresentRecord mpr){
		ResultApp msg = new ResultApp();
		try {
			MemberAccount memberAccount =service.getAccountInfo(mpr.getMemberid());
			if(memberAccount.getHasmoney()<mpr.getMoney()){
				msg.setMsg("提现金额不足");
				return msg;
			}
			double hasmoney=memberAccount.getHasmoney()-mpr.getMoney();
			memberAccount.setHasmoney(hasmoney);
			memberAccount.setUpdatetime(new Date());
			service.updateAccountByMemberId(memberAccount);
			//提现时间
			mpr.setCashtime(new Date());
			service.insertPresentRecord(mpr);
			msg.setSuccess(true);
			msg.setMsg("提现请求成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("提现请求失败"+e.getMessage());
		}
		return msg;
	}

	/**
	 * 获取提现记录
	 * **/
	@RequestMapping("getPresentRecord")
	@ResponseBody
	public ResultApp getPresentRecord(MemberPresentRecord mpr, int pageindex, int pagenum){
		ResultApp msg = new ResultApp();
		try {
			List<MemberPresentRecord> list =service.getListAccountByMemberId(mpr, pageindex * pagenum, pagenum, null);
			msg.setPageData(list);
			msg.setSuccess(true);
			msg.setMsg("获取提现记录成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取提现记录失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 获取充值记录
	 * **/
	@RequestMapping("getRechargeRecord")
	@ResponseBody
	public ResultApp getRechargeRecord(MemberRecharge mr, int pageindex, int pagenum){
		ResultApp msg = new ResultApp();
		try {
			List<MemberRecharge> list =service.getListRechargeByMemberId(mr, pageindex * pagenum, pagenum, null);
			msg.setPageData(list);
			msg.setSuccess(true);
			msg.setMsg("获取充值记录成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取充值记录失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 用户充值
	 * **/
	@RequestMapping("rechargeMoney")
	@ResponseBody
	public ResultApp rechargeMoney(MemberRecharge mr){
		ResultApp msg = new ResultApp();
		try {
			MemberAccount memberAccount =service.getAccountInfo(mr.getMemberid());
			double hasmoney=memberAccount.getHasmoney()+mr.getMoney();
			memberAccount.setHasmoney(hasmoney);
			memberAccount.setUpdatetime(new Date());
			service.updateAccountByMemberId(memberAccount);
			service.insertRecharge(mr);
			msg.setSuccess(true);
			msg.setMsg("充值成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("充值失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 获取积分记录
	 * **/
	@RequestMapping("getIntegralRecord")
	@ResponseBody
	public ResultApp getIntegralRecord(MemberIntegral mi, int pageindex, int pagenum){
		ResultApp msg = new ResultApp();
		try {
			List<MemberIntegral> list =service.getListMemberIntegralByMemberId(mi, pageindex * pagenum, pagenum, null);
			msg.setPageData(list);
			msg.setSuccess(true);
			msg.setMsg("获取积分记录成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取积分记录失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 用户签到
	 * **/
	@RequestMapping("memberSign")
	@ResponseBody
	public ResultApp memberSign(MemberIntegral mi){
		ResultApp msg = new ResultApp();
		try {
			MemberIntegral mil=service.getMemberIntegralBySignDate(mi.getMemberid());
			if(!StringUtil.isEmpty(mil)){
				msg.setSuccess(false);
				msg.setMsg("用户已签到");
			}else{
				SysIntegralSet sis=sysIntegralSetService.getPoint(systemSet.QDJF);
				if(!StringUtil.isEmpty(sis)&&sis.getStatus().equals("1")){
					mi.setSigndate(new Date());
					mi.setPoint(sis.getPoint());
					mi.setStatus("1");
					service.insertMemberIntegral(mi);
					MemberAccount memberAccount =service.getAccountInfo(mi.getMemberid());
					memberAccount.setIntegral(memberAccount.getIntegral()+sis.getPoint());
					memberAccount.setUpdatetime(new Date());
					service.updateAccountByMemberId(memberAccount);
				}else{
					msg.setMsg("签到功能未启用,请联系客服开启签到功能");
					msg.setSuccess(false);
				}
				msg.setSuccess(true);
				msg.setMsg("签到成功");
			}
			
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("签到失败"+e.getMessage());
		}
		return msg;
	}
}

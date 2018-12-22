package com.surpass.mvc.system.bas.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberPresentRecord;
import com.surpass.mvc.system.bas.service.MemberAccountService;
import com.surpass.mvc.system.bas.service.MemberPresentRecordService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

@Controller
@RequestMapping("system/bas/presentRecordController")
public class PresentRecordController extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MemberPresentRecordService presentRecordService;
	@Autowired
	private MemberAccountService memberAccountService;
	
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "presentrecord/presentrecord.jsp";
	}
	
	@RequestMapping("getPresentRecordGridStore")
	@ResponseBody
	public ResultGridStore getPresentRecordGridStore(MemberPresentRecord presentRecord, Integer start, Integer limit) {
		String orderBy = getParameter("orderBy", "");
		return presentRecordService.getPresentRecordGridStore(presentRecord, start, limit, orderBy);
	}
	/**
	 * 已处理提现
	 * */
	@RequestMapping("update")
	@ResponseBody
	public ResultMsg update(MemberPresentRecord presentRecord) {
		ResultMsg msg = new ResultMsg();
		try {
			presentRecord.setDealtime(new Date());
			presentRecord.setStatus("2");
			presentRecordService.update(presentRecord);
			//更新会员账户表
			MemberAccount ma =memberAccountService.getMemberAccountByMemberId(presentRecord.getMemberid());
			double usedmoney=ma.getUsedmoney()+presentRecord.getMoney();
			ma.setUsedmoney(usedmoney);
			ma.setUpdatetime(new Date());
			memberAccountService.updateMemberAccount(ma);
			msg.setExecute(true);
			msg.setSuccess(true);
			msg.setMsg("提现记录受理成功！");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setSuccess(false);
			msg.setMsg("提现记录受理失败！");
		}
		return msg;
	}
	/**
	 * 方法说明：驳回提现
	 * 参数：编号 id
	 * 
	 * */
	@RequestMapping("revoke")
	@ResponseBody
	public ResultMsg revoke(MemberPresentRecord presentRecord) {
		ResultMsg msg = new ResultMsg();
		try {
			presentRecord.setStatus("3");
			presentRecord.setDealtime(new Date());
			presentRecordService.update(presentRecord);
			//更新会员账户表
			MemberAccount ma =memberAccountService.getMemberAccountByMemberId(presentRecord.getMemberid());
			ma.setHasmoney(ma.getHasmoney()+presentRecord.getMoney());
			ma.setUpdatetime(new Date());
			memberAccountService.updateMemberAccount(ma);
			msg.setExecute(true);
			msg.setSuccess(true);
			msg.setMsg("提现驳回成功！");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setSuccess(false);
			msg.setMsg("提现驳回失败！");
		}
		return msg;
	}
	/**
	 * 方法说明：拒绝提现
	 * id
	 * */
	@RequestMapping("refuse")
	@ResponseBody
	public ResultMsg refuse(MemberPresentRecord presentRecord) {
		ResultMsg msg = new ResultMsg();
		try {
			presentRecord.setStatus("4");
			presentRecord.setDealtime(new Date());
			presentRecordService.update(presentRecord);
			msg.setExecute(true);
			msg.setSuccess(true);
			msg.setMsg("提现记录已拒绝！");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setSuccess(false);
			msg.setMsg("提现记录已拒绝！！");
		}
		return msg;
	}
	@RequestMapping("delete")
	@ResponseBody
	public ResultMsg delete(String ids) {
		ResultMsg msg = new ResultMsg();
		if (StringUtil.isNotEmpty(ids)) {
			try {
				int row = presentRecordService.delete(ids);
				if (row > 0) {
					msg.setExecute(true);
					msg.setSuccess(true);
					msg.setMsg("删除提现记录成功!");
				} else {
					msg.setExecute(false);
					msg.setSuccess(false);
					msg.setMsg("删除提现记录失败!");
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setSuccess(false);
				msg.setMsg("删除提现记录失败!");
			}
		} else {
			msg.setExecute(false);
			msg.setSuccess(false);
			msg.setMsg("数据传输失败!");
		}
		return msg;
	}
	@RequestMapping("getPresentRecordList")
	@ResponseBody
	public ResultMsg getPresentRecordList(MemberPresentRecord presentRecord, int pageindex, int pagenum){
		ResultMsg msg=new ResultMsg();
		try {
			String orderBy = getParameter("orderBy", "");
			List<MemberPresentRecord> list =presentRecordService.getPresentRecordByMemberId(presentRecord.getMemberid(), pageindex, pagenum, orderBy);
			msg.setData(list);
			msg.setExecute(true);
			msg.setSuccess(true);
			msg.setMsg("获取提现记录成功");
		} catch (Exception e) {
			// TODO: handle exception
			msg.setSuccess(false);
			msg.setMsg("获取提现记录错误"+e.getMessage());
		}
		return msg;
	}
}

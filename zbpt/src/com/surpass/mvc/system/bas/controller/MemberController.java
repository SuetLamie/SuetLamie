package com.surpass.mvc.system.bas.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.Member;
import com.surpass.mvc.system.bas.service.MemberService;
import com.surpass.utils.Constants;
import com.surpass.utils.MD5Util;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.RotaryDrawUtil;
import com.surpass.utils.StringUtil;

/**
 * 
 * 类名称： MemberController<br>
 * 类描述： <br>
 * 创建人： 何扬<br>
 * 创建时间：2018-8-1<br>
 */
@Controller
@RequestMapping("system/sys/memberController")
public class MemberController extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ftpBasePath = "member/";
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 页面跳转 方法名称：queryMain<br>
	 * 方法说明: <br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * 
	 * @return
	 */
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "member/member.jsp";
	}
	@RequestMapping("getMemberGridStore")
	@ResponseBody
	public ResultGridStore getMemberGridStore(Member member, Integer start, Integer limit) {
		String orderBy = getParameter("orderBy", "");
		return memberService.getMemberGridStore(member, start, limit, orderBy);
	}
	
	/**
	 * 
	 * 方法名称：updatePassword<br>
	 * 方法说明: <br>
	 * 创建人: 忘记密码<br>
	 * 创建日期: 2018-8-4<br>
	 * @param member
	 * @param isAdd
	 * @return
	 */
	@RequestMapping("updatePassword")
	@ResponseBody
	public ResultMsg updatePassword(@ModelAttribute(value = "member") Member member) {
		ResultMsg msg = new ResultMsg();
		try {
			member.setPassword(MD5Util.string2MD5(member.getPassword()));
			memberService.updateMember(member);
			msg.setSuccess(true);
			msg.setMsg("会员密码修改成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("会员修改失败！");
		}
		return msg;
	}
	/**
	 * 
	 * 
	 * */
	@RequestMapping("getMember")
	@ResponseBody
	public ResultMsg getMember(@RequestParam String openId,Integer memberId){
		ResultMsg msg = new ResultMsg();
		try {
			Member member = null;
			if(!openId.equals(null)){
				member=memberService.getMemberByOpenId(openId);
			}else if(!memberId.equals(null)){
				member =memberService.getMemberById(memberId);
			}
			if(StringUtil.isEmpty(member)){
				msg.setMsg("没有此会员，请注册");
				msg.setSuccess(false);
				msg.setData(null);
			}else{
				msg.setData(member);
				msg.setSuccess(true);
				msg.setMsg("查询成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			msg.setSuccess(false);
			msg.setMsg("数据传输失败");
		}
		return msg;
		
	}
	/**
	 * 
	 * 
	 * */
	@RequestMapping("getCommonNo")
	@ResponseBody
	public ResultMsg getCommonNo(@RequestParam String commonNo){
		ResultMsg msg = new ResultMsg();
		try {
			Member member =memberService.selectByCommendNO(commonNo);
			if(StringUtil.isEmpty(member)){
				msg.setMsg("没有此会员，请联系客服分配推荐人");
				msg.setSuccess(true);
				msg.setData(null);
			}else{
				msg.setData(commonNo);
				msg.setSuccess(true);
				msg.setMsg("推荐人存在");
			}
		} catch (Exception e) {
			// TODO: handle exception
			msg.setSuccess(false);
			msg.setMsg("数据传输失败");
		}
		return msg;
		
	}
	/**
	 * 方法名称：memberReg<br>
	 * 方法说明: 会员注册<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-2<br>
	 * @param member
	 * @param isAdd
	 * @return
	 */
	@RequestMapping("memberReg")
	@ResponseBody
	public ResultMsg memberReg(@ModelAttribute(value = "member") Member member) {
		ResultMsg msg = new ResultMsg();
		try {
			int ct = memberService.verifyMember(member);
			if (ct > 0) {
				msg.setSuccess(false);
				msg.setData("cardnumber");
				msg.setData("mobilephone");
				msg.setMsg("该会员已经存在,请另输入！");
				return msg;
			}
			member.setPassword(MD5Util.string2MD5(member.getPassword()));
			/* 会员注册当前时间 */
			member.setCreatetime(new Date());
			boolean flag;
			/* 新增会员生成推荐码 */
			String code = null;
			do {
				code = RotaryDrawUtil.card();
				// 判断生成推荐码是否重复 查询到此推荐码的会员信息
				Member iscode = memberService.selectByCommendNO(code);
				if (iscode != null) {// 有会员持有此推荐码
					flag = true;
				} else {
					flag = false;
				}
			} while (flag);
			member.setCommendno(code);
			// 如果注册会员没有填写邀请码或者邀请码为空时，为此会员绑定系统推荐人
			if (StringUtil.isEmpty(member.getPersonno())) {
				member.setPersonno("XT000000");
			} else {
				// 查询此邀请码是否有会员
				Member isMember = memberService.selectByCommendNO(member.getPersonno());
				if (StringUtil.isEmpty(isMember)) {// 有此会员
					msg.setSuccess(false);
					msg.setMsg("没有此会员,请输入正确的推荐码！");
					return msg;
				}
			}
			memberService.insert(member);
			Member memberInfo = memberService.selectByCommendNO(code);
			HashMap<Object, Object> param =new HashMap<>();
			param.put("MemberId", memberInfo.getId());
			param.put("OpenId",memberInfo.getOpenId());
			msg.setData(param);
			msg.setSuccess(true);
			msg.setMsg("会员增加成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("数据填写有误");
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：deleteMember<br>
	 * 方法说明: 删除会员<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-1<br>
	 * @param id 
	 *       
	 * @return
	 */
	@RequestMapping("deleteMember")
	@ResponseBody
	public ResultMsg deleteMember(String ids) {
		ResultMsg msg = new ResultMsg();
		if (StringUtil.isNotEmpty(ids)) {
			try {
				int row = memberService.deleteMember(ids);
				if (row > 0) {
					msg.setExecute(true);
					msg.setSuccess(true);
					msg.setMsg("删除会员成功!");
				} else {
					msg.setExecute(false);
					msg.setSuccess(false);
					msg.setMsg("删除会员失败!");
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setSuccess(false);
				msg.setMsg("删除会员失败!");
			}
		} else {
			msg.setExecute(false);
			msg.setSuccess(false);
			msg.setMsg("数据传输失败!");
		}
		return msg;
	}
	
	private String getFilePath(String loginname) {
		return "/" + ftpBasePath + loginname + "/";
	}
}

package com.surpass.mvc.app.android.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.surpass.mvc.app.android.service.AppMemberAccountService;
import com.surpass.mvc.app.android.service.AppOrderAndRebateService;
import com.surpass.mvc.app.android.service.AppUserService;
import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.Member;
import com.surpass.mvc.system.bas.model.MemberAccount;
import com.surpass.mvc.system.bas.model.MemberIdentity;
import com.surpass.mvc.system.bas.model.MemberRakeBack;
import com.surpass.mvc.system.bas.service.MemberService;
import com.surpass.mvc.system.sys.model.RackBackSet;
import com.surpass.mvc.system.sys.service.RackBackSetService;
import com.surpass.mvc.weixin.miniprogram.model.MiniprogramUser;
import com.surpass.mvc.weixin.miniprogram.service.MiniprogramUserService;
import com.surpass.utils.Constants.systemSet;
import com.surpass.utils.FTPUtil;
import com.surpass.utils.MD5Util;
import com.surpass.utils.ResultApp;
import com.surpass.utils.RotaryDrawUtil;
import com.surpass.utils.StringUtil;

@Controller
@RequestMapping("app/bas/user")
public class AppUserController extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AppOrderAndRebateService orderAndRebateservice;
	@Autowired
	private AppUserService service;
	
	@Autowired
	private AppMemberAccountService accountService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private RackBackSetService rackBackSetService;
	
	@Autowired
	private MiniprogramUserService wechatUserInfoservice;
	
	private static final String ftpBasePath = "memberImg/";
	
	
	private String getFilePath(String loginname) {
		return "/" + ftpBasePath + loginname + "/";
	}
	
	@RequestMapping("login")
	@ResponseBody
	public ResultApp login(String account, String password) {
		ResultApp msg = new ResultApp();
		Member m = new Member();
		m.setMobilephone(account);
		m.setPassword(MD5Util.string2MD5(password));
		Member member =service.login(m);
		if(null !=member){
			HashMap<Object, Object> param =new HashMap<>();
			param.put("name", member.getName());
			param.put("memberId",member.getId());
			param.put("headimgurl",member.getHeadimg());
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("账号用户名正确");
		}else{
			msg.setSuccess(false);
			msg.setMsg("用户名或密码不正确!");
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：changeMobilePhone<br>
	 * 方法说明: <br>
	 * 创建人: 手机号换绑<br>
	 * 创建日期: 2018-10-13<br>
	 * @param id
	 * @param mobilephone
	 * @return
	 */
	@RequestMapping("changeMobilePhone")
	@ResponseBody
	public ResultApp changeMobilePhone(@ModelAttribute(value = "member") Member member) {
		ResultApp msg = new ResultApp();
		try {
			Member m = null;
			MiniprogramUser wechat=null;
			int ct = memberService.verifyMember(member);
			if (ct > 0) {
				msg.setSuccess(false);
				msg.setMsg("该手机号已经存在,请另输入！");
				return msg;
			}
			m =memberService.getMemberById(member.getId());
			if(m.getOpenId()!=null){
				wechat=wechatUserInfoservice.selectByPrimaryKey(m.getOpenId());
			}
			m.setMobilephone(member.getMobilephone());
			memberService.updateMember(m);
			wechat.setPhoneNumber(member.getMobilephone());
			wechatUserInfoservice.updateByPrimaryKeySelective(wechat);
			HashMap<Object, Object> param =new HashMap<>();
			param.put("memberId", member.getId());
			param.put("mobilePhone", member.getMobilephone());
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("会员手机号换绑成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("会员手机号换绑失败！");
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：changeName<br>
	 * 方法说明: <br>
	 * 创建人: 更改用户名<br>
	 * 创建日期: 2018-10-15<br>
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("changeName")
	@ResponseBody
	public ResultApp changeName(@ModelAttribute(value = "member") Member member) {
		ResultApp msg = new ResultApp();
		try {
			Member m = null;
			m =memberService.getMemberById(member.getId());
			m.setName(member.getName());
			memberService.updateMember(m);
			HashMap<Object, Object> param =new HashMap<>();
			param.put("memberId", member.getId());
			param.put("name", member.getName());
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("会员昵称修改成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("会员昵称修改失败！");
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：getMemberCommonNo<br>
	 * 方法说明: <br>
	 * 创建人: 获取推广码<br>
	 * 创建日期: 2018-10-15<br>
	 * @param id
	 * @return
	 */
	@RequestMapping("getMemberCommonNo")
	@ResponseBody
	public ResultApp getMemberCommonNo(@ModelAttribute(value = "member") Member member) {
		ResultApp msg = new ResultApp();
		try {
			Member m =memberService.getMemberById(member.getId());
			HashMap<Object, Object> param =new HashMap<>();
			param.put("memberId", m.getId());
			param.put("CommonNo", m.getCommendno());
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("会员推荐码获取成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("会员推荐码获取失败！");
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：changeHeadImgUrl<br>
	 * 方法说明: <br>
	 * 创建人: 头像更换<br>
	 * 创建日期: 2018-10-13<br>
	 * @param id
	 * @param mobilephone
	 * @return
	 */
	@RequestMapping("changeHeadImgUrl")
	@ResponseBody
	public ResultApp changeHeadImgUrl(@ModelAttribute(value = "member") Member member) {
		ResultApp msg = new ResultApp();
		try {
			Member m = null;
			m =memberService.getMemberById(member.getId());
			MultipartHttpServletRequest resources = (MultipartHttpServletRequest) getRequest();
			List<MultipartFile> headImglist = resources.getFiles("headImgFile");
			for (MultipartFile file : headImglist) {
				String fileName = file.getOriginalFilename();
				InputStream inputStream = file.getInputStream();
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				String scName = Calendar.getInstance().getTimeInMillis() + "" + (int) (1 + Math.random() * 1000)
						+ suffix;
				String ftpPath = getFilePath(m.getId().toString());
				FTPClient ftpUtil = FTPUtil.connectServer();
				FTPUtil.uploadFile(ftpUtil, ftpPath, inputStream, scName);
				String savePath = FTPUtil.getSavePath() + ftpPath + scName;
				m.setHeadimg(savePath);
			}
			memberService.updateMember(m);
			
			HashMap<Object, Object> param =new HashMap<>();
			param.put("memberId", member.getId());
			param.put("headimgurl", m.getHeadimg());
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("会员头像更换成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("会员头像更换失败！");
		}
		return msg;
	}
	/**
	 * 获取会员信息
	 * 
	 * */
	@RequestMapping("getMember")
	@ResponseBody
	public ResultApp getMember(Member m){
		ResultApp msg = new ResultApp();
		try {
			Member member = null;
			MiniprogramUser wechat=null;
			if(!m.getId().equals(null)){
				member =memberService.getMemberById(m.getId());
				if(!StringUtil.isEmpty(member.getOpenId())){
					wechat=wechatUserInfoservice.selectByPrimaryKey(member.getOpenId());
				}
			}
			if(StringUtil.isEmpty(member)){
				msg.setMsg("没有此会员，请注册");
				msg.setSuccess(false);
			}else{
				int integral=accountService.getIntegral(member.getId());
				HashMap<Object, Object> param =new HashMap<>();
				param.put("name", member.getName());
				param.put("memberId",member.getId());
				param.put("headimgurl",member.getHeadimg());
				param.put("phone",member.getMobilephone());
				param.put("commonNo", member.getCommendno());
				param.put("integral",integral);
				if(!StringUtil.isEmpty(wechat)){
					param.put("wechatInfo",true);
				}else{
					param.put("wechatInfo",false);
				}
				msg.setPageData(param);
				msg.setSuccess(true);
				msg.setMsg("查询成功");
			}
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("数据传输失败");
		}
		return msg;
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
	public ResultApp updatePassword(@ModelAttribute(value = "member") Member member) {
		ResultApp msg = new ResultApp();
		try {
			member.setPassword(MD5Util.string2MD5(member.getPassword()));
			service.updatePassword(member);
			msg.setSuccess(true);
			msg.setMsg("会员密码修改成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("会员修改失败！");
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
	public ResultApp memberReg(@ModelAttribute(value = "member") Member member) {
		ResultApp msg = new ResultApp();
		try {
			
			RackBackSet rackBackSet=rackBackSetService.getRackBackSetInfo(systemSet.TJFY);
			//上级获得的电子币
			double personMoney=rackBackSet.getLeveltwo();
			//自己注册获得的电子币
			double myMoney=rackBackSet.getLevelone();
			//新建返佣记录对象
			MemberRakeBack mrb=new MemberRakeBack();
			//新建自己返佣记录对象
			MemberRakeBack myrb=new MemberRakeBack();
			
			int ct = memberService.verifyMember(member);
			if (ct > 0) {
				msg.setSuccess(false);
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
			member.setStatus(systemSet.CourseType);
			if(!StringUtil.isEmpty(member.getPersonno())){
				Member personmember =memberService.selectByCommendNO(member.getPersonno());
				if(!StringUtil.isEmpty(personmember)){
					memberService.insert(member);
					Member memberInfo = memberService.selectByCommendNO(code);
					MemberAccount memberAccount=accountService.getAccountInfo(personmember.getId());
					memberAccount.setElectronicmoney(memberAccount.getElectronicmoney()+personMoney);
					memberAccount.setUpdatetime(new Date());
					accountService.updateAccountByMemberId(memberAccount);
					mrb.setMoney(personMoney);
					mrb.setPersonid(personmember.getId());
					//返佣类型为推荐
					mrb.setType(systemSet.RechargeType);
					mrb.setMembername(member.getName());
					mrb.setPersonname(personmember.getName());
					mrb.setCreatedate(new Date());
					mrb.setMemberid(memberInfo.getId());
					orderAndRebateservice.insertRakeBack(mrb);
				}else{
					msg.setMsg("推荐人不存在，请确认后填写");
					msg.setSuccess(false);
					return msg;
				}
			}else{
				memberService.insert(member);
			}
			Member memberInfo = memberService.selectByCommendNO(code);
			MemberAccount ma=new MemberAccount();
			ma.setMemberid(memberInfo.getId());
			service.addAccount(ma);
			//获取自己的账户信息
			MemberAccount memberAccount=accountService.getAccountInfo(ma.getMemberid());
			memberAccount.setElectronicmoney(memberAccount.getElectronicmoney()+myMoney);
			memberAccount.setUpdatetime(new Date());
			accountService.updateAccountByMemberId(memberAccount);
			myrb.setMoney(myMoney);
			myrb.setPersonid(memberInfo.getId());
			//返佣类型为注册
			myrb.setType(systemSet.AdminIncomeAgentEndType);
			myrb.setCreatedate(new Date());
			myrb.setMemberid(memberInfo.getId());
			myrb.setMembername(member.getName());
			myrb.setPersonname(member.getName());
			orderAndRebateservice.insertRakeBack(mrb);
			
			MemberIdentity mi=new MemberIdentity();
			mi.setMemberid(memberInfo.getId());
			service.addIdentity(mi);
			HashMap<Object, Object> param =new HashMap<>();
			param.put("name", memberInfo.getName());
			param.put("memberId",memberInfo.getId());
			param.put("headimgurl",memberInfo.getHeadimg());
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("会员增加成功！");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("数据填写有误");
		}
		return msg;
	}
	/**
	 * 查询微信用户信息
	 * param openId
	 * **/
	@RequestMapping("getWechatInfo")
	@ResponseBody
	public ResultApp getWechatInfo(MiniprogramUser user) {
		ResultApp msg = new ResultApp();
		Member member=memberService.getMemberByOpenId(user.getOpenId());
		HashMap<Object, Object> param =new HashMap<>();
		if(!StringUtil.isEmpty(member)){
			param.put("memberId",member.getId());
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("此用户已存在");
		}else{
			msg.setMsg("新用户");
			msg.setSuccess(false);
		}
		return msg;
	}
	/**
	 * 绑定新增微信用户信息
	 * param user
	 * param member
	 * **/
	@RequestMapping("addWechatInfo")
	@ResponseBody
	public ResultApp addWechatInfo(MiniprogramUser user,Member member) {
		ResultApp msg = new ResultApp();
		try {
			if(member.getId()==null){
				int ct = memberService.verifyMember(member);
				if (ct > 0) {
					msg.setSuccess(false);
					msg.setMsg("该手机号已被注册,请另输入！或者通过手机登录");
					return msg;
				}
			}
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
			user.setPhoneNumber(member.getMobilephone());
			user.setC_time(new Date());
			wechatUserInfoservice.insertSelective(user);
			HashMap<Object, Object> param =new HashMap<>();
			member.setOpenid(user.getOpenId());
			if(member.getId()!=null){
				memberService.updateMember(member);
				param.put("MemberId", member.getId());
			}else{
				member.setName(user.getNickName());
				member.setCommendno(code);
				member.setCreatetime(new Date());
				member.setStatus("1");
				member.setHeadimg(user.getAvatarUrl());
				memberService.insert(member);
				Member memberInfo = memberService.selectByCommendNO(code);
				MemberAccount ma=new MemberAccount();
				ma.setMemberid(memberInfo.getId());
				service.addAccount(ma);
				MemberIdentity mi=new MemberIdentity();
				mi.setMemberid(memberInfo.getId());
				service.addIdentity(mi);
				param.put("MemberId", memberInfo.getId());
			}
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("会员绑定成功");
		} catch (Exception e) {
			msg.setSuccess(true);
			msg.setMsg("绑定失败"+e.getMessage());
		}
		return msg;
	}
}

package com.surpass.mvc.app.android.controller;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.app.android.service.AppUserService;
import com.surpass.mvc.app.android.service.AppVersionService;
import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.AppResourcesConfig;
import com.surpass.mvc.system.bas.model.Member;
import com.surpass.mvc.system.bas.model.MemberBrowsing;
import com.surpass.mvc.system.bas.service.AppResourcesConfigService;
import com.surpass.mvc.system.sys.model.AppVersion;
import com.surpass.mvc.system.sys.model.DictDetail;
import com.surpass.mvc.system.sys.service.SysService;
import com.surpass.utils.ResultApp;
import com.surpass.utils.StringUtil;

/**
 * 手机端 手机串码验证
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("app/appCommon")
public class AppCommonController extends BaseController implements Serializable {
	private static final Logger LOG4J = LoggerFactory.getLogger(AppCommonController.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private SysService sysSerivce;

	@Autowired
	private AppResourcesConfigService appResourcesConfigService;

	@Autowired
	private AppVersionService appVersionService;
	

	@Autowired
	private AppUserService appUserService;
	
	
	/**
	 * 
	 * 方法名称：getTopMember<br>
	 * 方法说明: 获取上级会员信息<br>
	 * 创建人: <br>
	 * 创建日期: 2018-8-9<br>
	 * @param member 提交的会员ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("getTopMember")
	@ResponseBody
	public ResultApp getTopMember(Member member) {
		ResultApp msg = new ResultApp();
		//获取此会员的基本信息
		Member m= appUserService.getMemberById(member.getId());
		//上级会员信息
		Member topMember = appUserService.selectByCommendNO(m.getPersonno());
		if(!StringUtil.isEmpty(topMember)){
			msg.setPageData(topMember);
			msg.setSuccess(true);
			msg.setMsg("获取上级会员成功");
			return msg;
		}else{
			msg.setSuccess(false);
			msg.setMsg("未找到上级会员,此会员没有上级");
			return msg;
		}
	}
	
	/**
	 * 
	 * 方法名称：getMyMember<br>
	 * 方法说明: 获取下级会员信息<br>
	 * 创建人: <br>
	 * 创建日期: 2018-8-9<br>
	 * @param member 提交的会员ID
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("getMyMember")
	@ResponseBody
	public ResultApp getMyMember(Member member) {
		ResultApp msg = new ResultApp();
		//获取此会员的基本信息
		Member m= appUserService.getMemberById(member.getId());
		//上级会员信息
		List<Member> myMember = appUserService.selectByPersonNO(m.getCommendno());
		if(myMember.size()>0){
			msg.setPageData(myMember);
			msg.setSuccess(true);
			msg.setMsg("获取下级会员成功");
			return msg;
		}else{
			msg.setSuccess(false);
			msg.setMsg("数据为空,获取下级会员失败/或者此会员没有下级");
			return msg;
		}
	}
	/**
	 * 
	 * 方法名称：getBrowsingHistoryByMember<br>
	 * 方法说明: 获取会员浏览记录<br>
	 */
	@RequestMapping("getBrowsingHistoryByMember")
	@ResponseBody
	public ResultApp getBrowsingHistoryByMember(MemberBrowsing mb,int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try{
			List<MemberBrowsing> lis = appUserService.getBrowsingHistoryByMember(mb, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取会员浏览记录成功");
		}catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取会员浏览记录错误");
		}
		return msg;
	}
	/**
	 * 增加会员浏览记录
	 * **/
	@RequestMapping("addBrowsingHistory")
	@ResponseBody
	public ResultApp getClickCollection(MemberBrowsing mb){
		ResultApp msg = new ResultApp();
		try {
			MemberBrowsing m=appUserService.checkBrowsingHistoryByMember(mb);
			if(StringUtil.isEmpty(m)){
				appUserService.addBrowsingHistory(mb);
			}else{
				appUserService.updateBrowsingHistory(m);
			}
			msg.setSuccess(true);
			msg.setMsg("增加浏览记录成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("增加浏览记录失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：login<br>
	 * 方法说明: 登录app <br>
	 * 创建人: pfwang<br>
	 * 创建日期: 2016-11-1<br>
	 * 
	 * @param login_name
	 *            登录帐号
	 * @param password
	 *            密码
	 * @param phonecode
	 *            SIM卡串码
	 * @param deviceid
	 *            手机串码
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public ResultApp login(String login_name, String password, String phonecode, String deviceid) {
		ResultApp msg = new ResultApp();
		try {
			msg = new ResultApp("{}", "登录成功", true);
		} catch (Exception e) {
			msg = new ResultApp(null, "登录失败", false);
			LOG4J.error("登录系统错误!", e);
		}
		return msg;
	}

	/**
	 * 
	 * 方法名称：getSysDictDetailList<br>
	 * 方法说明: 获取数字字典<br>
	 * 创建人: pfwang<br>
	 * 创建日期: 2017-12-27<br>
	 * 
	 * @param login_name
	 * @param auditSwitch
	 * @return
	 */
	@RequestMapping("getSysDictDetailList")
	@ResponseBody
	public ResultApp getSysDictDetailList(DictDetail detail) {
		ResultApp msg = new ResultApp();
		try {
			detail.setState("1");
			List<DictDetail> lis = sysSerivce.getDictDetailList(detail);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取字典数据成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取字典数据错误");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}

	@RequestMapping("getAppResourcesConfig")
	@ResponseBody
	public ResultApp getAppResourcesConfig(String page){
		ResultApp msg = new ResultApp();
		try {
			AppResourcesConfig obj = new AppResourcesConfig();
			obj.setPage(page);
			obj.setStatus(1);
			List<AppResourcesConfig> lis = appResourcesConfigService.query(obj, 0, 10, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取字典数据成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取字典数据错误");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}
	
	
	/**
	 * 
	 * 方法名称：getAppVersion<br>
	 * 方法说明: 获取版本信息<br>
	 * 创建人: pfwang<br>
	 * 创建日期: 2018-1-9<br>
	 * 
	 * @return
	 */
	@RequestMapping("getAppVersion")
	@ResponseBody
	public ResultApp getAppVersion() {
		ResultApp msg = new ResultApp();
		try {
			AppVersion av = appVersionService.getAppVersion();
			msg.setPageData(av);
			msg.setSuccess(true);
			msg.setMsg("获取版本信息成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取版本信息错误");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}
}

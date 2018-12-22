package com.surpass.mvc.system.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.sys.model.LoginAccount;
import com.surpass.mvc.system.sys.service.AccountLoginService;
import com.surpass.utils.Constants;
import com.surpass.utils.MD5Util;
import com.surpass.utils.ResultMsg;

/**
 * 
 * 类名称： LoginController<br>
 * 类描述： 系统用户登录Controller<br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-12-12<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-12-12
 */
@Controller
@RequestMapping("system/sys/loginController")
public class LoginController extends BaseController {
	private static final Logger log4j = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AccountLoginService accountLoginService;

	/**
	 * 
	 * 方法名称：login<br>
	 * 方法说明: 用户登录<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param account
	 * @param password
	 * @param user_type
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public ResultMsg login(String account, String password) {
		ResultMsg msg = new ResultMsg();
		try {
			LoginAccount loginUser = accountLoginService.login(account, MD5Util.string2MD5(password), Constants.userType.sysUser);
			if (null != loginUser) {
				if (Constants.state.disable.equals(loginUser.getState())) {
					msg.setExecute(false);
					msg.setMsg("该帐号已被禁用,请联系管理员!");
				} else {
					request.getSession().setAttribute(Constants.session.LOGIN_USER, loginUser);
					msg.setExecute(true);
					msg.setMsg("登录系统成功!");
				}
			} else {
				msg.setExecute(false);
				msg.setMsg("用户名或密码不正确!");
			}
			logWriteInfo(Constants.log.YWMC.SYS, Constants.log.CZLX.LOGIN, account, msg);
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("登录系统失败!" + e.getMessage());
			log4j.error("登录系统错误!", e);
		}
		return msg;
	}

	/**
	 * 
	 * 方法名称：logout<br>
	 * 方法说明: 用户退出<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @return
	 */
	@RequestMapping("logout")
	public String logout() {
		LoginAccount loginUser = getLoginUser();
		ResultMsg msg = new ResultMsg();
		try {
			msg.setExecute(true);
			msg.setMsg("退出系统成功!");
			logWriteInfo(Constants.log.YWMC.SYS, Constants.log.CZLX.LOGOUT, loginUser.getUserId(), msg);
			request.getSession().removeAttribute(Constants.session.LOGIN_USER);
		} catch (Exception e) {
			log4j.error("退出系统失败!", e);
		}

		return Constants.path.RETURN_PATH_SYSTEM + "login.jsp";
	}
	
	

	/**
	 * 
	 * 方法名称：queryMain<br>
	 * 方法说明:系统后台首页<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @return
	 */
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "index.jsp";
	}

	/**
	 * 
	 * 方法名称：loginPage<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @return
	 */
	@RequestMapping("loginPage")
	public String loginPage() {
		return Constants.path.RETURN_PATH_SYSTEM + "login.jsp";
	}
}

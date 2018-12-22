package com.surpass.mvc.system.sys.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.model.FrmAccount;
import com.surpass.mvc.system.sys.model.SysUser;
import com.surpass.mvc.system.sys.model.SysUserAccount;
import com.surpass.mvc.system.sys.service.DepartmentService;
import com.surpass.mvc.system.sys.service.RoleService;
import com.surpass.mvc.system.sys.service.SysUserService;
import com.surpass.utils.Constants;
import com.surpass.utils.MD5Util;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

/**
 * 
 * 类名称： SysUserController<br>
 * 类描述： 系统用户管理<br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-12-16<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-12-16
 */
@Controller
@RequestMapping("system/sys/sysUserController")
public class SysUserController extends BaseController {
	private static final Logger log4j = LoggerFactory.getLogger(SysUserController.class);
	@Autowired
	private SysUserService service;

	@Autowired
	private RoleService roleService;
	@Autowired
	private DepartmentService departmentService;

	/**
	 * 
	 * 方法名称：queryMain<br>
	 * 方法说明:页面跳转 方法名称 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @return
	 */
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "sys/sysUser.jsp";
	}

	/***
	 * 
	 * 方法名称：getUserGridStore<br>
	 * 方法说明: 获取用户分页数据<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-16<br>
	 * 
	 * @param user
	 * @param start
	 * @param limit
	 * @return
	 */
	@RequestMapping("getUserGridStore")
	@ResponseBody
	public ResultGridStore getUserGridStore(SysUserAccount user, Integer start, Integer limit) {
		String orderBy = "";
		if (StringUtil.isEmpty(user.getDeptText())) {
			getLoginUser();
			user.setDeptText(departmentService.getDepartmentById(
					service.selectSysUserAccountById(getLoginUser().getAccountUserId()).getDept_id().toString()).getPath());
		}
		return service.getUserGridStore(user, start, limit, orderBy);
	}

	/**
	 * 
	 * 方法名称：saveOrUpdateUser<br>
	 * 方法说明: 保存用户<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-19<br>
	 * 
	 * @param sysUser
	 * @param isAdd
	 * @return
	 */
	@RequestMapping("saveOrUpdateUser")
	@ResponseBody
	public ResultMsg saveOrUpdateUser(SysUser sysUser, FrmAccount account, boolean isAdd) {
		ResultMsg msg = new ResultMsg();
		FrmAccount tempAccount = service.findAccount(account.getAccount());
		if (isAdd) { // 添加
			if (null == tempAccount) {
				try {
					account.setPassword(MD5Util.string2MD5(account.getPassword()));
					sysUser.setC_time(new Date());
					service.insert(sysUser, account);
					msg.setExecute(true);
					msg.setMsg("用户增加成功！");
				} catch (Exception e) {
					msg.setExecute(false);
					msg.setMsg("用户增加失败！");
					log4j.error("增加用户信息失败!", e);
				}
			} else {
				msg.setExecute(false);
				msg.setMsg("该帐号已经存在！");
			}
		} else { // 修改
			try {
				if (null != tempAccount.getPassword()) {
					if (!tempAccount.getPassword().equals(account.getPassword())) {
						account.setPassword(MD5Util.string2MD5(account.getPassword()));
					}
				} else {
					if (!"".equals(account.getPassword())) {
						account.setPassword(MD5Util.string2MD5(account.getPassword()));
					}
				}
				service.updateSysUser(sysUser, account);
				msg.setExecute(true);
				msg.setMsg("用户修改成功！");
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("用户修改失败！");
				log4j.error("修改用户信息失败!", e);
			}
		}
		return msg;
	}

	/***
	 * 
	 * 方法名称：changeUserState<br>
	 * 方法说明: 更改用户状态<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-22<br>
	 * 
	 * @param userIds
	 * @param state
	 * @return
	 */
	@RequestMapping("changeUserState")
	@ResponseBody
	public ResultMsg changeUserState(String userIds, String state) {
		ResultMsg msg = new ResultMsg();
		if (StringUtil.isNotEmpty(userIds) && StringUtil.isNotEmpty(state)) {
			try {
				service.changeUserState(userIds, state);
				msg.setExecute(true);
				msg.setMsg("用户状态修改成功！");
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("用户状态修改失败！");
				log4j.error("用户状态修改失败!", e);
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败！");
			log4j.error("数据传输失败!", new RuntimeException("数据传输失败！"));
		}
		return msg;
	}

	/**
	 * 
	 * 方法名称：getUserOfRole<br>
	 * 方法说明:获取用户所赋予的角色 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-26<br>
	 * 
	 * @param user_id
	 * @return
	 */
	@RequestMapping("getUserOfRole")
	@ResponseBody
	public List<ComboBox> getUserOfRole(String user_id) {
		return roleService.getUserOfRole(user_id, Constants.userType.sysUser);
	}

	/***
	 * 
	 * 方法名称：getNoUserOfRole<br>
	 * 方法说明: 获取用户没有的角色<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-26<br>
	 * 
	 * @param user_id
	 * @return
	 */
	@RequestMapping("getNoUserOfRole")
	@ResponseBody
	public List<ComboBox> getNoUserOfRole(String user_id) {
		return roleService.getNoUserOfRole(user_id, Constants.userType.sysUser);
	}

	@RequestMapping("changeUserRole")
	@ResponseBody
	public ResultMsg changeUserRole(String user_id, String addIds, String delIds) {
		ResultMsg msg = new ResultMsg();
		if (StringUtil.isNotEmpty(user_id)) {
			try {
				roleService.changeUserRole(user_id, addIds, delIds);
				msg.setExecute(true);
				msg.setMsg("角色分配成功!");
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("角色分配失败!");
				log4j.error("角色分配失败!", e);
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败!");
		}
		return msg;
	}
}

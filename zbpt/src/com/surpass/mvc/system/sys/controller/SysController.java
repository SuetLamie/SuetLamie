package com.surpass.mvc.system.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.model.SysMenu;
import com.surpass.mvc.system.sys.model.SysUserAccount;
import com.surpass.mvc.system.sys.service.DepartmentService;
import com.surpass.mvc.system.sys.service.SysService;
import com.surpass.mvc.system.sys.service.SysUserService;

/**
 * 
 * 类名称： SysController<br>
 * 类描述： 系统信息Controller<br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-4-5<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-4-5
 */
@Controller
@RequestMapping("system/sys/sysController")
public class SysController extends BaseController {

	private static final Logger log4j = LoggerFactory.getLogger(SysController.class);

	@Autowired
	private SysService service;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private DepartmentService departmentService;

	/**
	 * 
	 * 方法名称：getMenuTreeList<br>
	 * 方法说明: 获取菜单信息<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-4-5<br>
	 * 
	 * @return
	 */
	@RequestMapping("getMenuTreeList")
	@ResponseBody
	public List<SysMenu> getMenuTreeList() {
		List<SysMenu> list = null;
		try {
			Map<String, String> setMap = new HashMap<String, String>();
			setMap.put("node", getParameter("node", "PC"));
			setMap.put("user_id", getLoginUser().getUserId());
			setMap.put("user_type", getLoginUser().getUserType().toString());
			list = service.getMenuTreeList(setMap);
		} catch (Exception e) {
			log4j.error("获取菜单信息失败!", e);
		}
		return list;
	}

	/**
	 * 
	 * 方法名称：getSysInfo<br>
	 * 方法说明: 获取系统信息<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-4-5<br>
	 * 
	 * @return
	 */
	@RequestMapping("getSysInfo")
	@ResponseBody
	public Map<String, Object> getSysInfo() {
		Map<String, Object> dataMap = null;
		try {
			dataMap = new HashMap<String, Object>();
			SysUserAccount loginUser = sysUserService.selectSysUserAccountById(getLoginUser().getAccountUserId());
			loginUser.setPassword("");
			dataMap.put("loginUser", loginUser);
		} catch (Exception e) {
			log4j.error("获取系统信息!", e);
		}
		return dataMap;
	}

	/**
	 * 
	 * 方法名称：getSysPower<br>
	 * 方法说明: 获取按钮权限<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("getSysPower")
	@ResponseBody
	public Map getSysPower() {
		Map map = new HashMap();
		try {
			String user_id = getLoginUser().getUserId();
			String modeIds = service.getSysPower(user_id);
			map.put("success", true);
			map.put("powerIds", modeIds);
			System.out.println(modeIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 
	 * 方法名称：getOrgComboBoxTree<br>
	 * 方法说明: 获取组织树<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("getOrgComboBoxTree")
	@ResponseBody
	public List getOrgComboBoxTree() {
		List list = null;
		try {
			Map setMap = new HashMap();
			setMap.put("node", getParameter("node", "0"));
			// list = service.getOrgComboBoxTree(setMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 方法名称：getDictList<br>
	 * 方法说明: 获取数据字典的列表<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2015-12-8<br>
	 * 
	 * @return
	 */
	@RequestMapping("getDictList")
	@ResponseBody
	public List<ComboBox> getDictList() {
		List<ComboBox> list = null;
		try {
			JSONObject jsonData = JSONObject.fromObject(request.getParameter("jsonData"));
			list = service.getDictList(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}

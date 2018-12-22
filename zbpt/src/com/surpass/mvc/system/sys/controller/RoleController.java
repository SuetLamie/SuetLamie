package com.surpass.mvc.system.sys.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.sys.model.Role;
import com.surpass.mvc.system.sys.model.SysModule;
import com.surpass.mvc.system.sys.model.SysUser;
import com.surpass.mvc.system.sys.service.DepartmentService;
import com.surpass.mvc.system.sys.service.RoleService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

/**
 * 
 * 类名称： RoleController<br>
 * 类描述：角色管理Controller <br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-3-22<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-3-22
 */
@Controller
@RequestMapping("system/sys/roleController")
public class RoleController extends BaseController {

	private static final Logger log4j = LoggerFactory.getLogger(RoleController.class);

	/**
	 * 角色service
	 */
	@Autowired
	private RoleService service;

	@Autowired
	private DepartmentService dempService;
	/**
	 * 
	 * 方法名称：queryMain<br>
	 * 方法说明:页面跳转 方法名称 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * @return
	 */
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "sys/role.jsp";
	}

	/**
	 * 
	 * 方法名称：getRoleGridStore<br>
	 * 方法说明: 获取角色列表<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-22<br>
	 * 
	 * @return
	 */
	@RequestMapping("getRoleGridStore")
	@ResponseBody
	public ResultGridStore getRoleGridStore(Role role, Integer start, Integer limit) {
		String orderBy = "";
		return service.getRoleGridStore(role, start, limit, orderBy);
	}

	/**
	 * 
	 * 方法名称：saveOrUpdateRole<br>
	 * 方法说明:修改或增加角色 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-22<br>
	 * 
	 * @param rl
	 * @return
	 */
	@RequestMapping("saveOrUpdateRole")
	@ResponseBody
	public ResultMsg saveOrUpdateRole(@ModelAttribute(value = "rl") Role rl) {
		ResultMsg msg = new ResultMsg();
		if (!service.verifyRoleCode(rl.getR_code(), rl.getR_id())) {
			msg.setExecute(false);
			msg.setMsg("该角色代码已经存在,请另输入！");
		} else {
			if (StringUtil.isEmpty(rl.getR_id())) {
				try {
					service.insertRole(rl);
					msg.setExecute(true);
					msg.setMsg("角色增加成功！");
				} catch (Exception e) {
					msg.setExecute(false);
					msg.setMsg("角色增加失败！");
					log4j.error("增加角色信息失败!", e);
				}
				logWriteInfo(Constants.log.YWMC.ROLE, Constants.log.CZLX.INSERT, rl.getR_id(), msg);
			} else {
				try {
					service.updateRole(rl);
					msg.setExecute(true);
					msg.setMsg("角色修改成功！");
				} catch (Exception e) {
					msg.setExecute(false);
					msg.setMsg("角色修改失败！");
					log4j.error("修改角色信息失败!", e);
				}
				logWriteInfo(Constants.log.YWMC.ROLE, Constants.log.CZLX.UPDATE, rl.getR_id(), msg);
			}
		}

		return msg;
	}

	/**
	 * 
	 * 方法名称：deleteRole<br>
	 * 方法说明: 删除角色<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-22<br>
	 * 
	 * @return 角色 id 多个角色用逗号隔开
	 */
	@RequestMapping("deleteRole")
	@ResponseBody
	public ResultMsg deleteRole(String roleIds) {
		ResultMsg msg = new ResultMsg();
		if (StringUtil.isNotEmpty(roleIds)) {
			try {
				int errorCode = service.verifyDeleteRole(roleIds);
				if (errorCode < Constants.numberInt.ZERO_INTEGER) {
					msg.setExecute(false);
					msg.setMsg("该角色被引用 不能被删除!");
				} else {
					int row = service.deleteRole(roleIds);
					if (row > 0) {
						msg.setExecute(true);
						msg.setMsg("删除角色成功!");
					} else {  
						msg.setExecute(false);
						msg.setMsg("删除角色失败!");
					}
				}
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除角色失败!");
				log4j.error("删除角色信息失败!", e);
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败!");
		}
		logWriteInfo(Constants.log.YWMC.ROLE, Constants.log.CZLX.DELETE, roleIds, msg);
		return msg;
	}
	
	
	

	/**
	 * 
	 * 方法名称：getRoleModuleTree<br>
	 * 方法说明: 获取模块树数据<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-4-1<br>
	 * 
	 * @param roleId
	 * @param node
	 * @return
	 */
	@RequestMapping("getRoleModuleTree")
	@ResponseBody
	public List<SysModule> getRoleModuleTree(String roleId, String node) {
		return service.getRoleModuleTree(roleId, node);
	}

	/**
	 * 
	 * 方法名称：changeRoleModule<br>
	 * 方法说明: 为角色分配模块<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param roleId
	 *            角色id
	 * @param delId
	 *            删除模块id
	 * @param addId
	 *            新增的模块Id
	 * @return
	 */
	@RequestMapping("changeRoleModule")
	@ResponseBody
	public ResultMsg changeRoleModule(String roleId, String delId, String addId) {
		ResultMsg msg = new ResultMsg();
		try {
			service.changeRoleModule(roleId, delId, addId);
			msg.setExecute(true);
			msg.setMsg("为角色分配模块权限成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("为角色分配模块权限失败!");
			log4j.error("为角色分配模块权限失败!", e);
		}
		return msg;
	}

	/**
	 * 
	 * 方法名称：getDfpRoleUserGridStore<br>
	 * 方法说明:获取该角色待分配用户<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-11-17<br>
	 * 
	 * @param roleId
	 * @param delId
	 * @param addId
	 * @return
	 */	 
	 @RequestMapping("getDfpRoleUserGridStore")
	 @ResponseBody
	 public ResultGridStore getDfpRoleUserGridStore(String roleId, SysUser user, Integer start, Integer limit) {
		 if(getParameter("bmdm") != null && !"".equals(getParameter("bmdm"))){
			 try {
				user.setDept_id(dempService.getDepartmentByPath(getParameter("bmdm").toString()).getBmbh());
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 if(StringUtil.isEmpty(user.getEmp_id())){
			 user.setEmp_id(getParameter("emp_id")); 
		 }	
		 return service.getRoleUserGridStore(start, limit, roleId, user,false);
	 }


	/**
	 * 
	 * 方法名称：getYjfpRoleUserGridStore<br>
	 * 方法说明: 已经分配给的用户<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-11-17<br>
	 * 
	 * @param roleId
	 * @param basPolice
	 * @param start
	 * @param limit
	 * @return
	 */
	 @RequestMapping("getYjfpRoleUserGridStore")
	 @ResponseBody
	 public ResultGridStore getYjfpRoleUserGridStore(String roleId, SysUser user, Integer start, Integer limit) {
		 if(getParameter("bmdm") != null && !"".equals(getParameter("bmdm"))){
			 try {
				user.setDept_id(dempService.getDepartmentByPath(getParameter("bmdm").toString()).getBmbh());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 if(StringUtil.isEmpty(user.getEmp_id())){
			 user.setEmp_id(getParameter("emp_id")); 
		 }	 
		 return service.getRoleUserGridStore(start, limit, roleId, user,true);
	 }

	/**
	 * 
	 * 方法名称：changeRoleUsers<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-11-17<br>
	 * 
	 * @param ids
	 * @param roleId
	 * @param contrl
	 * @return
	 */
	@RequestMapping("changeRoleUsers")
	@ResponseBody
	public ResultMsg changeRoleUsers(String ids, String roleId, String contrl) {
		ResultMsg msg = new ResultMsg();
		try {
			service.changeRoleUsers(ids, roleId, contrl);
			msg.setExecute(true);
			msg.setMsg("为角色分配用户权限成功!");
		} catch (Exception e) {
			msg.setExecute(false);
			msg.setMsg("为角色分配用户权限失败!");
			log4j.error("为角色分配模块权限失败!", e);
		}
		return msg;
	}
	
	/**
	 */
	@RequestMapping("getRoleDepartName")
	@ResponseBody
	public ResultMsg getRoleDepartName(@RequestParam("r_name")String r_name){
		ResultMsg msg = new ResultMsg();
		
		Integer count = null;
		try {
			count = service.getRoleCount()+1;
			String departname = service.getRoleDepartName(r_name);
			msg.setMsg(departname+"_"+count.toString());
			msg.setExecute(true);
		} catch (Exception e) {
			msg.setMsg( "_"+count.toString());
			msg.setExecute(false);
		} 
		return msg;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

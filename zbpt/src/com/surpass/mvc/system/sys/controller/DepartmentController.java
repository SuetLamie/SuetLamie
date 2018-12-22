package com.surpass.mvc.system.sys.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.core.bean.TreeNode;
import com.surpass.mvc.system.sys.bean.DepartmentTree;
import com.surpass.mvc.system.sys.model.Department;
import com.surpass.mvc.system.sys.service.DepartmentService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

/**
 * 
 * 类名称： DepartmentController<br>
 * 类描述： 部门Controller<br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-12-27<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-12-27
 */
@Controller
@RequestMapping("system/sys/departmentController")
public class DepartmentController extends BaseController {

	private static final Logger log4j = LoggerFactory.getLogger(DepartmentController.class);

	/**
	 * 部门service
	 */
	@Autowired
	private DepartmentService service;

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
		return Constants.path.RETURN_PATH_SYSTEM + "sys/department.jsp";
	}

	@RequestMapping("getDepartmentTreeData")
	@ResponseBody
	public List<DepartmentTree> getDepartmentTreeData(Department department, String node) {
		if (!"xnode-11".equals(node)) {
			department.setBmbh(Integer.valueOf(node));
		}
		return service.getDepartmentTreeData(department, getLoginUser());
	}

	@RequestMapping("getDepartmentTreeNode")
	@ResponseBody
	public List<TreeNode> getDepartmentTreeNode(String node) {
		return service.getDepartmentTreeNode(node);
	}

	@RequestMapping("getDepartmentById")
	@ResponseBody
	public Department getDepartmentById(String id) {
		return service.getDepartmentById(id);
	}

	@RequestMapping("saveOrUpdateDepartment")
	@ResponseBody
	public ResultMsg saveOrUpdateDepartment(Department department, boolean isAdd) {
		ResultMsg msg = new ResultMsg();
		if (isAdd) { // 添加
			try {
				service.insertDepartment(department);
				msg.setExecute(true);
				msg.setMsg("部门增加成功！");
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("部门增加失败！");
				log4j.error("增加部门信息失败!", e);
			}
		} else { // 修改
			try {
				service.updateDepartment(department);
				msg.setExecute(true);
				msg.setMsg("部门修改成功！");
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("部门修改失败！");
				log4j.error("修改部门信息失败!", e);
			}
		}
		return msg;
	}

	@RequestMapping("deleteDepartment")
	@ResponseBody
	public ResultMsg deleteDepartment(String id) {
		ResultMsg msg = new ResultMsg();
		if (StringUtil.isNotEmpty(id)) {
			try {
				service.deleteDepartment(Integer.valueOf(id));
				msg.setExecute(true);
				msg.setMsg("删除部门成功！");
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除部门失败!");
				log4j.error("删除部门信息失败!", e);
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败!");
		}
		logWriteInfo(Constants.log.YWMC.ROLE, Constants.log.CZLX.DELETE, id, msg);
		return msg;
	}

}

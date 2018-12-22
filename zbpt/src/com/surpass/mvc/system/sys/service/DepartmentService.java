package com.surpass.mvc.system.sys.service;

import java.util.List;

import com.surpass.mvc.core.bean.TreeNode;
import com.surpass.mvc.system.sys.bean.DepartmentTree;
import com.surpass.mvc.system.sys.model.Department;
import com.surpass.mvc.system.sys.model.LoginAccount;

public interface DepartmentService {

	List<DepartmentTree> getDepartmentTreeData(Department department, LoginAccount loginUser);

	List<TreeNode> getDepartmentTreeNode(String node);

	Department getDepartmentById(String id);

	void updateDepartment(Department department) throws Exception;

	void insertDepartment(Department department) throws Exception;

	void deleteDepartment(Integer bmbh) throws Exception;

	Department getDepartmentByPath(String path)throws Exception;

	/**
	 * 方法名称：selectdeptMc<br>
	 * 方法说明: <br>
	 * 创建人: wangli<br>
	 * 创建日期: 2017-5-24<br>
	 * @param path
	 * @return
	 */
	String selectdeptId(String path);

}

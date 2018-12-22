package com.surpass.mvc.system.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.bean.TreeNode;
import com.surpass.mvc.system.sys.bean.DepartmentTree;
import com.surpass.mvc.system.sys.dao.DepartmentMapper;
import com.surpass.mvc.system.sys.dao.SysUserMapper;
import com.surpass.mvc.system.sys.model.Department;
import com.surpass.mvc.system.sys.model.LoginAccount;
import com.surpass.mvc.system.sys.service.DepartmentService;

@Service("departmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentMapper dao;
	@Autowired
	private SysUserMapper userMapper;

	@Override
	public List<DepartmentTree> getDepartmentTreeData(Department department, LoginAccount loginUser) {
		try {
			if (null == department.getBmbh()) {
				return dao.getDepartmentTreeById(userMapper.selectByPrimaryKey(Integer.valueOf(loginUser.getAccountUserId())).getDept_id());
			} else {
				return dao.getDepartmentTreeData(department);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TreeNode> getDepartmentTreeNode(String node) {
		try {
			return dao.getDepartmentTreeNode(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Department getDepartmentById(String id) {
		return dao.selectByPrimaryKey(Integer.valueOf(id));
	}

	@Override
	public void updateDepartment(Department department) throws Exception {
		try {
			dao.updateByPrimaryKeySelective(department);
			Department sjbmBean = dao.selectByPrimaryKey(department.getSjbm());
			Department upPathBean = new Department();
			upPathBean.setBmbh(department.getBmbh());
			upPathBean.setPath(sjbmBean.getPath() + "_" + department.getBmbh());
			dao.updateByPrimaryKeySelective(upPathBean);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void insertDepartment(Department department) throws Exception {
		try {
			department.setC_time(new Date());
			department.setPath("");
			department.setIs_del("0");
			dao.insertSelective(department);
			Department sjbmBean = dao.selectByPrimaryKey(department.getSjbm());
			Department upPathBean = new Department();
			upPathBean.setBmbh(department.getBmbh());
			upPathBean.setPath(sjbmBean.getPath() + "_" + department.getBmbh());
			dao.updateByPrimaryKeySelective(upPathBean);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteDepartment(Integer bmbh) throws Exception {
		try {
			Department upDepartment = new Department();
			upDepartment.setBmbh(bmbh);
			upDepartment.setIs_del("1");
			dao.updateByPrimaryKeySelective(upDepartment);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Department getDepartmentByPath(String path) {
		return dao.selectByPath(path);
	}
	
	@Override
	public String selectdeptId(String path) {
		return dao.selectdeptId(path);
		
	}

}

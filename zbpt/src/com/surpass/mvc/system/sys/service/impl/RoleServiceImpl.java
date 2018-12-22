package com.surpass.mvc.system.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.dao.RoleMapper;
import com.surpass.mvc.system.sys.model.Role;
import com.surpass.mvc.system.sys.model.SysModule;
import com.surpass.mvc.system.sys.model.SysRoleModule;
import com.surpass.mvc.system.sys.model.SysUser;
import com.surpass.mvc.system.sys.service.RoleService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.StringUtil;

/**
 * 
 * 类名称： RoleServiceImpl<br>
 * 类描述：角色service 实现类 <br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-3-22<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-3-22
 */
@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {
	/**
	 * 角色 dao接口
	 */
	@Autowired
	private RoleMapper dao;
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;

	@Override
	public ResultGridStore getRoleGridStore(Role role, int start, int limit, String orderBy) {
		ResultGridStore rg = new ResultGridStore();
		try {
			rg.setList(dao.query(parametersMapBuilder.build(start, limit, orderBy, role)));
			rg.setTotalProperty(dao.total(role));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	@Override
	public void insertRole(Role rl) throws Exception {
		dao.insert(rl);
	}

	@Override
	public void updateRole(Role rl) throws Exception {
		dao.updateByPrimaryKey(rl);
	}

	@Override
	public int deleteRole(String roleIds) throws Exception {

		dao.deleteRoleModule(roleIds);

		dao.deleteUserRole(roleIds);

		return dao.deleteByPrimaryKey(roleIds);
	}

	@Override
	public List<SysModule> getRoleModuleTree(String role_id, String parentId) {
		return dao.getRoleModuleTree(role_id, parentId);
	}

	@Override
	public void changeRoleModule(String roleId, String delId, String addId) throws Exception {
		if (StringUtil.isNotEmpty(delId)) {
			Map<String, String> paramMap = new HashMap<String, String>();
			delId = "'" + delId.replaceAll(",", "','") + "'";
			// 新增里有不变的怕重复添加ztree
			if (StringUtil.isNotEmpty(addId)) {
				delId += ",'" + addId.replaceAll(",", "','") + "'";
			}
			paramMap.put("roleId", roleId);
			paramMap.put("delId", delId);
			dao.deleteRoleModuleByRoleId(paramMap);
		}
		if (StringUtil.isNotEmpty(addId)) {
			List<SysRoleModule> list = new ArrayList<SysRoleModule>();
			String[] arrStrings = addId.split(",");
			for (String mid : arrStrings) {
				SysRoleModule srm = new SysRoleModule();
				srm.setM_id(mid);
				srm.setR_id(roleId);
				list.add(srm);
			}
			if (list.size() > Constants.numberInt.ZERO_INTEGER) {
				dao.insertRoleModule(list);
			}
		}
	}

	@Override
	public boolean verifyRoleCode(String role_code, String role_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("r_code", role_code);
		map.put("r_id", role_id);
		return dao.verifyRoleCode(map) > 0 ? false : true;
	}

	@Override
	public int verifyDeleteRole(String roleIds) {
		int errorCode = Constants.numberInt.ZERO_INTEGER;
		int count = dao.totalUserRole(roleIds);
		if (count > 0) {
			errorCode = Constants.numberInt.MINUS_ONE_INTEGER;
		} else {

		}
		return errorCode;
	}

	@Override
	public ResultGridStore getRoleUserGridStore(Integer start, Integer limit, String roleId, SysUser user, Boolean isDfp) {
		ResultGridStore rg = new ResultGridStore();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("dept_id", user.getDept_id());
			map.put("emp_id", user.getEmp_id());
			map.put("isDfp", isDfp);
			map.put("start", start);
			map.put("limit", limit);
			rg.setList(dao.getRoleUserGridStoreQuery(map));
			rg.setTotalProperty(dao.getRoleUserGridStoreTotal(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	@Override
	public void changeRoleUsers(String ids, String roleId, String contrl) throws Exception {
		try {
			if ("add".equals(contrl)) {
				// 增加
				dao.grantRoleToUsers(ids.split(","), roleId);
			} else {
				// 删除
				dao.deleteUsersFromRole(ids.split(","), roleId);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ComboBox> getNoUserOfRole(String user_id, int sysType) {
		return dao.getNoUserOfRole(user_id, sysType);
	}

	@Override
	public List<ComboBox> getUserOfRole(String user_id, int sysType) {
		return dao.getUserOfRole(user_id, sysType);
	}

	@Override
	public void changeUserRole(String user_id, String addIds, String delIds) throws Exception {
		try {
			if (StringUtil.isNotEmpty(addIds)) {
				dao.addUserOfRole(user_id, addIds.split(","));
			}
			if (StringUtil.isNotEmpty(delIds)) {
				dao.deleteUserOfRole(user_id, delIds.split(","));
			}
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public String getRoleDepartName(String role_name) throws Exception {

		return dao.getRoleDepartName(role_name);
	}

	@Override
	public int getRoleCount() throws Exception {
		return dao.getRoleCount();
	}

}

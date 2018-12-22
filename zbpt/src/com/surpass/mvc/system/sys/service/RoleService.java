package com.surpass.mvc.system.sys.service;

import java.util.List;

import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.model.Role;
import com.surpass.mvc.system.sys.model.SysModule;
import com.surpass.mvc.system.sys.model.SysUser;
import com.surpass.utils.ResultGridStore;

/**
 * 
 * 类名称： RoleService<br>
 * 类描述：角色service接口 <br>
 * 创建人： 方曙强<br>
 * 创建时间：2016-3-22<br>
 * 修改人： 方曙强<br>
 * 修改时间：2016-3-22
 */
public interface RoleService {
	/**
	 * 
	 * 方法名称：getRoleGridStore<br>
	 * 方法说明: 获取 角色 列表信息<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-22<br>
	 * 
	 * @param setMap
	 * @return
	 */
	ResultGridStore getRoleGridStore(Role role, int start, int limit, String orderBy);

	/**
	 * 
	 * 方法名称：verifyRoleCode<br>
	 * 方法说明: 角色code 唯一 验证<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-28<br>
	 * 
	 * @param role_code
	 * @param role_id
	 * @return
	 */
	boolean verifyRoleCode(String role_code, String role_id);

	/**
	 * 
	 * 方法名称：insertRole<br>
	 * 方法说明: 添加 角色 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-28<br>
	 * 
	 * @param rl
	 * @throws Exception
	 */
	void insertRole(Role rl) throws Exception;

	/**
	 * 
	 * 方法名称：updateRole<br>
	 * 方法说明: 修改 角色<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-28<br>
	 * 
	 * @param rl
	 * @throws Exception
	 */
	void updateRole(Role rl) throws Exception;

	/***
	 * 
	 * 方法名称：deleteRole<br>
	 * 方法说明: 批量删除角色验证 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-28<br>
	 * 
	 * @param roleIds
	 *            多个角色 之间 用 逗号隔开
	 * @return 0 正常 <br/>
	 *         1 错误 该角色被引用 不能被删除
	 * @throws Exception
	 */
	int verifyDeleteRole(String roleIds);

	/***
	 * 
	 * 方法名称：deleteRole<br>
	 * 方法说明: 批量删除角色 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-28<br>
	 * 
	 * @param roleIds
	 *            多个角色 之间 用 逗号隔开
	 * @return
	 * @throws Exception
	 */
	int deleteRole(String roleIds) throws Exception;

	/***
	 * 
	 * 方法名称：getRoleModuleTree<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param role_id
	 * @param parentId
	 * @return
	 */
	List<SysModule> getRoleModuleTree(String role_id, String parentId);

	/**
	 * 
	 * 方法名称：changeRoleModule<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param roleId
	 * @param delId
	 * @param addId
	 * @throws Exception
	 */
	void changeRoleModule(String roleId, String delId, String addId) throws Exception;

	/**
	 * 
	 * 方法名称：getRoleUserGridStore<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-11-17<br>
	 * 
	 * @param start
	 * @param limit
	 * @param roleId
	 * @param basPolice
	 * @param isDfp
	 * @return
	 */
	 ResultGridStore getRoleUserGridStore(Integer start, Integer limit, String roleId, SysUser user, Boolean isDfp);

	void changeRoleUsers(String ids, String roleId, String contrl) throws Exception;

	/***
	 * 
	 * 方法名称：getNoUserOfRole<br>
	 * 方法说明:获取用户没有赋予的角色列表 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-26<br>
	 * 
	 * @param user_id
	 * @param sysuser
	 * @return
	 */
	List<ComboBox> getNoUserOfRole(String user_id, int sysType);

	/***
	 * 
	 * 方法名称：getUserOfRole<br>
	 * 方法说明:获取用户已经赋予的角色 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-26<br>
	 * 
	 * @param user_id
	 * @param sysuser
	 * @return
	 */
	List<ComboBox> getUserOfRole(String user_id, int sysType);

	/**
	 * 
	 * 方法名称：changeUserRole<br>
	 * 方法说明:更改用户角色 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-26<br>
	 * 
	 * @param user_id
	 * @param addIds
	 * @param delIds
	 * @param sysuser
	 */
	void changeUserRole(String user_id, String addIds, String delIds) throws Exception;
	
	/**
	 * 
	 * 方法名称：getRoleDepartName<br>
	 * 方法说明: 通过role_id获取部门名称<br>
	 * 创建人: 侯智<br>
	 * 创建日期: 2017-5-10<br>
	 * @param role_id
	 * @return
	 */
	
	String getRoleDepartName (String role_name) throws Exception;
	
	/**
	 * 
	 * 方法名称：getRoleCount<br>
	 * 方法说明: <br>
	 * 创建人: 侯智<br>
	 * 创建日期: 2017-5-10<br>
	 * @param role_name
	 * @return
	 */
	
	int getRoleCount () throws Exception;

}

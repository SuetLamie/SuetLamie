package com.surpass.mvc.system.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.model.Role;
import com.surpass.mvc.system.sys.model.SysModule;

@SuppressWarnings("rawtypes")
public interface RoleMapper {

	/**
	 * 
	 * 方法名称：query<br>
	 * 方法说明: 查询 角色信息 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param build
	 * @return
	 */
	List<Role> query(Map<String, Object> build);

	/**
	 * 
	 * 方法名称：total<br>
	 * 方法说明: 查询 角色信息 记录数<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param role
	 * @return
	 */
	Integer total(Role role);

	/**
	 * 
	 * 方法名称：deleteByPrimaryKey<br>
	 * 方法说明: 删除角色信息<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param r_id
	 * @return
	 */
	int deleteByPrimaryKey(@Param(value = "r_id") String r_id);

	/**
	 * 
	 * 方法名称：insert<br>
	 * 方法说明: 添加 角色信息<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param record
	 * @return
	 */
	int insert(Role record);

	/**
	 * 
	 * 方法名称：updateByPrimaryKey<br>
	 * 方法说明: 修改角色信息<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Role record);

	/**
	 * 
	 * 方法名称：verifyRoleCode<br>
	 * 方法说明: 验证角色code<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param map
	 * @return
	 */
	int verifyRoleCode(Map map);

	/**
	 * 
	 * 方法名称：getRoleModuleTree<br>
	 * 方法说明: 获取角色模块树形数据结构<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param role_id
	 * @param parentId
	 * @return
	 */
	List<SysModule> getRoleModuleTree(@Param(value = "role_id") String role_id, @Param(value = "parentId") String parentId);

	/**
	 * 
	 * 方法名称：insertRoleModule<br>
	 * 方法说明: 添加 角色模块对应关系<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param list
	 * @return
	 */
	
	/**
	 * 
	 * 方法名称：getRoleDepartName<br>
	 * 方法说明: 通过角色id获得部门名称<br>
	 * 创建人: 侯智<br>
	 * 创建日期: 2017-5-10<br>
	 * @param role_name
	 * @return
	 */
	String getRoleDepartName (@Param(value = "role_name") String role_name);
	
	
	
	int insertRoleModule(List list);

	/**
	 * 
	 * 方法名称：deleteRoleModule<br>
	 * 方法说明: 删除角色模块对应关系 (用于分配权限)<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param m_id
	 * @param r_id
	 * @return
	 */
	int deleteRoleModuleByRoleId(Map map);

	/**
	 * 
	 * 方法名称：totalUserRole<br>
	 * 方法说明: 角色被引用记录 条数<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param r_id
	 * @return
	 */
	int totalUserRole(@Param(value = "r_id") String r_id);

	/**
	 * 
	 * 方法名称：deleteRoleModule<br>
	 * 方法说明: 删除角色模块 对应关系<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param r_id
	 * @return
	 */
	int deleteRoleModule(@Param(value = "r_id") String r_id);

	/**
	 * 
	 * 方法名称：deleteUserRole<br>
	 * 方法说明: 删除用户角色 对应关系<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-3-30<br>
	 * 
	 * @param r_id
	 * @return
	 */
	int deleteUserRole(@Param(value = "r_id") String r_id);

	/**
	 * 
	 * 方法名称：getRoleUserGridStoreQuery<br>
	 * 方法说明:获取角色用户 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param build
	 * @return
	 */
	List<?> getRoleUserGridStoreQuery(Map<String, Object> build);

	/**
	 * 
	 * 方法名称：getRoleUserGridStoreTotal<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param map
	 * @return
	 */
	Integer getRoleUserGridStoreTotal(Map<String, Object> map);

	/**
	 * 
	 * 方法名称：grantRoleToUsers<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param ids
	 * @param roleId
	 * @throws Exception
	 */
	void grantRoleToUsers(@Param("ids") String[] ids, @Param("roleId") String roleId) throws Exception;

	/**
	 * 
	 * 方法名称：deleteUsersFromRole<br>
	 * 方法说明: <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-12<br>
	 * 
	 * @param ids
	 * @param roleId
	 * @throws Exception
	 */
	void deleteUsersFromRole(@Param("ids") String[] ids, String roleId) throws Exception;

	/***
	 * 
	 * 方法名称：getNoUserOfRole<br>
	 * 方法说明:获取用户没有赋予的角色 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-26<br>
	 * 
	 * @param user_id
	 * @param sysType
	 * @return
	 */
	List<ComboBox> getNoUserOfRole(String user_id, int sysType);

	/***
	 * 
	 * 方法名称：getUserOfRole<br>
	 * 方法说明:获取用户所赋予的角色 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-26<br>
	 * 
	 * @param user_id
	 * @param sysType
	 * @return
	 */
	List<ComboBox> getUserOfRole(String user_id, int sysType);

	/**
	 * 
	 * 方法名称：addUserOfRole<br>
	 * 方法说明: 增加用户角色<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-26<br>
	 * 
	 * @param user_id
	 * @param split
	 * @throws Exception
	 */
	void addUserOfRole(String user_id, @Param("ids") String[] split) throws Exception;

	/***
	 * 
	 * 方法名称：deleteUserOfRole<br>
	 * 方法说明: 删除用户角色<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-12-26<br>
	 * 
	 * @param user_id
	 * @param split
	 * @throws Exception
	 */
	void deleteUserOfRole(String user_id, @Param("ids") String[] split) throws Exception;
	
	/**
	 * 
	 * 方法名称：getRoleCount<br>
	 * 方法说明: <br>
	 * 创建人: 侯智<br>
	 * 创建日期: 2017-5-10<br>
	 * @param role_name
	 * @return
	 */
	 int getRoleCount() throws Exception;
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
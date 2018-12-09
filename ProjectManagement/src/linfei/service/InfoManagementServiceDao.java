package linfei.service;
/**
 * 项目合同信息管理Service层-接口
 * @author 林飞
 *
 */

import java.util.List;

import linfei.pojo.InfoManagement;

public interface InfoManagementServiceDao {
	/**
	 * 查询所有项目合同信息管理
	 * @return List<InfoManagement>
	 */
	public List<InfoManagement> selectAllInfoManagement();
	/**
	 * 根据id删除项目合同信息管理
	 * @return Integer
	 */
	public Integer deleteInfoManagementById(Integer id);
	/**
	 * 根据id修改项目合同信息管理
	 * @return Integer
	 */
	public Integer updateInfoManagementById(InfoManagement infomanagement);
	/**
	 * 添加项目合同信息管理
	 * @param infomanagement
	 * @return Integer
	 */
	public Integer insertInfoManagement(InfoManagement infomanagement);
}

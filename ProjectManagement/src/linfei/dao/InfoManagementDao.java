package linfei.dao;

import java.util.List;

import linfei.pojo.InfoManagement;

/**
 * 项目合同信息管理-Dao接口
 * @author 林飞
 *
 */
public interface InfoManagementDao {
	/**
	 * 添加合同信息
	 * @param InfoManagement infomanagement
	 * @return Integer
	 */
	public Integer insert(InfoManagement infomanagement);
	/**
	 * 根据id删除合同信息
	 * @param Integer id
	 * @return Integer
	 */
	public Integer delete(Integer id);
	/**
	 * 根据id修改合同信息
	 * @param InfoManagement infomanagement
	 * @return Integer
	 */
	public Integer updateById(InfoManagement infomanagement);
	/**
	 * 查询所有合同信息
	 * @return List<InfoManagement>
	 */
	public List<InfoManagement> selectAll();
}

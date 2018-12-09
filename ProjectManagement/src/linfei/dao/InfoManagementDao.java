package linfei.dao;

import java.util.List;

import linfei.pojo.InfoManagement;

/**
 * ��Ŀ��ͬ��Ϣ����-Dao�ӿ�
 * @author �ַ�
 *
 */
public interface InfoManagementDao {
	/**
	 * ��Ӻ�ͬ��Ϣ
	 * @param InfoManagement infomanagement
	 * @return Integer
	 */
	public Integer insert(InfoManagement infomanagement);
	/**
	 * ����idɾ����ͬ��Ϣ
	 * @param Integer id
	 * @return Integer
	 */
	public Integer delete(Integer id);
	/**
	 * ����id�޸ĺ�ͬ��Ϣ
	 * @param InfoManagement infomanagement
	 * @return Integer
	 */
	public Integer updateById(InfoManagement infomanagement);
	/**
	 * ��ѯ���к�ͬ��Ϣ
	 * @return List<InfoManagement>
	 */
	public List<InfoManagement> selectAll();
}

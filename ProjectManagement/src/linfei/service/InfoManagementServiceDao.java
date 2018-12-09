package linfei.service;
/**
 * ��Ŀ��ͬ��Ϣ����Service��-�ӿ�
 * @author �ַ�
 *
 */

import java.util.List;

import linfei.pojo.InfoManagement;

public interface InfoManagementServiceDao {
	/**
	 * ��ѯ������Ŀ��ͬ��Ϣ����
	 * @return List<InfoManagement>
	 */
	public List<InfoManagement> selectAllInfoManagement();
	/**
	 * ����idɾ����Ŀ��ͬ��Ϣ����
	 * @return Integer
	 */
	public Integer deleteInfoManagementById(Integer id);
	/**
	 * ����id�޸���Ŀ��ͬ��Ϣ����
	 * @return Integer
	 */
	public Integer updateInfoManagementById(InfoManagement infomanagement);
	/**
	 * �����Ŀ��ͬ��Ϣ����
	 * @param infomanagement
	 * @return Integer
	 */
	public Integer insertInfoManagement(InfoManagement infomanagement);
}

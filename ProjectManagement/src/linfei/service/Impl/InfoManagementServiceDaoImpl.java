package linfei.service.Impl;
/**
 * ��Ŀ��ͬ��Ϣ����Service��ʵ����
 * @author �ַ�
 *
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import linfei.dao.InfoManagementDao;
import linfei.pojo.InfoManagement;
import linfei.service.InfoManagementServiceDao;
@Service
@Controller
public class InfoManagementServiceDaoImpl implements InfoManagementServiceDao {
	@Autowired
	private InfoManagementDao infomanagementdao;
	/**
	 * ��ѯ������Ŀ��ͬ��Ϣ����
	 * @return List<InfoManagement>
	 */
	@Override
	public List<InfoManagement> selectAllInfoManagement() {
		List<InfoManagement> list=infomanagementdao.selectAll();
		return list;
	}
	/**
	 * ����idɾ����Ŀ��ͬ��Ϣ����
	 * @return Integer
	 */
	@Override
	public Integer deleteInfoManagementById(Integer id) {
		Integer num=infomanagementdao.delete(id);
		return num;
	}
	/**
	 * ����id�޸���Ŀ��ͬ��Ϣ����
	 * @return Integer
	 */
	@Override
	public Integer updateInfoManagementById(InfoManagement infomanagement) {
		Integer num=infomanagementdao.updateById(infomanagement);
		return num;
	}
	/**
	 * �����Ŀ��ͬ��Ϣ����
	 * @param infomanagement
	 * @return Integer
	 */
	@Override
	public Integer insertInfoManagement(InfoManagement infomanagement) {
		Integer num=infomanagementdao.insert(infomanagement);
		return num;
	}
}

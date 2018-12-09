package linfei.service.Impl;
/**
 * 项目合同信息管理Service层实现类
 * @author 林飞
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
	 * 查询所有项目合同信息管理
	 * @return List<InfoManagement>
	 */
	@Override
	public List<InfoManagement> selectAllInfoManagement() {
		List<InfoManagement> list=infomanagementdao.selectAll();
		return list;
	}
	/**
	 * 根据id删除项目合同信息管理
	 * @return Integer
	 */
	@Override
	public Integer deleteInfoManagementById(Integer id) {
		Integer num=infomanagementdao.delete(id);
		return num;
	}
	/**
	 * 根据id修改项目合同信息管理
	 * @return Integer
	 */
	@Override
	public Integer updateInfoManagementById(InfoManagement infomanagement) {
		Integer num=infomanagementdao.updateById(infomanagement);
		return num;
	}
	/**
	 * 添加项目合同信息管理
	 * @param infomanagement
	 * @return Integer
	 */
	@Override
	public Integer insertInfoManagement(InfoManagement infomanagement) {
		Integer num=infomanagementdao.insert(infomanagement);
		return num;
	}
}

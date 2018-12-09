package linfei.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * InfoManagementController
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import linfei.pojo.InfoManagement;
import linfei.service.InfoManagementServiceDao;

@Controller
@RequestMapping("/InfoManagement")
public class InfoManagementController {
	@Autowired
	private InfoManagementServiceDao infomanagementservicedao;
	/**
	 * �����Ŀ��ͬ��Ϣ
	 * @return Integer
	 */
	@RequestMapping("/insertInfoManagement")
	public String insertInfoManagement(@ModelAttribute("InfoManagement")InfoManagement infomanagement,Model model){
		
		Integer num=infomanagementservicedao.insertInfoManagement(infomanagement);
		model.addAttribute("success", num);
		return "info";
	}
	/**
	 * ����id�޸���Ŀ��ͬ��Ϣ
	 * @return Integer
	 */
	@RequestMapping("/updateInfoManagementById")
	public Integer updateInfoManagementById(InfoManagement infomanagement){
		Integer num=infomanagementservicedao.updateInfoManagementById(infomanagement);
		return num;
	}
	/**
	 * ����idɾ����Ŀ��ͬ��Ϣ
	 * @return Integer
	 */
	@RequestMapping("/deleteInfoManagementById")
	public Integer deleteInfoManagementById(Integer id){
		Integer num=infomanagementservicedao.deleteInfoManagementById(id);
		return num;
	}
	/**
	 * ��ѯ������Ŀ��ͬ��Ϣ
	 * @return List<InfoManagement>
	 */
	@RequestMapping("/selectAllInfoManagement")
	public List<InfoManagement> selectAllInfoManagement(){
		List<InfoManagement> list=infomanagementservicedao.selectAllInfoManagement();
		return list;
	}
}

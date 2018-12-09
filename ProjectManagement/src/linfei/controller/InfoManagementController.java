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
	 * 添加项目合同信息
	 * @return Integer
	 */
	@RequestMapping("/insertInfoManagement")
	public String insertInfoManagement(@ModelAttribute("InfoManagement")InfoManagement infomanagement,Model model){
		
		Integer num=infomanagementservicedao.insertInfoManagement(infomanagement);
		model.addAttribute("success", num);
		return "info";
	}
	/**
	 * 根据id修改项目合同信息
	 * @return Integer
	 */
	@RequestMapping("/updateInfoManagementById")
	public Integer updateInfoManagementById(InfoManagement infomanagement){
		Integer num=infomanagementservicedao.updateInfoManagementById(infomanagement);
		return num;
	}
	/**
	 * 根据id删除项目合同信息
	 * @return Integer
	 */
	@RequestMapping("/deleteInfoManagementById")
	public Integer deleteInfoManagementById(Integer id){
		Integer num=infomanagementservicedao.deleteInfoManagementById(id);
		return num;
	}
	/**
	 * 查询所有项目合同信息
	 * @return List<InfoManagement>
	 */
	@RequestMapping("/selectAllInfoManagement")
	public List<InfoManagement> selectAllInfoManagement(){
		List<InfoManagement> list=infomanagementservicedao.selectAllInfoManagement();
		return list;
	}
}

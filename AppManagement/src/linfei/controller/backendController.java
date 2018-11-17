package linfei.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import linfei.pojo.BackendUser;
import linfei.service.Services;

/**
 * ������
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/manager")
public class backendController {
	@Autowired
	private Services services;
	/**
	 * ������
	 * @return String
	 */
	@RequestMapping("/login")
	public String managerlogin(){
		return "backendlogin";
	}
	/**
	 * ��¼��֤
	 * @param devCode
	 * @param devPassword
	 * @param model
	 * @return String
	 */
	@RequestMapping("/dologin")
	public String backdologin(String userCode,String userPassword,Model model){
		BackendUser backenduser=services.backdologin(userCode, userPassword);
		if(backenduser==null){
			model.addAttribute("error", "�û��������ڣ�");
			return "devlogin";
		}else{
			model.addAttribute("backenduser", backenduser);
			return "/backend/applist";
		}		
	}
	/**
	 * �˳�
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req,Model model){
		return "devlogin";
	}
}

package linfei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {
	@RequestMapping("/dev/login")
	public String devlogin(){
		return "devlogin";
	}
	@RequestMapping("/manager/login")
	public String managerlogin(){
		return "backendlogin";
	}
	@RequestMapping("/dev/dologin")
	public String dologin(String devCode,String devPassword){
		
		System.out.println(devCode);
		System.out.println(devPassword);
		return "/backend/main";
	}
}

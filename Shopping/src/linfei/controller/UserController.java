package linfei.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import linfei.service.UserServiceDao;
/**
 * 用户Controller
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceDao userservice;
	@RequestMapping("/deluserbyid")
	@ResponseBody
	public int deluserbyid(HttpServletRequest req){
		String userid_str=req.getParameter("id");
		int userid=Integer.parseInt(userid_str);
		int cun=userservice.deleteByPrimaryKey(userid);
		return cun;
	}
}

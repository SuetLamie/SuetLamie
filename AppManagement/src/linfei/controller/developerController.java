package linfei.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import linfei.pojo.AppInfo;
import linfei.pojo.Category;
import linfei.pojo.DataDictionary;
import linfei.pojo.Pages;
import linfei.pojo.User;
import linfei.service.Services;
/**
 * 开发者
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/dev")
public class developerController {
	Pages pages=new Pages();
	/**
	 * 开发者
	 */
	@Autowired
	private Services services;
	@RequestMapping("/login")
	public String devlogin(){
		return "devlogin";
	}
	/**
	 * 登录验证
	 * @param devCode
	 * @param devPassword
	 * @param model
	 * @return String
	 */
	@RequestMapping("/dologin")
	public String dologin(String devCode,String devPassword,Model model){
		User user=services.dologin(devCode, devPassword);
		if(user==null){
			model.addAttribute("error", "用户名不存在！");
			return "devlogin";
		}else{
			List<Category> list=services.selectCategoryByParentId(0);
			model.addAttribute("categoryLevel1List", list);
			List<DataDictionary> statusList=services.selectDataDictionaryBytypecode("APP_STATUS");
			model.addAttribute("statusList", statusList);
			List<DataDictionary> flatFormList=services.selectDataDictionaryBytypecode("APP_FLATFORM");
			model.addAttribute("flatFormList", flatFormList);			
			model.addAttribute("user", user);
			List<AppInfo> appInfoList=services.selectAllAppInfo(devPassword, 0, pages.getPagesize(), null, null, null, 0, 0);
			int count=services.selectCount();				
			pages.setTotalCount(count);
			model.addAttribute("appInfoList", appInfoList);	
			model.addAttribute("pages", pages);
			return "/developer/appinfolist";
		}		
	}
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req,Model model){
		return "devlogin";
	}
	/**
	 * 分类查询
	 * @param req
	 * @return Object
	 */
	@RequestMapping("/categorylevellist")
	@ResponseBody 
	public Object selectCategoryByParentId(HttpServletRequest req){
		String parentid_str=req.getParameter("pid");
		Integer parentid=Integer.parseInt(parentid_str);
		List<Category> list=services.selectCategoryByParentId(parentid);
		return list;
	}
	/**
	 * 分页查询App基础信息
	 * @return
	 */
	@RequestMapping("/list")	
	public Object selectAllAppInfo(String querySoftwareName,Integer status,Integer flatformId,Integer categoryLevel1,Integer categoryLevel2,Integer categoryLevel3){	
		int fromindex=(pages.getCurrentPageNo()-1)*pages.getPagesize();
		List<AppInfo> appInfoList=services.selectAllAppInfo(querySoftwareName,status,flatformId,categoryLevel1,categoryLevel2,categoryLevel3,fromindex, pages.getPagesize());
		return appInfoList;
	}
}

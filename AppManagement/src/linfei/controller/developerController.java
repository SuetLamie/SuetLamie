package linfei.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	public String dologin(String devCode,String devPassword,HttpSession session,HttpServletRequest req){
		Pages pages=new Pages();
		String querySoftwareName=req.getParameter("querySoftwareName");
		String queryStatus=req.getParameter("queryStatus");
		String queryFlatformId=req.getParameter("queryFlatformId");
		String queryCategoryLevel1=req.getParameter("queryCategoryLevel1");
		String queryCategoryLevel2=req.getParameter("queryCategoryLevel2");
		String queryCategoryLevel3=req.getParameter("queryCategoryLevel3");
		User user=services.dologin(devCode, devPassword);
		if(user==null){
			session.setAttribute("error", "用户名不存在！");
			return "devlogin";
		}else{
			List<Category> list=services.selectCategoryByParentId(0);
			session.setAttribute("categoryLevel1List", list);
			List<DataDictionary> statusList=services.selectDataDictionaryBytypecode("APP_STATUS");
			session.setAttribute("statusList", statusList);
			List<DataDictionary> flatFormList=services.selectDataDictionaryBytypecode("APP_FLATFORM");
			session.setAttribute("flatFormList", flatFormList);			
			session.setAttribute("user", user);
			List<AppInfo> appInfoList=services.selectAllAppInfo(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, 1, pages.getPagesize());
			int count=services.selectCount();				
			pages.setTotalCount(count);
			pages.setCurrentPageNo(1);
			session.setAttribute("appInfoList", appInfoList);	
			session.setAttribute("pages", pages);
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
	@RequestMapping("/categorylevellist.json")
	@ResponseBody 
	public Object selectCategoryByParentId(HttpServletRequest req){
		String parentid_str=req.getParameter("pid");
		Integer parentid=Integer.parseInt(parentid_str);
		List<Category> list=services.selectCategoryByParentId(parentid);
		return list;
	}
	/**
	 * 分页
	 * @return
	 */
	@RequestMapping("/list")
	public Object list(HttpServletRequest req,HttpSession session){
		Pages pages=(Pages)session.getAttribute("pages");
		String querySoftwareName=req.getParameter("querySoftwareName");
		String queryStatus=req.getParameter("queryStatus");
		String queryFlatformId=req.getParameter("queryFlatformId");
		String queryCategoryLevel1=req.getParameter("queryCategoryLevel1");
		String queryCategoryLevel2=req.getParameter("queryCategoryLevel2");
		String queryCategoryLevel3=req.getParameter("queryCategoryLevel3");
		String index_str=req.getParameter("pageIndex");
		int index=Integer.parseInt(index_str);
		pages.setCurrentPageNo(index);
		List<AppInfo> list=services.selectAllAppInfo(querySoftwareName, queryStatus, queryFlatformId, queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3,index, pages.getPagesize());
		session.setAttribute("appInfoList", list);
		session.setAttribute("pages", pages);
		return "/developer/appinfolist";
	}
	/**
	 * 添加App
	 * @return String
	 */
	@RequestMapping("/appversionadd")
	public String appinfoadd(){				
		return "/developer/appversionadd";
	}
	/**
	 * 添加App版本
	 * @return
	 */
	@RequestMapping("/addversionsave")
	public String addversionsave(HttpServletRequest req,MultipartFile file){
		if(!file.isEmpty()){
			
		}
		String versionNo_str=req.getParameter("versionNo");
		Integer versionNo=Integer.parseInt(versionNo_str);	//版本号
		String versionSize_str=req.getParameter("versionSize");
		Double versionSize=Double.parseDouble(versionSize_str);	//版本大小
		String publishStatus=req.getParameter("publishStatus");	//发布状态 值为3，需在数据库查询
		String versionInfo=req.getParameter("versionInfo");	//版本简介
		return "/developer/appversionadd";
	}
}

package linfei.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * Informations-Controller类
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import linfei.pojo.Informations;
import linfei.pojo.Pages;
import linfei.pojo.Replies;
import linfei.service.ServiceDao;

@Controller
public class controller {
	@Autowired
	private ServiceDao servicedao;
	@RequestMapping("/index")
	public String index(HttpSession session){
		Pages pages=new Pages();
		int cun=servicedao.selectAllCount();	//查询总页
		List<Informations> list=servicedao.selectByLimitandorder(pages);
		pages.setInformations(list);
		pages.setTotalCount(cun);
		System.out.println(cun);
		System.out.println(pages.getCurrentPageNo());
		System.out.println(pages.getTotalPageCount());
		session.setAttribute("pages", pages);
		return "forward:/index.jsp";
	}
	/**
	 * 分页
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping("/limit")
	@ResponseBody
	public Object limit(HttpServletRequest req,HttpSession session){
		String id_str=req.getParameter("id");
		Pages pages=(Pages)session.getAttribute("pages");
		if(id_str.equals("首页")){
			//首页			
			pages.setCurrentPageNo(1);
		}else if(id_str.equals("上一页")){
			//上一页
			pages.setCurrentPageNo(pages.getCurrentPageNo()-1);
		}else if(id_str.equals("下一页")){
			//下一页
			pages.setCurrentPageNo(pages.getCurrentPageNo()+1);
		}else if(id_str.equals("末页")){
			//末页
			pages.setCurrentPageNo(pages.getTotalPageCount());		
		}	
		List<Informations> list=servicedao.selectByLimitandorder(pages);
		pages.setInformations(list);
		session.setAttribute("pages", pages);
		return pages;
	}
	@RequestMapping("/info")
	public String info(HttpServletRequest req,HttpSession session){
		String id_str=req.getParameter("id");
		int id=Integer.parseInt(id_str);
		Informations informations=servicedao.selectById(id);
		informations.setViewCount(informations.getViewCount()+1);
		session.setAttribute("informations", informations);
		servicedao.updateviewCountById(id, informations.getViewCount());	
		return "forward:/info.jsp";
	}
	@RequestMapping("submitreplies")
	@ResponseBody
	public Replies submitreplies(HttpServletRequest req){
		Replies replies=new Replies();
		Date date=new Date();
		String textarea=req.getParameter("textarea");
		String informationsid_str=req.getParameter("informationsid");
		int informationsid=Integer.parseInt(informationsid_str);
		System.out.println(textarea);
		System.out.println(informationsid);
		replies.setUcontent(textarea);
		replies.setInfoId(informationsid);
		replies.setReplyTime(date);
		servicedao.insertreplies(replies);
		Informations informations=servicedao.selectById(informationsid);
		informations.setLastPostTime(date);
		servicedao.updatereplyCountandlastPostTimeById(informationsid, informations.getReplyCount()+1, date);
		System.out.println(replies.getReplyTime());			
		return replies;
	}
}

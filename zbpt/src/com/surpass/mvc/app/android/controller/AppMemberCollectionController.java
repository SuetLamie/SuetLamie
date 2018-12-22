package com.surpass.mvc.app.android.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.app.android.service.AppMemberCollectionService;
import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.MemberCollectionCourse;
import com.surpass.mvc.system.bas.model.MemberCollectionOpus;
import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.utils.ResultApp;
import com.surpass.utils.StringUtil;

@Controller
@RequestMapping("app/bas/collection")
public class AppMemberCollectionController extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AppMemberCollectionService service;
	
	/**
	 * 获取用户收藏的课程数据
	 * **/
	@RequestMapping("getMemberCollectionCourse")
	@ResponseBody
	public ResultApp getMemberCollectionCourse(int memberid,int pageindex, int pagenum){
		ResultApp msg = new ResultApp();
		try {
			List<Course> list =service.getList(memberid, pageindex * pagenum, pagenum, null);
			msg.setPageData(list);
			msg.setSuccess(true);
			msg.setMsg("获取用户收藏课程成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取用户收藏课程错误"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 获取被收藏的数量和用户是否收藏
	 * **/
	@RequestMapping("getMemberCollectionTotal")
	@ResponseBody
	public ResultApp getMemberCollectionTotal(MemberCollectionCourse mcc,MemberCollectionOpus mco){
		ResultApp msg = new ResultApp();
		try {
			HashMap<Object, Object> param =new HashMap<>();
			int total =0;
			List<MemberCollectionCourse> isCourse=service.isCollectionCourse(mcc);
			List<MemberCollectionOpus> isOpus=service.isCollectionOpus(mco);
			if(!StringUtil.isEmpty(mcc.getCourseid())){
				total=service.totalbyCourse(mcc);
			}
			if(!StringUtil.isEmpty(mco.getOpusid())){
				total=service.totalbyOpus(mco);
			}
			if(isCourse.size()>0||isOpus.size()>0){
				param.put("Collection",true);
			}else{
				param.put("Collection", false);
			}
			param.put("total", total);
			msg.setPageData(param);
			msg.setSuccess(true);
			msg.setMsg("获取收藏数量成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取收藏数量错误"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 用户点击收藏
	 * **/
	@RequestMapping("getClickCollection")
	@ResponseBody
	public ResultApp getClickCollection(MemberCollectionCourse mcc,MemberCollectionOpus mco){
		ResultApp msg = new ResultApp();
		try {
			if(StringUtil.isEmpty(mco.getOpusid())){
				service.insert(mcc);
			}else{
				service.insertOpus(mco);
			}
			msg.setSuccess(true);
			msg.setMsg("收藏成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("收藏失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 用户取消收藏
	 * **/
	@RequestMapping("delete")
	@ResponseBody
	public ResultApp delete(MemberCollectionCourse mcc,MemberCollectionOpus mco){
		ResultApp msg = new ResultApp();
		try {
			if(StringUtil.isEmpty(mco.getOpusid())){
				service.delete(mcc);
			}else{
				service.deleteOpus(mco);
			}
			msg.setSuccess(true);
			msg.setMsg("取消收藏成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("取消收藏失败"+e.getMessage());
		}
		return msg;
	}
	/**
	 * 获取用户收藏的作品数据
	 * **/
	@RequestMapping("getMemberCollectionOpus")
	@ResponseBody
	public ResultApp getMemberCollectionOpus(int memberid,int pageindex, int pagenum){
		ResultApp msg = new ResultApp();
		try {
			List<TeacherOpus> list =service.getOpusList(memberid, pageindex * pagenum, pagenum, null);
			msg.setPageData(list);
			msg.setSuccess(true);
			msg.setMsg("获取用户收藏作品成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取用户收藏作品错误"+e.getMessage());
		}
		return msg;
	}
}

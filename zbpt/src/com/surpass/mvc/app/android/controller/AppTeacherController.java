package com.surpass.mvc.app.android.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.app.android.service.AppTeacherService;
import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.mvc.system.sys.model.TeacherInfo;
import com.surpass.utils.ResultApp;

@Controller
@RequestMapping("app/sys/teacher")
public class AppTeacherController extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AppTeacherService service;
	
	@RequestMapping("getTeacherData")
	@ResponseBody
	public ResultApp getTeacherData(TeacherInfo obj, int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<TeacherInfo> lis = service.getListData(obj, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取教师信息成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取教师信息错误");
		}
		return msg;
	}
	/**
	 * getTeacherByTypeId
	 * 说明：通过类型获取教师信息
	 * */
	@RequestMapping("getTeacherByTypeId")
	@ResponseBody
	public ResultApp getTeacherByTypeId(String typeid, String commond,int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<TeacherInfo> lis = service.getTeacherInfoByTypeId(typeid,commond, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取教师信息成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取教师信息错误"+e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping("getTeacherOpusData")
	@ResponseBody
	public ResultApp getTeacherOpusData(TeacherOpus obj, int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<TeacherOpus> lis = service.getTeacherOpusList(obj, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取教师作品信息成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取教师作品信息错误");
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：getTeacherOpusTJData<br>
	 * 方法说明: 作品展示<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-9-26<br>
	 * 
	 * @param course
	 * @param pageindex
	 * @param pagenum
	 * @return
	 */
	@RequestMapping("getTeacherOpusTJData")
	@ResponseBody
	public ResultApp getTeacherOpusTJData(TeacherOpus teacherOpus, int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<TeacherOpus> lis = service.getHotData(teacherOpus, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取作品展示成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取作品展示错误");
		}
		return msg;
	}
}

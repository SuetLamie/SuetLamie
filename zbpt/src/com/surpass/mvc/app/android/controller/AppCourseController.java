/**
 *
 */
package com.surpass.mvc.app.android.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.app.android.model.CourseByMember;
import com.surpass.mvc.app.android.service.AppCourseService;
import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.Courseware;
import com.surpass.mvc.system.bas.model.MyCourse;
import com.surpass.utils.ResultApp;
import com.surpass.utils.StringUtil;

/**
 * 类名称： AppCourseController<br>
 * 类描述： 课程相关<br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-27<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-27
 */
@Controller
@RequestMapping("app/bas/course")
public class AppCourseController extends BaseController implements Serializable {

	/**
	 * CourseController.java
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG4J = LoggerFactory.getLogger(AppCourseController.class);

	@Autowired
	private AppCourseService service;

	/**
	 * 
	 * 方法名称：getCourseData<br>
	 * 方法说明: 获取课程列表<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2018-5-28<br>
	 * 
	 * @param course
	 * @param pageindex
	 * @param pagenum
	 * @return
	 */
	@RequestMapping("getCourseData")
	@ResponseBody
	public ResultApp getCourseData(Course course, int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<Course> lis = service.getListData(course, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取课程成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取课程错误");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 
	 * 方法名称：getCourseByMy<br>
	 * 方法说明: 获取APP会员课程列表<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-11-7<br>
	 * 
	 * @param memberid
	 * @param level
	 * @param type
	 * @param pageindex
	 * @param pagenum
	 * @return
	 */
	@RequestMapping("getCourseByMy")
	@ResponseBody
	public ResultApp getCourseByMy(CourseByMember cb, int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<CourseByMember> lis = service.getCourseByMyList(cb, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取课程成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取课程错误");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：getMyCourse<br>
	 * 方法说明: 获取会员购买的课程列表<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-11-7<br>
	 * 
	 * @param memberId
	 * @param pageindex
	 * @param pagenum
	 * @return
	 */
	@RequestMapping("getMyCourse")
	@ResponseBody
	public ResultApp getMyCourse(CourseByMember cb, int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<CourseByMember> lis = service.getMyCourse(cb, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取我的课程成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取我的课程成功");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：courseIsBuy<br>
	 * 方法说明: 课程是否购买或者过期<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-11-7<br>
	 * @param course_id
	 * @param memberid
	 * @param pageindex
	 * @param pagenum
	 * @return
	 */
	@RequestMapping("courseIsBuy")
	@ResponseBody
	public ResultApp courseIsBuy(MyCourse mc) {
		ResultApp msg = new ResultApp();
		try {
			MyCourse m = service.courseisbuy(mc);
			HashMap<Object, Object> param =new HashMap<>();
			if(StringUtil.isEmpty(m)){
				param.put("isBuyOrEnd","1");
				msg.setMsg("已过期");
			}else{
				param.put("isBuyOrEnd","0");
				msg.setMsg("未过期");
			}
			msg.setPageData(param);
			msg.setSuccess(true);
		} catch (Exception e) {
			msg.setSuccess(false);
			LOG4J.error(e.getMessage());
		}
		return msg;
	}
	/**
	 * 
	 * 方法名称：getCourseTJData<br>
	 * 方法说明: 热门推荐<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2018-5-28<br>
	 * 
	 * @param course
	 * @param pageindex
	 * @param pagenum
	 * @return
	 */
	@RequestMapping("getCourseTJData")
	@ResponseBody
	public ResultApp getCourseTJData(Course course, int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<Course> lis = service.getHotData(course, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取热门推荐成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取热门推荐错误");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}

	/**
	 * 
	 * 方法名称：getCourseTJData<br>
	 * 方法说明: 讲师推荐<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2018-5-28<br>
	 * 
	 * @param course
	 * @param pageindex
	 * @param pagenum
	 * @return
	 */
	@RequestMapping("getCourseJsTjData")
	@ResponseBody
	public ResultApp getCourseJsTjData(Course course, int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<Course> lis = service.getTeacherData(course, pageindex * pagenum, pagenum, null);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取讲师推荐成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取讲师推荐错误");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}

	/**
	 * 获取课件信息
	 */
	@RequestMapping("getCourseware")
	@ResponseBody
	public ResultApp getCourseware(int course_id) {
		ResultApp msg = new ResultApp();
		try {
			List<Courseware> lis = service.getCoursewareList(course_id);
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取课件信息成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取课件信息错误");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 获取最新课件信息
	 */
	@RequestMapping("getNewCourseware")
	@ResponseBody
	public ResultApp getNewCoursewareList(int pageindex, int pagenum) {
		ResultApp msg = new ResultApp();
		try {
			List<Courseware> lis = service.getNewCoursewareList(pageindex * pagenum, pagenum, "");
			msg.setPageData(lis);
			msg.setSuccess(true);
			msg.setMsg("获取最新课件成功");
		} catch (Exception e) {
			msg.setSuccess(false);
			msg.setMsg("获取最新课件错误");
			LOG4J.error(e.getMessage());
		}
		return msg;
	}

}

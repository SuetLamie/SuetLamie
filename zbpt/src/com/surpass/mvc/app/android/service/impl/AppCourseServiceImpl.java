/**
 *
 */
package com.surpass.mvc.app.android.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.app.android.dao.AppCourseMapper;
import com.surpass.mvc.app.android.model.CourseByMember;
import com.surpass.mvc.app.android.service.AppCourseService;
import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.Courseware;
import com.surpass.mvc.system.bas.model.MyCourse;

/**
 * 类名称： AppCourseServiceImpl<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-28<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-28
 */
@Service("appCourseService")
public class AppCourseServiceImpl implements AppCourseService {

	/**
	 * 请求参数绑定器
	 */
	@Autowired
	private ParametersMapBuilder parametersMapBuilder;

	@Autowired
	private AppCourseMapper dao;

	@Override
	public List<Course> getListData(Course obj, int start, int limit, String orderBy) throws Exception {
		return dao.getList(parametersMapBuilder.build(start, start + limit, orderBy, obj));
	}

	@Override
	public List<Course> getHotData(Course obj, int start, int limit, String orderBy) throws Exception {
		return dao.getHotData(parametersMapBuilder.build(start, start + limit, orderBy, obj));
	}

	@Override
	public List<Course> getTeacherData(Course obj, int start, int limit, String orderBy) throws Exception {
		return dao.getTeacherData(parametersMapBuilder.build(start, start + limit, orderBy, obj));
	}

	@Override
	public List<Courseware> getCoursewareList(int course_id) throws Exception {
		return dao.getCoursewareList(course_id);
	}

	@Override
	public List<Courseware> getNewCoursewareList(int start, int limit, String orderBy) throws Exception {
		Courseware obj = new Courseware();
		return dao.getNewCoursewareList(parametersMapBuilder.build(start, start + limit, orderBy, obj));
	}

	@Override
	public List<CourseByMember> getCourseByMyList(CourseByMember cb, int start, int limit, String orderBy)
			throws Exception {
		return dao.getCourseByMyList(parametersMapBuilder.build(start, start + limit, orderBy, cb));
	}

	@Override
	public List<CourseByMember> getMyCourse(CourseByMember cb, int start, int limit, String orderBy) throws Exception {
		return dao.getMyCourse(parametersMapBuilder.build(start, start + limit, orderBy, cb));
	}

	@Override
	public MyCourse courseisbuy(MyCourse mc) throws Exception {
		return dao.courseisbuy(mc);
	}


}

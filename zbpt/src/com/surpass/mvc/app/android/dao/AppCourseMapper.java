package com.surpass.mvc.app.android.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.surpass.mvc.app.android.model.CourseByMember;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.Courseware;
import com.surpass.mvc.system.bas.model.MyCourse;

/**
 * 类名称： CourseMapper<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-23<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-23
 */
public interface AppCourseMapper {
	/**
	 * 查询 信息
	 */
	List<Course> getList(Map<String, Object> build) throws Exception;
	
	/**
	 * 查询课件信息
	 */
	List<Courseware> getCoursewareList(@Param(value = "course_id") int course_id)throws Exception;

	/**
	 * 获取最新视频
	 */
	List<Courseware> getNewCoursewareList(Map<String, Object> build)throws Exception;

	/**
	 * 热门推荐
	 */
	List<Course> getHotData(Map<String, Object> build) throws Exception;

	/**
	 * 讲师推荐
	 */
	List<Course> getTeacherData(Map<String, Object> build) throws Exception;
	
	/**
	 * 我购买的课程
	 */
	List<CourseByMember> getMyCourse(Map<String, Object> build) throws Exception;

	/**
	 * 查询课程通过app会员附带收藏信息
	 */
	List<CourseByMember> getCourseByMyList(Map<String, Object> build) throws Exception;
	/**
	 * 查询课程是否已购买或者已到期
	 */
	MyCourse courseisbuy(MyCourse mc)throws Exception;
}
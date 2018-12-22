/**
*
*/
package com.surpass.mvc.app.android.service;

import java.util.List;

import com.surpass.mvc.app.android.model.CourseByMember;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.Courseware;
import com.surpass.mvc.system.bas.model.MyCourse;

/**
 * 类名称： AppCourseService<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-28<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-28
 */
public interface AppCourseService {

	/**
	 * 课程查询
	 */
	List<Course> getListData(Course obj, int start, int limit, String orderBy) throws Exception;
	
	/**
	 * 课件查询 根据课程ID
	 */
	List<Courseware> getCoursewareList(int course_id) throws Exception;

	/**
	 * 最新视频
	 */
	List<Courseware> getNewCoursewareList(int start, int limit, String orderBy) throws Exception;
	
	/**
	 * 热门推荐
	 */
	List<Course> getHotData(Course obj, int start, int limit, String orderBy) throws Exception;
	
	/**
	 * 讲师推荐
	 */
	List<Course> getTeacherData(Course obj, int start, int limit, String orderBy) throws Exception;
	
	/**
	 * 查询课程通过app会员附带收藏信息
	 */
	List<CourseByMember> getCourseByMyList(CourseByMember cb,int start, int limit, String orderBy) throws Exception;
	/**
	 * 我购买的课程
	 */
	List<CourseByMember> getMyCourse(CourseByMember cb,int start, int limit, String orderBy) throws Exception;
	/**
	 * 查询课程是否已购买或者已到期
	 */
	MyCourse courseisbuy(MyCourse mc)throws Exception;
}

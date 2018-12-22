package com.surpass.mvc.app.android.service;

import java.util.List;

import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.MemberCollectionCourse;
import com.surpass.mvc.system.bas.model.MemberCollectionOpus;
import com.surpass.mvc.system.bas.model.TeacherOpus;

public interface AppMemberCollectionService {
	/**
	 * 用户收藏的课程总数
	 * */
	int totalbyMember(MemberCollectionCourse mcc)throws Exception;
	/**
	 * 课程被收藏总数
	 */
	int totalbyCourse(MemberCollectionCourse mcc)throws Exception;
	/**
	 * 获取收藏课程数据
	 * */
	List<Course> getList(int memberid, int start, int limit, String orderBy)throws Exception;
	/**
	 * 收藏课程
	 * */
	void insert(MemberCollectionCourse obj)throws Exception;
	/**
	 * 取消课程
	 * */
	void delete(MemberCollectionCourse mcc)throws Exception;
	/**
	 * 获取收藏作品数据
	 * */
	List<TeacherOpus> getOpusList(int memberid, int start, int limit, String orderBy)throws Exception;
	
	/**
	 * 用户收藏的作品总数
	 * */
	int totalOpusbyMember(MemberCollectionOpus mco)throws Exception;
	/**
	 * 作品被收藏总数
	 */
	int totalbyOpus(MemberCollectionOpus mco)throws Exception;
	
	/**
	 * 收藏作品
	 * */
	void insertOpus(MemberCollectionOpus obj)throws Exception;
	/**
	 * 取消课程
	 * */
	void deleteOpus(MemberCollectionOpus mco)throws Exception;
	
	List<MemberCollectionCourse> isCollectionCourse(MemberCollectionCourse mcc)throws Exception;
	List<MemberCollectionOpus> isCollectionOpus(MemberCollectionOpus mco)throws Exception;
}

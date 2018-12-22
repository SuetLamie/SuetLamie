package com.surpass.mvc.app.android.dao;

import java.util.List;

import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.MemberCollectionCourse;
import com.surpass.mvc.system.bas.model.MemberCollectionOpus;
import com.surpass.mvc.system.bas.model.TeacherOpus;

public interface AppMemberCollectionMapper {
	 
    int insertSelective(MemberCollectionCourse record);
    
	/**
	 * 用户收藏的课程详情查询
	 */
	List<Course> getListData(int memberid,int start,int limit) throws Exception;
	
	/**
	 * 用户收藏的课程总数
	 */
	int totalbyMember(MemberCollectionCourse mcc);
	
	/**
	 * 课程被收藏总数
	 */
	int totalbyCourse(MemberCollectionCourse mcc);
	
	int delete(MemberCollectionCourse mcc);
	
	/*收藏作品*/
	int insertOpus(MemberCollectionOpus record);
    
	/**
	 * 用户收藏的作品详情查询
	 */
	List<TeacherOpus> getListOpus(int memberid,int start,int limit) throws Exception;
	
	/**
	 * 用户收藏的作品总数
	 */
	int totalOpusbyMember(MemberCollectionOpus mco);
	
	/**
	 * 作品被收藏总数
	 */
	int totalbyOpus(MemberCollectionOpus mco);
	
	int deleteOpus(MemberCollectionOpus mco);
	
	List<MemberCollectionCourse> isCollectionCourse(MemberCollectionCourse mcc)throws Exception;
	List<MemberCollectionOpus> isCollectionOpus(MemberCollectionOpus mco)throws Exception;
}

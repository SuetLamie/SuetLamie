package com.surpass.mvc.app.android.service;

import java.util.List;

import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.mvc.system.sys.model.TeacherInfo;

public interface AppTeacherService {
	/**
	 * 教师查询
	 */
	List<TeacherInfo> getListData(TeacherInfo obj, int start, int limit, String orderBy) throws Exception;
	
	/**
	 * 教师作品查询
	 */
	List<TeacherOpus> getTeacherOpusList(TeacherOpus obj, int start, int limit, String orderBy) throws Exception;
	
	/**
	 * 作品展示
	 */
	List<TeacherOpus> getHotData(TeacherOpus obj, int start, int limit, String orderBy) throws Exception;
	/**
	 * 通过类型查询教师信息 
	 */
	List<TeacherInfo> getTeacherInfoByTypeId(String typeid,String commond,int start,int limit, String orderBy) throws Exception;
}

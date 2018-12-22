package com.surpass.mvc.app.android.dao;

import java.util.List;
import java.util.Map;

import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.mvc.system.bas.model.TeacherType;
import com.surpass.mvc.system.sys.model.TeacherInfo;

public interface AppTeacherMapper {
	/**
	 * 查询 教师信息
	 */
	List<TeacherInfo> getList(Map<String, Object> build) throws Exception;
	
	/**
	 * 查询教师作品信息
	 */
	List<TeacherOpus> getOupsList(Map<String, Object> build) throws Exception;
	
	/**
	 * 作品展示
	 */
	List<TeacherOpus> getHotData(Map<String, Object> build) throws Exception;
	/**
	 * 教师类型
	 * */
	List<TeacherType> getTeacherType(TeacherType teacherType);
	
	/**
	 * 通过类型查询教师信息 
	 */
	List<TeacherInfo> getTeacherInfoByTypeId(String typeid,String commond,int start,int limit) throws Exception;
	
}

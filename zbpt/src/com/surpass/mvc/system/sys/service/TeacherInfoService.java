package com.surpass.mvc.system.sys.service;

import com.surpass.mvc.system.sys.model.TeacherInfo;
import com.surpass.utils.ResultGridStore;

public interface TeacherInfoService {
	/**
	 * 查询
	 */
	ResultGridStore getGridStore(TeacherInfo teacherInfo, int start, int limit, String orderBy) throws Exception;

	/**
	 * 添加
	 */
	void insert(TeacherInfo obj) throws Exception;

	/**
	 * 修改
	 */
	void update(TeacherInfo obj) throws Exception;

	/**
	 * 删除
	 */
	void delete(String ids) throws Exception;
	
	TeacherInfo getTeacherInfoByAccoutId(String accountid) throws Exception;
}

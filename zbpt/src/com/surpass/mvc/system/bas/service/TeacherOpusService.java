package com.surpass.mvc.system.bas.service;

import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.utils.ResultGridStore;

public interface TeacherOpusService {
	/**
	 * 查询
	 */
	ResultGridStore getGridStore(TeacherOpus obj, int start, int limit, String orderBy) throws Exception;

	/**
	 * 添加
	 */
	void insert(TeacherOpus obj) throws Exception;

	/**
	 * 修改
	 */
	void update(TeacherOpus obj) throws Exception;

	/**
	 * 删除
	 */
	int delete(String ids) throws Exception;
}

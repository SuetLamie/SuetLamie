package com.surpass.mvc.system.bas.service;

import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.mvc.system.bas.model.TeacherOpusHot;
import com.surpass.utils.ResultGridStore;

public interface TeacherOpusHotService {
	
	/**
	 * 查询
	 */
	ResultGridStore getGridStore(TeacherOpus obj, int start, int limit, String orderBy) throws Exception;
	
	/**
	 * 添加
	 */
	void insert(TeacherOpusHot obj) throws Exception;
	
	/**
	 * 删除
	 */
	int delete(String ids) throws Exception;
}

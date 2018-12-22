package com.surpass.mvc.app.android.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.app.android.dao.AppTeacherMapper;
import com.surpass.mvc.app.android.service.AppTeacherService;
import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.mvc.system.sys.model.TeacherInfo;

@Service("appTeacherService")
public class AppTeacherServiceImpl implements AppTeacherService{
	
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;

	@Autowired
	private AppTeacherMapper dao;
	
	@Override
	public List<TeacherInfo> getListData(TeacherInfo obj, int start, int limit, String orderBy) throws Exception {
		return dao.getList(parametersMapBuilder.build(start, start + limit, orderBy, obj));
	}

	@Override
	public List<TeacherOpus> getTeacherOpusList(TeacherOpus obj, int start, int limit, String orderBy)
			throws Exception {
		return dao.getOupsList(parametersMapBuilder.build(start, start + limit, orderBy, obj));
	}

	@Override
	public List<TeacherOpus> getHotData(TeacherOpus obj, int start, int limit, String orderBy) throws Exception {
		return dao.getHotData(parametersMapBuilder.build(start, start + limit, orderBy, obj));
	}

	@Override
	public List<TeacherInfo> getTeacherInfoByTypeId(String typeid,String commond, int start, int limit, String orderBy)
			throws Exception {
		return dao.getTeacherInfoByTypeId(typeid,commond, start, limit);
	}

}

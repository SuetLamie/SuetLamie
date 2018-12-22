package com.surpass.mvc.system.bas.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.TeacherOpusMapper;
import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.mvc.system.bas.service.TeacherOpusService;
import com.surpass.utils.ResultGridStore;

@Service("teacherOpusService")
public class TeacherOpusServiceImpl implements TeacherOpusService{
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;

	@Autowired
	private TeacherOpusMapper dao;

	@Override
	public ResultGridStore getGridStore(TeacherOpus obj, int start, int limit, String orderBy) throws Exception {
		ResultGridStore rg = new ResultGridStore();
		rg.setList(dao.query(parametersMapBuilder.build(start, start + limit, orderBy, obj)));
		rg.setTotalProperty(dao.total(obj));
		return rg;
	}

	@Override
	public void insert(TeacherOpus obj) throws Exception {
		dao.insertSelective(obj);
	}

	@Override
	public void update(TeacherOpus obj) throws Exception {
		// TODO Auto-generated method stub
		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(ids);
	}
}

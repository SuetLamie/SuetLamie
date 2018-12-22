package com.surpass.mvc.system.bas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.TeacherOpusHotMapper;
import com.surpass.mvc.system.bas.model.TeacherOpus;
import com.surpass.mvc.system.bas.model.TeacherOpusHot;
import com.surpass.mvc.system.bas.service.TeacherOpusHotService;
import com.surpass.utils.ResultGridStore;

@Service("teacherOpusHotService")
public class TeacherOpusHotServiceImpl implements TeacherOpusHotService{
	
	/**
	 * 请求参数绑定器
	 */
	@Autowired
	private ParametersMapBuilder parametersMapBuilder;

	@Autowired
	private TeacherOpusHotMapper dao;
	
	@Override
	public ResultGridStore getGridStore(TeacherOpus obj, int start, int limit, String orderBy) throws Exception {
		ResultGridStore rg = new ResultGridStore();
		rg.setList(dao.query(parametersMapBuilder.build(start, start + limit, orderBy, obj)));
		rg.setTotalProperty(dao.total(obj));
		return rg;
	}

	@Override
	public void insert(TeacherOpusHot obj) throws Exception {
		if(dao.getTeacherOpusHotByTeacherIdTotal(obj) > 0){
			dao.updateDate(obj);
		}else{
			dao.insertSelective(obj);
		}
		
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(ids);
	}

}

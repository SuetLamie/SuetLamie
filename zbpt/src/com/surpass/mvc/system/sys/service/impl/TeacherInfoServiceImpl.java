package com.surpass.mvc.system.sys.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.sys.dao.TeacherInfoMapper;
import com.surpass.mvc.system.sys.model.TeacherInfo;
import com.surpass.mvc.system.sys.service.TeacherInfoService;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： TeacherInfoServiceImpl<br>
 * 类描述： <br>
 * 创建人： 何扬<br>
 * 创建时间：2018-9-20<br>
 */
@Service("teacherInfoService")
public class TeacherInfoServiceImpl implements TeacherInfoService{
	
	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;

	@Autowired
	private TeacherInfoMapper dao;
	
	@Override
	public ResultGridStore getGridStore(TeacherInfo teacherInfo, int start, int limit, String orderBy) throws Exception {
		ResultGridStore rg = new ResultGridStore();
		rg.setList(dao.query(parametersMapBuilder.build(start, start + limit, orderBy, teacherInfo)));
		rg.setTotalProperty(dao.total(teacherInfo));
		return rg;
	}

	@Override
	public void insert(TeacherInfo teacherInfo) throws Exception {
		// TODO Auto-generated method stub
		teacherInfo.setId(UUID.randomUUID().toString());
		dao.insert(teacherInfo);
	}

	@Override
	public void update(TeacherInfo obj) throws Exception {
		// TODO Auto-generated method stub
		dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void delete(String ids) throws Exception {
		dao.deleteByPrimaryKey(ids);
	}

	@Override
	public TeacherInfo getTeacherInfoByAccoutId(String accountid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getTeacherInfoByAccoutId(accountid);
	}

}

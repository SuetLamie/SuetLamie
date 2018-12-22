package com.surpass.mvc.system.bas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.system.bas.dao.TeacherTypeMapper;
import com.surpass.mvc.system.bas.model.TeacherType;
import com.surpass.mvc.system.bas.service.TeacherTypeService;
@Service("teacherTypeService")
public class TeacherTypeServiceImpl implements TeacherTypeService{
	
	@Autowired
	private TeacherTypeMapper dao;
	
	@Override
	public List<TeacherType> getTeacherType(TeacherType teacherType) {
		return dao.getTeacherType(teacherType);
	}

}

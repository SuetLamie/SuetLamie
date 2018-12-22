/**
*
*/
package com.surpass.mvc.system.bas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.CourseHotMapper;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.CourseHot;
import com.surpass.mvc.system.bas.service.CourseHotService;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： CourseHotServiceImpl<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-7-7<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-7-7
 */
@Service("courseHotService")
public class CourseHotServiceImpl implements CourseHotService{

	
	/**
	 * 请求参数绑定器
	 */
	@Autowired
	private ParametersMapBuilder parametersMapBuilder;

	@Autowired
	private CourseHotMapper dao;
	
	/*
	*/
	@Override
	public ResultGridStore getGridStore(Course obj, int start, int limit, String orderBy) throws Exception {
		ResultGridStore rg = new ResultGridStore();
		rg.setList(dao.query(parametersMapBuilder.build(start, start + limit, orderBy, obj)));
		rg.setTotalProperty(dao.total(obj));
		return rg;
	}

	/*
	*/
	@Override
	public void insert(CourseHot obj) throws Exception {
		if(dao.getCourseHotByCourseIdTotle(obj) > 0){
			dao.updateDate(obj);
		}else{
			dao.insertSelective(obj);
		}
	}

	/*
	*/
	@Override
	public int delete(String ids) throws Exception {
		return dao.deleteByKey(ids);
	}

}

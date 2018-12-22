/**
 *
 */
package com.surpass.mvc.system.bas.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.CourseMapper;
import com.surpass.mvc.system.bas.dao.MyCourseMapper;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.model.MyCourse;
import com.surpass.mvc.system.bas.service.CourseService;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： CourseServiceImpl<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-23<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-23
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

	/**
	 * 请求参数绑定器
	 */
	@Autowired
	private ParametersMapBuilder parametersMapBuilder;

	@Autowired
	private CourseMapper dao;
	@Autowired
	private MyCourseMapper mdao;

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
	public void insert(Course obj) throws Exception {
		dao.insert(obj);
		Integer courseId = obj.getId();

		MyCourse mc = new MyCourse();
		mc.setId(UUID.randomUUID().toString());
		mc.setCourse_id(courseId);
		mc.setAct_user_id(obj.getAct_user_id());
		mc.setType(1);
		mdao.insert(mc);
	}

	/*
	*/
	@Override
	public void update(Course obj) throws Exception {
		dao.updateByKey(obj);
	}

	/*
	*/
	@Override
	public int delete(String ids, String actUserId) throws Exception {
		dao.deleteByKey(ids);
		return mdao.deleteByKey(ids, actUserId);
	}

	@Override
	public Course getcourse(Integer id) throws Exception {
		return dao.selectByKey(id);
	}


}

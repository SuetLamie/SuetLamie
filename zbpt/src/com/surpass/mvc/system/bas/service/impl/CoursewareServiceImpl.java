/**
*
*/
package com.surpass.mvc.system.bas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.system.bas.dao.CoursewareMapper;
import com.surpass.mvc.system.bas.model.Courseware;
import com.surpass.mvc.system.bas.service.CoursewareService;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： CoursewareServiceImpl<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-8-7<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-8-7
 */
@Service("coursewareService")
public class CoursewareServiceImpl implements CoursewareService {

	@Autowired
	private CoursewareMapper dao;
	
	/*
	*/
	@Override
	public ResultGridStore getGridStore(Courseware obj) throws Exception {
		ResultGridStore rg = new ResultGridStore();
		rg.setList(dao.query(obj));
		rg.setTotalProperty(rg.getList().size());
		return rg;
	}

	/*
	*/
	@Override
	public void insert(Courseware obj) throws Exception {
		dao.insertSelective(obj);
	}

	/*
	*/
	@Override
	public void update(Courseware obj) throws Exception {
		
	}

	/*
	*/
	@Override
	public int delete(String ids, String actUserId) throws Exception {
		return dao.deleteByKey(ids);
	}

}

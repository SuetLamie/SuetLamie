/**
 *
 */
package com.surpass.mvc.system.bas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.SysDictDetailMapper;
import com.surpass.mvc.system.bas.service.CourseTypeService;
import com.surpass.mvc.system.sys.model.DictDetail;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： CourseTypeServiceImpl<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-7-7<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-7-7
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl implements CourseTypeService {

	/**
	 * 请求参数绑定器
	 */
	@Autowired
	private ParametersMapBuilder parametersMapBuilder;

	@Autowired
	private SysDictDetailMapper dao;

	/*
	*/
	@Override
	public ResultGridStore getGridStore(DictDetail obj, int start, int limit, String orderBy) throws Exception {
		ResultGridStore rg = new ResultGridStore();
		rg.setList(dao.query(parametersMapBuilder.build(start, start + limit, orderBy, obj)));
		rg.setTotalProperty(dao.total(obj));
		return rg;
	}

	/*
	*/
	@Override
	public void insert(DictDetail obj) throws Exception {
		DictDetail nextId = dao.getNextDictDetailIdValue();
		DictDetail nextValue = dao.typeNextValue(obj.getDict_parent_id());
		
		obj.setDict_detail_id(nextId.getDict_detail_id());
		obj.setDict_detail_order(nextValue.getDict_detail_order());
		obj.setDict_detall_code(nextValue.getDict_detall_code());
		
		dao.insert(obj);
	}

	/*
	*/
	@Override
	public void update(DictDetail obj) throws Exception {
		dao.updateByKey(obj);
	}

	/*
	*/
	@Override
	public int delete(String ids) throws Exception {
		return dao.deleteByKey(ids);
	}

}

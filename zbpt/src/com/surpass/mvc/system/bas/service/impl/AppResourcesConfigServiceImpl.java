/**
*
*/
package com.surpass.mvc.system.bas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.bas.dao.AppResourcesConfigMapper;
import com.surpass.mvc.system.bas.model.AppResourcesConfig;
import com.surpass.mvc.system.bas.model.Course;
import com.surpass.mvc.system.bas.service.AppResourcesConfigService;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： AppResourcesConfigServiceImpl<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-7-14<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-7-14
 */
@Service("appResourcesConfigService")
public class AppResourcesConfigServiceImpl implements AppResourcesConfigService {
	/**
	 * 请求参数绑定器
	 */
	@Autowired
	private ParametersMapBuilder parametersMapBuilder;
	
	@Autowired
	private AppResourcesConfigMapper dao;
	
	/*
	 */
	@Override
	public ResultGridStore getGridStore(AppResourcesConfig obj, int start, int limit, String orderBy) throws Exception {
		ResultGridStore rg = new ResultGridStore();
		Map<String, Object> builder = parametersMapBuilder.build(start, start + limit, orderBy, obj);
		rg.setList(dao.query(builder));
		rg.setTotalProperty(dao.total(obj));
		return rg;
	}

	/*
	 */
	@Override
	public List<String> getAppResourcesConfigPageItem() throws Exception {
		return dao.getAppResourcesConfigPageItem();
	}

	/*
	 */
	@Override
	public int deleteByKey(String ids) throws Exception {
		return dao.deleteByKey(ids);
	}

	/*
	 */
	@Override
	public int insert(AppResourcesConfig record) throws Exception {
		return dao.insert(record);
	}

	/*
	 */
	@Override
	public Course selectByKey(Long id) throws Exception {
		return dao.selectByKey(id);
	}

	/*
	 */
	@Override
	public int updateByKey(AppResourcesConfig record) throws Exception {
		return dao.updateByKey(record);
	}

	/*
	*/
	@Override
	public List<AppResourcesConfig> query(AppResourcesConfig obj, int start, int limit, String orderBy) throws Exception {
		return dao.query(parametersMapBuilder.build(start, start + limit, orderBy, obj));
	}

}

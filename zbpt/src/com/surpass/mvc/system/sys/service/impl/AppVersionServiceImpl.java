/**
 * 
 */
package com.surpass.mvc.system.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.system.sys.dao.AppVersionMapper;
import com.surpass.mvc.system.sys.model.AppVersion;
import com.surpass.mvc.system.sys.service.AppVersionService;
import com.surpass.utils.ResultGridStore;

/**
 * 类名称： AppVersionServiceImpl<br>
 * 类描述： <br>
 * 创建人：pfwang<br>
 */
@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
	/**
	 * 请求参数绑定器
	 */
	@Autowired
	private ParametersMapBuilder parametersMapBuilder;
	
	@Resource
	private AppVersionMapper dao;
	
	@Override
	public ResultGridStore getGridStore(AppVersion obj, int start, int limit, String orderBy) throws Exception {
		ResultGridStore rg = new ResultGridStore();
		rg.setList(dao.query(parametersMapBuilder.build(start, limit, orderBy, obj)));
		rg.setTotalProperty(dao.total(obj));
		return rg;
	}

	@Override
	public int validate(String v_code) {
		return dao.validate(v_code);
	}

	@Override
	public void insert(AppVersion obj) throws Exception {
		dao.insert(obj);
	}

	@Override
	public void update(AppVersion obj) throws Exception {
		dao.updateByPrimaryKey(obj);
	}

	@Override
	public int delete(String ids) throws Exception {
		return dao.deleteByPrimaryKey(ids);
	}

	@Override
	public String getNewCode() throws Exception {
		return dao.getNewCode();
	}

	@Override
	public List<String> getPaths(String ids) throws Exception {
		// TODO Auto-generated method stub
		return dao.getPaths(ids);
	}

}

package com.surpass.mvc.weixin.miniprogram.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surpass.mvc.core.query.ParametersMapBuilder;
import com.surpass.mvc.weixin.miniprogram.dao.MiniprogramUserMapper;
import com.surpass.mvc.weixin.miniprogram.model.MiniprogramUser;
import com.surpass.mvc.weixin.miniprogram.service.MiniprogramUserService;
import com.surpass.utils.ResultGridStore;

/**
 * 
 * 类名称： MiniprogramUserServiceImpl<br>
 * 类描述： <br>
 * 创建人： 方曙强<br>
 * 创建时间：2018-5-10<br>
 * 修改人： 方曙强<br>
 * 修改时间：2018-5-10
 */
@Service("wechatUserInfoservice")
public class MiniprogramUserServiceImpl implements MiniprogramUserService {
	@Autowired
	private MiniprogramUserMapper mapper;

	@Override
	public int deleteByPrimaryKey(String openId) throws Exception {
		return mapper.deleteByPrimaryKey(openId);
	}

	@Override
	public int insert(MiniprogramUser record) throws Exception {
		int ct = mapper.insert(record);
		return ct;
	}

	@Override
	public int insertSelective(MiniprogramUser record) throws Exception {
		return mapper.insertSelective(record);
	}

	@Override
	public MiniprogramUser selectByPrimaryKey(String openId) {
		return mapper.selectByPrimaryKey(openId);
	}

	@Override
	public int updateByPrimaryKeySelective(MiniprogramUser record) throws Exception {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MiniprogramUser record) throws Exception {
		return mapper.updateByPrimaryKey(record);
	}

	/**
	 * 请求参数绑定器
	 */
	@Resource
	private ParametersMapBuilder parametersMapBuilder;

	@Override
	public ResultGridStore getGridStore(MiniprogramUser miniprogramUser, Integer start, Integer limit, String orderBy) {
		ResultGridStore rg = new ResultGridStore();
		try {
			List<Map<String, Object>> list = mapper.query(parametersMapBuilder.build(start, limit, orderBy, miniprogramUser));
			rg.setList(list);
			rg.setTotalProperty(mapper.total(miniprogramUser));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	@Override
	public void updateUserState(String[] ids, String state) throws Exception {
		mapper.updateUserState(ids, state);
	}

}

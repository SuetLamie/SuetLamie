package com.surpass.mvc.weixin.miniprogram.service;

import com.surpass.mvc.weixin.miniprogram.model.MiniprogramUser;
import com.surpass.utils.ResultGridStore;

/**
 * 
 * 类名称： MiniprogramUserService<br>
 * 类描述： <br>
 * 创建人： 方曙强<br>
 * 创建时间：2018-5-10<br>
 * 修改人： 方曙强<br>
 * 修改时间：2018-5-10
 */
public interface MiniprogramUserService {

	int deleteByPrimaryKey(String openId) throws Exception;

	int insert(MiniprogramUser record) throws Exception;

	int insertSelective(MiniprogramUser record) throws Exception;

	MiniprogramUser selectByPrimaryKey(String openId);

	int updateByPrimaryKeySelective(MiniprogramUser record) throws Exception;

	int updateByPrimaryKey(MiniprogramUser record) throws Exception;

	/**
	 * 
	 * 方法名称：getGridStore<br>
	 * 方法说明:获取微信用户分页列表 <br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2018-5-12<br>
	 * 
	 * @param miniprogramUser
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return
	 */
	ResultGridStore getGridStore(MiniprogramUser miniprogramUser, Integer start, Integer limit, String orderBy);

	void updateUserState(String[] split, String state) throws Exception;
}
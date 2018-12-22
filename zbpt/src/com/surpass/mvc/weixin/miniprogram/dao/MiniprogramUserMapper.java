package com.surpass.mvc.weixin.miniprogram.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.surpass.mvc.weixin.miniprogram.model.MiniprogramUser;

public interface MiniprogramUserMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table weixin_miniprogram_user
	 * 
	 * @mbggenerated Mon May 07 15:19:00 CST 2018
	 */
	int deleteByPrimaryKey(String openId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table weixin_miniprogram_user
	 * 
	 * @mbggenerated Mon May 07 15:19:00 CST 2018
	 */
	int insert(MiniprogramUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table weixin_miniprogram_user
	 * 
	 * @mbggenerated Mon May 07 15:19:00 CST 2018
	 */
	int insertSelective(MiniprogramUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table weixin_miniprogram_user
	 * 
	 * @mbggenerated Mon May 07 15:19:00 CST 2018
	 */
	MiniprogramUser selectByPrimaryKey(String openId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table weixin_miniprogram_user
	 * 
	 * @mbggenerated Mon May 07 15:19:00 CST 2018
	 */
	int updateByPrimaryKeySelective(MiniprogramUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table weixin_miniprogram_user
	 * 
	 * @mbggenerated Mon May 07 15:19:00 CST 2018
	 */
	int updateByPrimaryKey(MiniprogramUser record);

	/********************** 自动生成end *******************************/
	List<Map<String,Object>> query(Map<String, Object> build);

	Integer total(MiniprogramUser miniprogramUser);

	void updateUserState(@Param(value = "ids") String[] ids, @Param(value = "state") String state);

}
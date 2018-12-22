package com.surpass.mvc.system.bas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.surpass.mvc.system.sys.model.DictDetail;

public interface SysDictDetailMapper {
	/**
	 * 课程类型下一个的值
	 */
	DictDetail typeNextValue(@Param(value="dict_parent_id") String dict_parent_id) throws Exception;

	DictDetail getNextDictDetailIdValue() throws Exception;
	
	/**
	 * 查询 信息
	 */
	List<DictDetail> query(Map<String, Object> build) throws Exception;

	/**
	 * 查询 记录数
	 */
	Integer total(DictDetail record) throws Exception;

	/**
	 * 新增
	 */
	int insert(DictDetail record) throws Exception;

	/**
	 * 根据ID修改
	 */
	int updateByKey(DictDetail record) throws Exception;

	/**
	 * 删除
	 */
	int deleteByKey(@Param(value = "ids") String ids) throws Exception;
}
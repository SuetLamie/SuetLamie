package com.surpass.mvc.system.bas.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.surpass.mvc.system.bas.model.Course;

/**
 * 类名称： CourseMapper<br>
 * 类描述： <br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2018-5-23<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2018-5-23
 */
public interface CourseMapper {

	/**
	 * 查询 信息
	 */
	List<Course> query(Map<String, Object> build) throws Exception;

	/**
	 * 查询 记录数
	 */
	Integer total(Course record) throws Exception;

	/**
	 * 删除
	 */
	int deleteByKey(@Param(value = "ids") String ids) throws Exception;

	/**
	 * 新增
	 */
	int insert(Course record) throws Exception;

	/**
	 * 根据ID查询
	 */
	Course selectByKey(Integer id) throws Exception;

	/**
	 * 根据ID修改
	 */
	int updateByKey(Course record) throws Exception;

}
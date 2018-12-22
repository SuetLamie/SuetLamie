package com.surpass.mvc.system.bas.dao;

import org.apache.ibatis.annotations.Param;

import com.surpass.mvc.system.bas.model.MyCourse;

public interface MyCourseMapper {

	int insert(MyCourse record);

	int deleteByKey(@Param(value = "courseIds") String courseIds, @Param(value = "actUserId") String actUserId) throws Exception;

}
package com.surpass.mvc.system.bas.dao;

import java.util.List;
import java.util.Map;

import com.surpass.mvc.system.bas.model.TeacherOpus;

public interface TeacherOpusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher_opus
     *
     * @mbggenerated Thu Sep 20 17:40:45 CST 2018
     */
    int deleteByPrimaryKey(String ids);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher_opus
     *
     * @mbggenerated Thu Sep 20 17:40:45 CST 2018
     */
    int insert(TeacherOpus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher_opus
     *
     * @mbggenerated Thu Sep 20 17:40:45 CST 2018
     */
    int insertSelective(TeacherOpus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher_opus
     *
     * @mbggenerated Thu Sep 20 17:40:45 CST 2018
     */
    TeacherOpus selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher_opus
     *
     * @mbggenerated Thu Sep 20 17:40:45 CST 2018
     */
    int updateByPrimaryKeySelective(TeacherOpus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_teacher_opus
     *
     * @mbggenerated Thu Sep 20 17:40:45 CST 2018
     */
    int updateByPrimaryKey(TeacherOpus record);
    
    /**
	 * 查询 信息
	 */
	List<TeacherOpus> query(Map<String, Object> build) throws Exception;

	/**
	 * 查询 记录数
	 */
	Integer total(TeacherOpus record) throws Exception;

}
package com.surpass.mvc.system.bas.dao;

import com.surpass.mvc.system.bas.model.MemberIntegral;

public interface MemberIntegralMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral
     *
     * @mbggenerated Thu Oct 25 13:38:21 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral
     *
     * @mbggenerated Thu Oct 25 13:38:21 CST 2018
     */
    int insert(MemberIntegral record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral
     *
     * @mbggenerated Thu Oct 25 13:38:21 CST 2018
     */
    int insertSelective(MemberIntegral record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral
     *
     * @mbggenerated Thu Oct 25 13:38:21 CST 2018
     */
    MemberIntegral selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral
     *
     * @mbggenerated Thu Oct 25 13:38:21 CST 2018
     */
    int updateByPrimaryKeySelective(MemberIntegral record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member_integral
     *
     * @mbggenerated Thu Oct 25 13:38:21 CST 2018
     */
    int updateByPrimaryKey(MemberIntegral record);
}
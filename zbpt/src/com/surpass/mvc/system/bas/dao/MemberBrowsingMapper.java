package com.surpass.mvc.system.bas.dao;

import com.surpass.mvc.system.bas.model.MemberBrowsing;

public interface MemberBrowsingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_member_browsing
     *
     * @mbggenerated Sat Nov 17 14:11:56 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_member_browsing
     *
     * @mbggenerated Sat Nov 17 14:11:56 CST 2018
     */
    int insert(MemberBrowsing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_member_browsing
     *
     * @mbggenerated Sat Nov 17 14:11:56 CST 2018
     */
    int insertSelective(MemberBrowsing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_member_browsing
     *
     * @mbggenerated Sat Nov 17 14:11:56 CST 2018
     */
    MemberBrowsing selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_member_browsing
     *
     * @mbggenerated Sat Nov 17 14:11:56 CST 2018
     */
    int updateByPrimaryKeySelective(MemberBrowsing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_member_browsing
     *
     * @mbggenerated Sat Nov 17 14:11:56 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(MemberBrowsing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_member_browsing
     *
     * @mbggenerated Sat Nov 17 14:11:56 CST 2018
     */
    int updateByPrimaryKey(MemberBrowsing record);
}
package com.surpass.mvc.system.bas.dao;

import java.util.List;
import java.util.Map;

import com.surpass.mvc.system.bas.model.MemberRecharge;

public interface MemberRechargeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_member_recharge
     *
     * @mbggenerated Wed Oct 24 14:10:02 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_member_recharge
     *
     * @mbggenerated Wed Oct 24 14:10:02 CST 2018
     */
    int insert(MemberRecharge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_member_recharge
     *
     * @mbggenerated Wed Oct 24 14:10:02 CST 2018
     */
    int insertSelective(MemberRecharge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_member_recharge
     *
     * @mbggenerated Wed Oct 24 14:10:02 CST 2018
     */
    MemberRecharge selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_member_recharge
     *
     * @mbggenerated Wed Oct 24 14:10:02 CST 2018
     */
    int updateByPrimaryKeySelective(MemberRecharge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_member_recharge
     *
     * @mbggenerated Wed Oct 24 14:10:02 CST 2018
     */
    int updateByPrimaryKey(MemberRecharge record);
    
    List<MemberRecharge> getRechargeByMemberId(Integer memberid,int start, int limit, String orderBy);
    
    /**
	 * 
	 * 方法名称：query<br>
	 * 方法说明: 查询充值数据列表<br>
	 * 创建人: 何扬<br>
	 * 创建日期: 2018-8-29<br>
	 * 
	 * @param build
	 * @return
	 */
	List<MemberRecharge> query(Map<String, Object> build);
    
    Integer total(MemberRecharge record);
    
    Integer totalbymemberId(Integer memberid);
    
    Integer totalbyquery(Map<String, Object> build);
    
}
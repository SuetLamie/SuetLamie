package com.surpass.mvc.system.sys.dao;

import com.surpass.mvc.system.sys.model.FrmConfig;

public interface FrmConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table frm_config
     *
     * @mbggenerated Mon Dec 12 09:19:30 CST 2016
     */
    int deleteByPrimaryKey(String cfgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table frm_config
     *
     * @mbggenerated Mon Dec 12 09:19:30 CST 2016
     */
    int insert(FrmConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table frm_config
     *
     * @mbggenerated Mon Dec 12 09:19:30 CST 2016
     */
    int insertSelective(FrmConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table frm_config
     *
     * @mbggenerated Mon Dec 12 09:19:30 CST 2016
     */
    FrmConfig selectByPrimaryKey(String cfgId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table frm_config
     *
     * @mbggenerated Mon Dec 12 09:19:30 CST 2016
     */
    int updateByPrimaryKeySelective(FrmConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table frm_config
     *
     * @mbggenerated Mon Dec 12 09:19:30 CST 2016
     */
    int updateByPrimaryKeyWithBLOBs(FrmConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table frm_config
     *
     * @mbggenerated Mon Dec 12 09:19:30 CST 2016
     */
    int updateByPrimaryKey(FrmConfig record);
}
package com.surpass.mvc.system.sys.model;

import java.util.Date;

/*t_sys_integralset*/
public class SysIntegralSet {
    /**编号*/
    private String integralno;

    /**名称*/
    private String name;

    /**积分值*/
    private Integer point;

    /**创建时间*/
    private Date createdate;

    /**状态0：禁用1：启用*/
    private String status;

    /** get 编号*/
    public String getIntegralno() {
        return integralno;
    }

    /** set 编号*/
    public void setIntegralno(String integralno) {
        this.integralno = integralno == null ? null : integralno.trim();
    }

    /** get 名称*/
    public String getName() {
        return name;
    }

    /** set 名称*/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** get 积分值*/
    public Integer getPoint() {
        return point;
    }

    /** set 积分值*/
    public void setPoint(Integer point) {
        this.point = point;
    }

    /** get 创建时间*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 创建时间*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /** get 状态0：禁用1：启用*/
    public String getStatus() {
        return status;
    }

    /** set 状态0：禁用1：启用*/
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}
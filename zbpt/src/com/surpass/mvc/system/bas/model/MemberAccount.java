package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_sys_member_account*/
public class MemberAccount {
    /**账户编号*/
    private Integer id;

    /**金额*/
    private Double hasmoney;

    /**创建时间*/
    private Date createdate;

    /**更新时间*/
    private Date updatetime;

    private Integer memberid;

    /**已提现金额*/
    private Double usedmoney;

    /**积分*/
    private Integer integral;

    /**电子币*/
    private Double electronicmoney;

    /** get 账户编号*/
    public Integer getId() {
        return id;
    }

    /** set 账户编号*/
    public void setId(Integer id) {
        this.id = id;
    }

    /** get 金额*/
    public Double getHasmoney() {
        return hasmoney;
    }

    /** set 金额*/
    public void setHasmoney(Double hasmoney) {
        this.hasmoney = hasmoney;
    }

    /** get 创建时间*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 创建时间*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /** get 更新时间*/
    public Date getUpdatetime() {
        return updatetime;
    }

    /** set 更新时间*/
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    /** get 已提现金额*/
    public Double getUsedmoney() {
        return usedmoney;
    }

    /** set 已提现金额*/
    public void setUsedmoney(Double usedmoney) {
        this.usedmoney = usedmoney;
    }

    /** get 积分*/
    public Integer getIntegral() {
        return integral;
    }

    /** set 积分*/
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /** get 电子币*/
    public Double getElectronicmoney() {
        return electronicmoney;
    }

    /** set 电子币*/
    public void setElectronicmoney(Double electronicmoney) {
        this.electronicmoney = electronicmoney;
    }
}
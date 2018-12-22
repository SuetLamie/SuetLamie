package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_sys_member_recharge*/
public class MemberRecharge {
    /**充值记录编号*/
    private String id;

    /**会员ID。会员外键*/
    private Integer memberid;

    /**充值金额*/
    private Double money;

    /**充值时间*/
    private Date createtime;

    /**充值状态0:失败1：成功*/
    private String status;

    /**充值类型1：银行卡充值 2：支付宝充值3微信充值*/
    private String type;

    /** get 充值记录编号*/
    public String getId() {
        return id;
    }

    /** set 充值记录编号*/
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /** get 会员ID。会员外键*/
    public Integer getMemberid() {
        return memberid;
    }

    /** set 会员ID。会员外键*/
    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    /** get 充值金额*/
    public Double getMoney() {
        return money;
    }

    /** set 充值金额*/
    public void setMoney(Double money) {
        this.money = money;
    }

    /** get 充值时间*/
    public Date getCreatetime() {
        return createtime;
    }

    /** set 充值时间*/
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /** get 充值状态0:失败1：成功*/
    public String getStatus() {
        return status;
    }

    /** set 充值状态0:失败1：成功*/
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /** get 充值类型1：银行卡充值 2：支付宝充值3微信充值*/
    public String getType() {
        return type;
    }

    /** set 充值类型1：银行卡充值 2：支付宝充值3微信充值*/
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}
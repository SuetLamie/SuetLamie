package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_sys_member_presentrecord*/
public class MemberPresentRecord {
    /**编号*/
    private String id;

    /**会员编号*/
    private Integer memberid;

    /**提现金额*/
    private Double money;

    /**提现时间*/
    private Date cashtime;

    /**受理时间*/
    private Date dealtime;

    /**提现类型1.银行卡 2.支付宝*/
    private String type;

    /**银行卡卡号*/
    private String banknumber;

    /**户名*/
    private String username;

    /**开户银行*/
    private String bankname;

    /**支付宝账号*/
    private String alipayaccount;

    /**手机号码*/
    private String mobilephone;

    /**受理状态1.待处理2已处理3已驳回4已拒绝*/
    private String status;

    /**拒绝原因*/
    private String reason;

    /** get 编号*/
    public String getId() {
        return id;
    }

    /** set 编号*/
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /** get 会员编号*/
    public Integer getMemberid() {
        return memberid;
    }

    /** set 会员编号*/
    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    /** get 提现金额*/
    public Double getMoney() {
        return money;
    }

    /** set 提现金额*/
    public void setMoney(Double money) {
        this.money = money;
    }

    /** get 提现时间*/
    public Date getCashtime() {
        return cashtime;
    }

    /** set 提现时间*/
    public void setCashtime(Date cashtime) {
        this.cashtime = cashtime;
    }

    /** get 受理时间*/
    public Date getDealtime() {
        return dealtime;
    }

    /** set 受理时间*/
    public void setDealtime(Date dealtime) {
        this.dealtime = dealtime;
    }

    /** get 提现类型1.银行卡 2.支付宝*/
    public String getType() {
        return type;
    }

    /** set 提现类型1.银行卡 2.支付宝*/
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /** get 银行卡卡号*/
    public String getBanknumber() {
        return banknumber;
    }

    /** set 银行卡卡号*/
    public void setBanknumber(String banknumber) {
        this.banknumber = banknumber == null ? null : banknumber.trim();
    }

    /** get 户名*/
    public String getUsername() {
        return username;
    }

    /** set 户名*/
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /** get 开户银行*/
    public String getBankname() {
        return bankname;
    }

    /** set 开户银行*/
    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    /** get 支付宝账号*/
    public String getAlipayaccount() {
        return alipayaccount;
    }

    /** set 支付宝账号*/
    public void setAlipayaccount(String alipayaccount) {
        this.alipayaccount = alipayaccount == null ? null : alipayaccount.trim();
    }

    /** get 手机号码*/
    public String getMobilephone() {
        return mobilephone;
    }

    /** set 手机号码*/
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    /** get 受理状态1.待处理2已处理3已驳回4已拒绝*/
    public String getStatus() {
        return status;
    }

    /** set 受理状态1.待处理2已处理3已驳回4已拒绝*/
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /** get 拒绝原因*/
    public String getReason() {
        return reason;
    }

    /** set 拒绝原因*/
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}
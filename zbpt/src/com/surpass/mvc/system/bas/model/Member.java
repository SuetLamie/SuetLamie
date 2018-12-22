package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_sys_member*/
public class Member {
    /**会员ID*/
    private Integer id;

    /**会员姓名*/
    private String name;

    /**会员等级0:普通会员，1：超级VIP 2：白金会员3：代理商*/
    private String level;

    /**会员头像*/
    private String headimg;

    /**身份证号码*/
    private String cardnumber;

    /**身份证正面照*/
    private String cardfaceimg;

    /**身份证反面照*/
    private String cardconimg;

    /**手机号*/
    private String mobilephone;

    /**注册时间*/
    private Date createtime;

    /**推荐码*/
    private String commendno;

    /**注册方式0:平台注册1：微信公众号*/
    private String ways;

    /**邀请码*/
    private String personno;

    /**会员卡开始时间*/
    private Date begintime;

    /**会员卡到期时间*/
    private Date endtime;

    /**账号密码*/
    private String password;

    /**1:启用2：禁用*/
    private String status;

    /**用户的openId*/
    private String openId;

    /** get 会员ID*/
    public Integer getId() {
        return id;
    }

    /** set 会员ID*/
    public void setId(Integer id) {
        this.id = id;
    }

    /** get 会员姓名*/
    public String getName() {
        return name;
    }

    /** set 会员姓名*/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** get 会员等级0:普通会员，1：超级VIP 2：白金会员3：代理商*/
    public String getLevel() {
        return level;
    }

    /** set 会员等级0:普通会员，1：超级VIP 2：白金会员3：代理商*/
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /** get 会员头像*/
    public String getHeadimg() {
        return headimg;
    }

    /** set 会员头像*/
    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    /** get 身份证号码*/
    public String getCardnumber() {
        return cardnumber;
    }

    /** set 身份证号码*/
    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber == null ? null : cardnumber.trim();
    }

    /** get 身份证正面照*/
    public String getCardfaceimg() {
        return cardfaceimg;
    }

    /** set 身份证正面照*/
    public void setCardfaceimg(String cardfaceimg) {
        this.cardfaceimg = cardfaceimg == null ? null : cardfaceimg.trim();
    }

    /** get 身份证反面照*/
    public String getCardconimg() {
        return cardconimg;
    }

    /** set 身份证反面照*/
    public void setCardconimg(String cardconimg) {
        this.cardconimg = cardconimg == null ? null : cardconimg.trim();
    }

    /** get 手机号*/
    public String getMobilephone() {
        return mobilephone;
    }

    /** set 手机号*/
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    /** get 注册时间*/
    public Date getCreatetime() {
        return createtime;
    }

    /** set 注册时间*/
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /** get 推荐码*/
    public String getCommendno() {
        return commendno;
    }

    /** set 推荐码*/
    public void setCommendno(String commendno) {
        this.commendno = commendno == null ? null : commendno.trim();
    }

    /** get 注册方式0:平台注册1：微信公众号2：人工报单*/
    public String getWays() {
        return ways;
    }

    /** set 注册方式0:平台注册1：微信公众号2：人工报单*/
    public void setWays(String ways) {
        this.ways = ways == null ? null : ways.trim();
    }

    /** get 邀请码*/
    public String getPersonno() {
        return personno;
    }

    /** set 邀请码*/
    public void setPersonno(String personno) {
        this.personno = personno == null ? null : personno.trim();
    }

    /** get 会员卡开始时间*/
    public Date getBegintime() {
        return begintime;
    }

    /** set 会员卡开始时间*/
    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    /** get 会员卡到期时间*/
    public Date getEndtime() {
        return endtime;
    }

    /** set 会员卡到期时间*/
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /** get 账号密码*/
    public String getPassword() {
        return password;
    }

    /** set 账号密码*/
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /** get 1:启用2：禁用*/
    public String getStatus() {
        return status;
    }

    /** set 1:启用2：禁用*/
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /** get 用户的openId*/
    public String getOpenId() {
        return openId;
    }

    /** set 用户的openId*/
    public void setOpenid(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }
}
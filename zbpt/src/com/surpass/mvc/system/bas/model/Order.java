package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_order*/
public class Order {
    /**订单序号自增*/
    private Integer id;

    /**订单编号*/
    private String orderno;

    /**会员id*/
    private Integer memberid;

    /**手机号*/
    private String phone;

    /**金额*/
    private Double money;

    /**数量*/
    private Integer num;

    private String address;

    /**课程编号*/
    private Integer courseid;

    /**商品编号*/
    private Integer shopid;

    /**创建时间*/
    private Date createdate;

    /**更新时间*/
    private Date updatedate;

    /**类型1课程2商品*/
    private String type;

    /**支付状态1已支付2未支付*/
    private String paystatus;

    /**交易状态0进行中1已完成2已取消*/
    private String tradestatus;

    /**使用的积分*/
    private Integer integral;

    /**订单备注*/
    private String remark;

    /**配送状态0未收货1已收货3送货中*/
    private String deliverystatus;

    /**订单名称*/
    private String name;

    /**电子币*/
    private Double electronicmoney;

    /**商品详情*/
    private String info;

    /** get 订单序号自增*/
    public Integer getId() {
        return id;
    }

    /** set 订单序号自增*/
    public void setId(Integer id) {
        this.id = id;
    }

    /** get 订单编号*/
    public String getOrderno() {
        return orderno;
    }

    /** set 订单编号*/
    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    /** get 会员id*/
    public Integer getMemberid() {
        return memberid;
    }

    /** set 会员id*/
    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    /** get 手机号*/
    public String getPhone() {
        return phone;
    }

    /** set 手机号*/
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /** get 金额*/
    public Double getMoney() {
        return money;
    }

    /** set 金额*/
    public void setMoney(Double money) {
        this.money = money;
    }

    /** get 数量*/
    public Integer getNum() {
        return num;
    }

    /** set 数量*/
    public void setNum(Integer num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /** get 课程编号*/
    public Integer getCourseid() {
        return courseid;
    }

    /** set 课程编号*/
    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }

    /** get 商品编号*/
    public Integer getShopid() {
        return shopid;
    }

    /** set 商品编号*/
    public void setShopid(Integer shopid) {
        this.shopid = shopid;
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
    public Date getUpdatedate() {
        return updatedate;
    }

    /** set 更新时间*/
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    /** get 类型1课程2商品*/
    public String getType() {
        return type;
    }

    /** set 类型1课程2商品*/
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /** get 支付状态1已支付2未支付*/
    public String getPaystatus() {
        return paystatus;
    }

    /** set 支付状态1已支付2未支付*/
    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }

    /** get 交易状态0进行中1已完成2已取消*/
    public String getTradestatus() {
        return tradestatus;
    }

    /** set 交易状态0进行中1已完成2已取消*/
    public void setTradestatus(String tradestatus) {
        this.tradestatus = tradestatus == null ? null : tradestatus.trim();
    }

    /** get 使用的积分*/
    public Integer getIntegral() {
        return integral;
    }

    /** set 使用的积分*/
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /** get 订单备注*/
    public String getRemark() {
        return remark;
    }

    /** set 订单备注*/
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /** get 配送状态0未收货1已收货3送货中*/
    public String getDeliverystatus() {
        return deliverystatus;
    }

    /** set 配送状态0未收货1已收货3送货中*/
    public void setDeliverystatus(String deliverystatus) {
        this.deliverystatus = deliverystatus == null ? null : deliverystatus.trim();
    }

    /** get 订单名称*/
    public String getName() {
        return name;
    }

    /** set 订单名称*/
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** get 电子币*/
    public Double getElectronicmoney() {
        return electronicmoney;
    }

    /** set 电子币*/
    public void setElectronicmoney(Double electronicmoney) {
        this.electronicmoney = electronicmoney;
    }

    /** get 商品详情*/
    public String getInfo() {
        return info;
    }

    /** set 商品详情*/
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}
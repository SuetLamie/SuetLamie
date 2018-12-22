package com.surpass.mvc.system.sys.model;

import java.util.Date;

/*t_adminincome*/
public class AdminIncome {
    private Integer id;

    /**平台收入金额*/
    private Double money;

    /**创建时间*/
    private Date createdate;

    /**类型1:购物2.课程3.教师到期返佣4代理到期返佣*/
    private String type;

    /**订单编号*/
    private String orderid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** get 平台收入金额*/
    public Double getMoney() {
        return money;
    }

    /** set 平台收入金额*/
    public void setMoney(Double money) {
        this.money = money;
    }

    /** get 创建时间*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 创建时间*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /** get 类型1:购物2.课程3购物返佣4课程返佣*/
    public String getType() {
        return type;
    }

    /** set 类型1:购物2.课程3购物返佣4课程返佣*/
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /** get 订单编号*/
    public String getOrderid() {
        return orderid;
    }

    /** set 订单编号*/
    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }
}
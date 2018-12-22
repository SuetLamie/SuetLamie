package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_sys_member_rakeback*/
public class MemberRakeBack {
    private Integer id;

    /**会员编号*/
    private Integer memberid;

    /**返佣金额*/
    private Double money;

    /**返佣人编号*/
    private Integer personid;

    /**返佣时间*/
    private Date createdate;

    /**订单编号*/
    private String orderid;

    /**返佣类别 1课程 2产品3推荐4注册*/
    private String type;

    /**会员名称*/
    private String membername;

    /**推荐人名称*/
    private String personname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** get 会员编号*/
    public Integer getMemberid() {
        return memberid;
    }

    /** set 会员编号*/
    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    /** get 返佣金额*/
    public Double getMoney() {
        return money;
    }

    /** set 返佣金额*/
    public void setMoney(Double money) {
        this.money = money;
    }

    /** get 返佣人编号*/
    public Integer getPersonid() {
        return personid;
    }

    /** set 返佣人编号*/
    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    /** get 返佣时间*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 返佣时间*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /** get 订单编号*/
    public String getOrderid() {
        return orderid;
    }

    /** set 订单编号*/
    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    /** get 返佣类别 1课程 2产品3推荐4注册*/
    public String getType() {
        return type;
    }

    /** set 返佣类别 1课程 2产品3推荐4注册*/
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /** get 会员名称*/
    public String getMembername() {
        return membername;
    }

    /** set 会员名称*/
    public void setMembername(String membername) {
        this.membername = membername == null ? null : membername.trim();
    }

    /** get 推荐人名称*/
    public String getPersonname() {
        return personname;
    }

    /** set 推荐人名称*/
    public void setPersonname(String personname) {
        this.personname = personname == null ? null : personname.trim();
    }
}
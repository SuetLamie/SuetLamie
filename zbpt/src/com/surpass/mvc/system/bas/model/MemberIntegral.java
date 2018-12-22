package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*member_integral*/
public class MemberIntegral {
    private Integer id;

    /**会员编号*/
    private Integer memberid;

    /**签到时间*/
    private Date signdate;

    /**类型:1.签到2.购物3.推荐*/
    private String type;

    private Integer point;

    /**1增加0减少*/
    private String status;

    /**生成时间*/
    private Date createdate;

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

    /** get 签到时间*/
    public Date getSigndate() {
        return signdate;
    }

    /** set 签到时间*/
    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    /** get 类型:1.签到2.购物3.推荐*/
    public String getType() {
        return type;
    }

    /** set 类型:1.签到2.购物3.推荐*/
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    /** get 1增加0减少*/
    public String getStatus() {
        return status;
    }

    /** set 1增加0减少*/
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /** get 生成时间*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 生成时间*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
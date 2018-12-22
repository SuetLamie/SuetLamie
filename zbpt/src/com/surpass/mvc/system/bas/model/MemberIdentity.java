package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_member_identity*/
public class MemberIdentity {
    /**序号*/
    private Integer id;

    /**会员编号*/
    private Integer memberid;

    /**教师身份0:不是1:是*/
    private String teacheridentity;

    /**教师身份开始时间*/
    private Date teacherbegindate;

    /**教师身份结束时间*/
    private Date teacherenddate;

    /**商家身份0:不是1:是*/
    private String businessidentity;

    /**商家身份开始时间*/
    private Date businessbegindate;

    /**商家身份结束时间*/
    private Date businessenddate;

    /**代理商身份0:不是1:是*/
    private String agentidentity;

    /**代理商身份开始时间*/
    private Date agentbegindate;

    /**代理商身份结束时间*/
    private Date agentenddate;

    /**合伙人身份0:不是1:是*/
    private String partneridentity;

    /**合伙人身份开始时间*/
    private Date partnerbegindate;

    /**合伙人身份结束时间*/
    private Date partnerenddate;

    /** get 序号*/
    public Integer getId() {
        return id;
    }

    /** set 序号*/
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

    /** get 教师身份0:不是1:是*/
    public String getTeacheridentity() {
        return teacheridentity;
    }

    /** set 教师身份0:不是1:是*/
    public void setTeacheridentity(String teacheridentity) {
        this.teacheridentity = teacheridentity == null ? null : teacheridentity.trim();
    }

    /** get 教师身份开始时间*/
    public Date getTeacherbegindate() {
        return teacherbegindate;
    }

    /** set 教师身份开始时间*/
    public void setTeacherbegindate(Date teacherbegindate) {
        this.teacherbegindate = teacherbegindate;
    }

    /** get 教师身份结束时间*/
    public Date getTeacherenddate() {
        return teacherenddate;
    }

    /** set 教师身份结束时间*/
    public void setTeacherenddate(Date teacherenddate) {
        this.teacherenddate = teacherenddate;
    }

    /** get 商家身份0:不是1:是*/
    public String getBusinessidentity() {
        return businessidentity;
    }

    /** set 商家身份0:不是1:是*/
    public void setBusinessidentity(String businessidentity) {
        this.businessidentity = businessidentity == null ? null : businessidentity.trim();
    }

    /** get 商家身份开始时间*/
    public Date getBusinessbegindate() {
        return businessbegindate;
    }

    /** set 商家身份开始时间*/
    public void setBusinessbegindate(Date businessbegindate) {
        this.businessbegindate = businessbegindate;
    }

    /** get 商家身份结束时间*/
    public Date getBusinessenddate() {
        return businessenddate;
    }

    /** set 商家身份结束时间*/
    public void setBusinessenddate(Date businessenddate) {
        this.businessenddate = businessenddate;
    }

    /** get 代理商身份0:不是1:是*/
    public String getAgentidentity() {
        return agentidentity;
    }

    /** set 代理商身份0:不是1:是*/
    public void setAgentidentity(String agentidentity) {
        this.agentidentity = agentidentity == null ? null : agentidentity.trim();
    }

    /** get 代理商身份开始时间*/
    public Date getAgentbegindate() {
        return agentbegindate;
    }

    /** set 代理商身份开始时间*/
    public void setAgentbegindate(Date agentbegindate) {
        this.agentbegindate = agentbegindate;
    }

    /** get 代理商身份结束时间*/
    public Date getAgentenddate() {
        return agentenddate;
    }

    /** set 代理商身份结束时间*/
    public void setAgentenddate(Date agentenddate) {
        this.agentenddate = agentenddate;
    }

    /** get 合伙人身份0:不是1:是*/
    public String getPartneridentity() {
        return partneridentity;
    }

    /** set 合伙人身份0:不是1:是*/
    public void setPartneridentity(String partneridentity) {
        this.partneridentity = partneridentity == null ? null : partneridentity.trim();
    }

    /** get 合伙人身份开始时间*/
    public Date getPartnerbegindate() {
        return partnerbegindate;
    }

    /** set 合伙人身份开始时间*/
    public void setPartnerbegindate(Date partnerbegindate) {
        this.partnerbegindate = partnerbegindate;
    }

    /** get 合伙人身份结束时间*/
    public Date getPartnerenddate() {
        return partnerenddate;
    }

    /** set 合伙人身份结束时间*/
    public void setPartnerenddate(Date partnerenddate) {
        this.partnerenddate = partnerenddate;
    }
}
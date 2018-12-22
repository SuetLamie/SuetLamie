package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_member_browsing*/
public class MemberBrowsing {
    /**浏览记录编号*/
    private Integer id;

    /**会员编号*/
    private Integer memberid;

    /**创建时间*/
    private Date createdate;

    /**浏览类型1：课程2：作品3：商品*/
    private String type;

    /**浏览相应数据的id*/
    private Integer browsingid;

    /**历史记录*/
    private String historyinfo;

    /** get 浏览记录编号*/
    public Integer getId() {
        return id;
    }

    /** set 浏览记录编号*/
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

    /** get 创建时间*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 创建时间*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /** get 浏览类型1：课程2：作品3：商品*/
    public String getType() {
        return type;
    }

    /** set 浏览类型1：课程2：作品3：商品*/
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /** get 浏览相应数据的id*/
    public Integer getBrowsingid() {
        return browsingid;
    }

    /** set 浏览相应数据的id*/
    public void setBrowsingid(Integer browsingid) {
        this.browsingid = browsingid;
    }

    /** get 历史记录*/
    public String getHistoryinfo() {
        return historyinfo;
    }

    /** set 历史记录*/
    public void setHistoryinfo(String historyinfo) {
        this.historyinfo = historyinfo == null ? null : historyinfo.trim();
    }
}
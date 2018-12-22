package com.surpass.mvc.system.bas.model;

/*member_collection_opus*/
public class MemberCollectionOpus {
    /**会员收藏作品主键id*/
    private Integer id;

    /**会员ID*/
    private Integer memberid;

    /**作品ID*/
    private Integer opusid;

    /** get 会员收藏作品主键id*/
    public Integer getId() {
        return id;
    }

    /** set 会员收藏作品主键id*/
    public void setId(Integer id) {
        this.id = id;
    }

    /** get 会员ID*/
    public Integer getMemberid() {
        return memberid;
    }

    /** set 会员ID*/
    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    /** get 作品ID*/
    public Integer getOpusid() {
        return opusid;
    }

    /** set 作品ID*/
    public void setOpusid(Integer opusid) {
        this.opusid = opusid;
    }
}
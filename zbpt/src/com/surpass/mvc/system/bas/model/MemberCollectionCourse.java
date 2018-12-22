package com.surpass.mvc.system.bas.model;

/*member_collection_course*/
public class MemberCollectionCourse {
    private Integer id;

    /**会员ID*/
    private Integer memberid;

    /**课程ID*/
    private Integer courseid;

    public Integer getId() {
        return id;
    }

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

    /** get 课程ID*/
    public Integer getCourseid() {
        return courseid;
    }

    /** set 课程ID*/
    public void setCourseid(Integer courseid) {
        this.courseid = courseid;
    }
}
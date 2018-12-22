package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_teacher_opus_hot*/
public class TeacherOpusHot {
    private Integer id;

    /**作品外键ID*/
    private Integer opusid;

    /**老师姓名*/
    private String teachername;

    /**老师编号*/
    private String teacherid;

    /**创建时间*/
    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** get 作品外键ID*/
    public Integer getOpusid() {
        return opusid;
    }

    /** set 作品外键ID*/
    public void setOpusid(Integer opusid) {
        this.opusid = opusid;
    }

    /** get 老师姓名*/
    public String getTeachername() {
        return teachername;
    }

    /** set 老师姓名*/
    public void setTeachername(String teachername) {
        this.teachername = teachername == null ? null : teachername.trim();
    }

    /** get 老师编号*/
    public String getTeacherid() {
        return teacherid;
    }

    /** set 老师编号*/
    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }

    /** get 创建时间*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 创建时间*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
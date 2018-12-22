package com.surpass.mvc.system.bas.model;

import java.util.Date;

/*t_teacher_opus*/
public class TeacherOpus {
    /**作品ID*/
    private Integer id;

    /**作品名*/
    private String title;

    /**教师编号*/
    private String teacherid;

    /**教师名称*/
    private String teachernane;

    /**作品状态0禁用1启用*/
    private String status;

    /**视频路径*/
    private String path;

    /**封面图*/
    private String img;

    /**排序*/
    private Integer index;

    /**创建时间*/
    private Date createdate;
    
    /**简介*/
    private String synopsis;
    private String teacherimg;
    private String iscollection;
    private Integer memberid;
    
    
    public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	public String getIscollection() {
		return iscollection;
	}

	public void setIscollection(String iscollection) {
		this.iscollection = iscollection;
	}

	public String getTeacherimg() {
		return teacherimg;
	}

	public void setTeacherimg(String teacherimg) {
		this.teacherimg = teacherimg;
	}

	/** get 作品ID*/
    public Integer getId() {
        return id;
    }

    /** set 作品ID*/
    public void setId(Integer id) {
        this.id = id;
    }

    /** get 作品名*/
    public String getTitle() {
        return title;
    }

    /** set 作品名*/
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /** get 教师编号*/
    public String getTeacherid() {
        return teacherid;
    }

    /** set 教师编号*/
    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }

    /** get 教师名称*/
    public String getTeachernane() {
        return teachernane;
    }

    /** set 教师名称*/
    public void setTeachernane(String teachernane) {
        this.teachernane = teachernane == null ? null : teachernane.trim();
    }

    /** get 作品状态0禁用1启用*/
    public String getStatus() {
        return status;
    }

    /** set 作品状态0禁用1启用*/
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /** get 视频路径*/
    public String getPath() {
        return path;
    }

    /** set 视频路径*/
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /** get 封面图*/
    public String getImg() {
        return img;
    }

    /** set 封面图*/
    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    /** get 排序*/
    public Integer getIndex() {
        return index;
    }

    /** set 排序*/
    public void setIndex(Integer index) {
        this.index = index;
    }

    /** get 创建时间*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 创建时间*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    /** get 简介*/
    public String getSynopsis() {
        return synopsis;
    }

    /** set 简介*/
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis == null ? null : synopsis.trim();
    }
}
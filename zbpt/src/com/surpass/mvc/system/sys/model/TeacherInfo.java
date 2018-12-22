package com.surpass.mvc.system.sys.model;

import java.util.Date;

/*t_teacher_info*/
public class TeacherInfo {
    /**id*/
    private String id;

    /**昵称*/
    private String nickname;

    /**图像*/
    private String picture;

    /**介绍信息*/
    private String info;

    /**是否推荐0不推荐1推荐*/
    private String commond;

    /**创建日期*/
    private Date createdate;

    /**账户编号*/
    private String accountid;

    /**简介*/
    private String synopsis;
    
    /**会员编号*/
    private Integer memberid;
    
    public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	/** get id*/
    public String getId() {
        return id;
    }

    /** set id*/
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /** get 昵称*/
    public String getNickname() {
        return nickname;
    }

    /** set 昵称*/
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /** get 图像*/
    public String getPicture() {
        return picture;
    }

    /** set 图像*/
    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    /** get 介绍信息*/
    public String getInfo() {
        return info;
    }

    /** set 介绍信息*/
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /** get 是否推荐0不推荐1推荐*/
    public String getCommond() {
        return commond;
    }

    /** set 是否推荐0不推荐1推荐*/
    public void setCommond(String commond) {
        this.commond = commond == null ? null : commond.trim();
    }

    /** get 创建日期*/
    public Date getCreatedate() {
        return createdate;
    }

    /** set 创建日期*/
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /** get 账户编号*/
    public String getAccountid() {
        return accountid;
    }

    /** set 账户编号*/
    public void setAccountid(String accountid) {
        this.accountid = accountid == null ? null : accountid.trim();
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
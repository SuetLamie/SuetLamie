package com.surpass.mvc.system.bas.model;

public class Course {
    /**唯一标识*/
    private Integer id;

    private String title;

    /**类型  1010*/
    private String level;

    /**子类型 1009*/
    private String type;

    /**店*/
    private String store;

    /**老师ＩＤ*/
    private String teacher_id;

    /**老师名*/
    private String teacher_name;

    /**点击量*/
    private Integer clicks;

    /**积分*/
    private Integer ji_fen;

    /**价格*/
    private Double jia_ge;

    /**课程状态 1启用 0 禁用*/
    private Integer status;

    /**课程访问路径*/
    private String path;

    /**课程封面路径*/
    private String img;

    /**热门推介*/
    private Integer tui_jie;

    /**排序*/
    private Integer index;
    
    /**创建时间*/
    private String cjsj;
    
    /**教师头像*/
    private String teacherimg;
    
    
    ///////  查询参数  / ////////////////////
    private String act_user_id;
    
    
	public String getTeacherimg() {
		return teacherimg;
	}

	public void setTeacherimg(String teacherimg) {
		this.teacherimg = teacherimg;
	}

	public String getAct_user_id() {
		return act_user_id;
	}

	public void setAct_user_id(String act_user_id) {
		this.act_user_id = act_user_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public Integer getClicks() {
		return clicks;
	}

	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}

	public Integer getJi_fen() {
		return ji_fen;
	}

	public void setJi_fen(Integer ji_fen) {
		this.ji_fen = ji_fen;
	}

	public Double getJia_ge() {
		return jia_ge;
	}

	public void setJia_ge(Double jia_ge) {
		this.jia_ge = jia_ge;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getTui_jie() {
		return tui_jie;
	}

	public void setTui_jie(Integer tui_jie) {
		this.tui_jie = tui_jie;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

    
}
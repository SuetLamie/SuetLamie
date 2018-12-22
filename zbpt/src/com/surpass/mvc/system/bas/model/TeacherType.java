package com.surpass.mvc.system.bas.model;

/*t_teacher_type*/
public class TeacherType {
    private Integer id;

    /**教师编号*/
    private String teacherid;

    /**类别编号*/
    private String typeid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** get 教师编号*/
    public String getTeacherid() {
        return teacherid;
    }

    /** set 教师编号*/
    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }

    /** get 类别编号*/
    public String getTypeid() {
        return typeid;
    }

    /** set 类别编号*/
    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }
}
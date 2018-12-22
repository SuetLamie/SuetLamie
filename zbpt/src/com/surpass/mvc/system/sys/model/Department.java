package com.surpass.mvc.system.sys.model;

import java.util.Date;

/*t_sys_department*/
public class Department {
    /**部门编号*/
    private Integer bmbh;

    /**部门名称*/
    private String bmmc;

    /**上级部门*/
    private Integer sjbm;

    /**层级路径*/
    private String path;

    /**是否删除*/
    private String is_del;

    /**创建时间*/
    private Date c_time;

    /** get 部门编号*/
    public Integer getBmbh() {
        return bmbh;
    }

    /** set 部门编号*/
    public void setBmbh(Integer bmbh) {
        this.bmbh = bmbh;
    }

    /** get 部门名称*/
    public String getBmmc() {
        return bmmc;
    }

    /** set 部门名称*/
    public void setBmmc(String bmmc) {
        this.bmmc = bmmc == null ? null : bmmc.trim();
    }

    /** get 上级部门*/
    public Integer getSjbm() {
        return sjbm;
    }

    /** set 上级部门*/
    public void setSjbm(Integer sjbm) {
        this.sjbm = sjbm;
    }

    /** get 层级路径*/
    public String getPath() {
        return path;
    }

    /** set 层级路径*/
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /** get 是否删除*/
    public String getIs_del() {
        return is_del;
    }

    /** set 是否删除*/
    public void setIs_del(String is_del) {
        this.is_del = is_del == null ? null : is_del.trim();
    }

    /** get 创建时间*/
    public Date getC_time() {
        return c_time;
    }

    /** set 创建时间*/
    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }
}
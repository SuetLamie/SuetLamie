package com.surpass.mvc.system.sys.model;

import java.util.Date;

public class SysMenu {
    /**菜单编号*/
    private String id;

    /**上级菜单编号*/
    private String parentId;

    /**菜单内容*/
    private String text;

    private String cls;

    /**图标样式*/
    private String iconCls;

    /**是否是叶子*/
    private Boolean leaf;

    private Boolean isClass;

    /**是否点击展开*/
    private Boolean singleClickExpand;

    /**页面路径*/
    private String page_path;

    /**排序*/
    private Integer idx;

    /**状态 1：启用  0：禁用*/
    private String state;

    /**创建时间*/
    private Date c_time;

    /**最后一次更新时间*/
    private Date u_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls == null ? null : cls.trim();
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls == null ? null : iconCls.trim();
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Boolean getIsClass() {
        return isClass;
    }

    public void setIsClass(Boolean isClass) {
        this.isClass = isClass;
    }

    public Boolean getSingleClickExpand() {
        return singleClickExpand;
    }

    public void setSingleClickExpand(Boolean singleClickExpand) {
        this.singleClickExpand = singleClickExpand;
    }

    public String getPage_path() {
        return page_path;
    }

    public void setPage_path(String page_path) {
        this.page_path = page_path == null ? null : page_path.trim();
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }

    public Date getU_time() {
        return u_time;
    }

    public void setU_time(Date u_time) {
        this.u_time = u_time;
    }
}
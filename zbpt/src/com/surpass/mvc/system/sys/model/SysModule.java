package com.surpass.mvc.system.sys.model;

import java.util.Date;

public class SysModule {
    /**模块id*/
    private String id;

    /**模块名称*/
    private String text;

    /**上级模块编号*/
    private String parentId;

    /**图标样式*/
    private String iconCls;

    /**是否是叶子*/
    private Boolean leaf;

    /**点击展开*/
    private Boolean singleClickExpand;

    /**页面功能路径*/
    private String page_path;

    /**排序*/
    private Integer idx;

    /**备注*/
    private String bz;

    /**状态 1：启用  0：禁用*/
    private String state;

    /**创建时间*/
    private Date c_time;

    /**最后一次更新时间*/
    private Date u_time;

    private boolean checked;

    
    /**  
	 * 获取 checked  
	 * @return checked checked  
	 */
	public boolean isChecked() {
		return checked;
	}

	/**  
	 * 设置 checked  
	 * @param checked checked  
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**  
	 * 获取 id  
	 * @return id id  
	 */
	public String getId() {
		return id;
	}

	/**  
	 * 设置 id  
	 * @param id id  
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**  
	 * 获取 text  
	 * @return text text  
	 */
	public String getText() {
		return text;
	}

	/**  
	 * 设置 text  
	 * @param text text  
	 */
	public void setText(String text) {
		this.text = text;
	}

	public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
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
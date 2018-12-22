package com.surpass.mvc.system.sys.model;

public class SysRoleModule {
    private String id;

    /**模块id*/
    private String m_id;

    /**角色编号*/
    private String r_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id == null ? null : m_id.trim();
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id == null ? null : r_id.trim();
    }
}
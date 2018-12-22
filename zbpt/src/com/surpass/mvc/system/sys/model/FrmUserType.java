package com.surpass.mvc.system.sys.model;

/*frm_user_type*/
public class FrmUserType {
    /**用户类型主键*/
    private Integer utpId;

    /**类型名称*/
    private String utpName;

    /** get 用户类型主键*/
    public Integer getUtpId() {
        return utpId;
    }

    /** set 用户类型主键*/
    public void setUtpId(Integer utpId) {
        this.utpId = utpId;
    }

    /** get 类型名称*/
    public String getUtpName() {
        return utpName;
    }

    /** set 类型名称*/
    public void setUtpName(String utpName) {
        this.utpName = utpName == null ? null : utpName.trim();
    }
}
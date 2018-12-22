package com.surpass.mvc.system.sys.model;

/*frm_account_user*/
public class FrmAccountUser {
    /**主键*/
    private String act_user_id;

    /**帐号编号*/
    private Integer account_id;

    /**用户类型*/
    private Integer user_type;

    /**用户编号*/
    private String user_id;

    /**状态 1:启用  0:禁用*/
    private String state;

    /** get 主键*/
    public String getAct_user_id() {
        return act_user_id;
    }

    /** set 主键*/
    public void setAct_user_id(String act_user_id) {
        this.act_user_id = act_user_id == null ? null : act_user_id.trim();
    }

    /** get 帐号编号*/
    public Integer getAccount_id() {
        return account_id;
    }

    /** set 帐号编号*/
    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    /** get 用户类型*/
    public Integer getUser_type() {
        return user_type;
    }

    /** set 用户类型*/
    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    /** get 用户编号*/
    public String getUser_id() {
        return user_id;
    }

    /** set 用户编号*/
    public void setUser_id(String user_id) {
        this.user_id = user_id == null ? null : user_id.trim();
    }

    /** get 状态 1:启用  0:禁用*/
    public String getState() {
        return state;
    }

    /** set 状态 1:启用  0:禁用*/
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}
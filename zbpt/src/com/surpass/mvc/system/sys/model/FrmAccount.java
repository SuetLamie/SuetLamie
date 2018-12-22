package com.surpass.mvc.system.sys.model;

/*frm_account*/
public class FrmAccount {
    /**帐号主键*/
    private Integer account_id;

    /**登录帐号*/
    private String account;

    /**用户姓名*/
    private String username;

    /**用户密码*/
    private String password;

    /** get 帐号主键*/
    public Integer getAccount_id() {
        return account_id;
    }

    /** set 帐号主键*/
    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    /** get 登录帐号*/
    public String getAccount() {
        return account;
    }

    /** set 登录帐号*/
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /** get 用户姓名*/
    public String getUsername() {
        return username;
    }

    /** set 用户姓名*/
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /** get 用户密码*/
    public String getPassword() {
        return password;
    }

    /** set 用户密码*/
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}
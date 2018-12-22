package com.surpass.mvc.system.sys.model;

public class SysUserAccount extends SysUser {
	private String act_user_id;
	private String account_id;
	private String account;
	private String password;
	private String username;
	private String state;
	private String deptText;

	public String getAct_user_id() {
		return act_user_id;
	}

	public void setAct_user_id(String act_user_id) {
		this.act_user_id = act_user_id;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDeptText() {
		return deptText;
	}

	public void setDeptText(String deptText) {
		this.deptText = deptText;
	}

}

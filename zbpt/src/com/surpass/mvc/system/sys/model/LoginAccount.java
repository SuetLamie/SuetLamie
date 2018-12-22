package com.surpass.mvc.system.sys.model;

import java.io.Serializable;

public class LoginAccount extends FrmAccount implements Serializable {

	/**
	 * LoginAccount.java
	 */
	private static final long serialVersionUID = 1L;
	/** 用户类型 */
	private Integer userType;
	/** 用户类型 */
	private String userTypeText;
	/** 帐号用户 */
	private String accountUserId;
	/** 用户编号 */
	private String userId;
	/** 状态 */
	private String state;
	
	private String bmdm;

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserTypeText() {
		return userTypeText;
	}

	public void setUserTypeText(String userTypeText) {
		this.userTypeText = userTypeText;
	}

	public String getAccountUserId() {
		return accountUserId;
	}

	public void setAccountUserId(String accountUserId) {
		this.accountUserId = accountUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

package com.surpass.mvc.weixin.miniprogram.model;

import java.io.Serializable;
import java.util.Date;


/*weixin_miniprogram_user*/
public class MiniprogramUser implements Serializable {
	/**
	 * MiniprogramUser.java
	 */
	private static final long serialVersionUID = 1L;

	/** 用户唯一标识 */
	private String openId;

	/** 用户昵称 */
	private String nickName;

	/** 性别 */
	private String gender;

	/** 用户的语言，简体中文为zh_CN */
	private String language;

	/** 用户所在国家 */
	private String city;

	/** 用户所在省份 */
	private String province;

	/** 用户所在国家 */
	private String country;

	/** 用户头像 */
	private String avatarUrl;

	/** 用户绑定的手机号 */
	private String phoneNumber;

	/** 推荐码 */
	private String refOpenId;

	/** 状态:1启用 0禁用 */
	private String state;
	/** 创建时间 */
	private Date c_time;
	
	private Integer memberid;

	/** get 用户唯一标识 */
	public String getOpenId() {
		return openId;
	}

	/** set 用户唯一标识 */
	public void setOpenId(String openId) {
		this.openId = openId == null ? null : openId.trim();
	}

	/** get 用户昵称 */
	public String getNickName() {
		return nickName;
	}

	/** set 用户昵称 */
	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	/** get 性别 */
	public String getGender() {
		return gender;
	}

	/** set 性别 */
	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	/** get 用户的语言，简体中文为zh_CN */
	public String getLanguage() {
		return language;
	}

	/** set 用户的语言，简体中文为zh_CN */
	public void setLanguage(String language) {
		this.language = language == null ? null : language.trim();
	}

	/** get 用户所在国家 */
	public String getCity() {
		return city;
	}

	/** set 用户所在国家 */
	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	/** get 用户所在省份 */
	public String getProvince() {
		return province;
	}

	/** set 用户所在省份 */
	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	/** get 用户所在国家 */
	public String getCountry() {
		return country;
	}

	/** set 用户所在国家 */
	public void setCountry(String country) {
		this.country = country == null ? null : country.trim();
	}

	/** get 用户头像 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	/** set 用户头像 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
	}

	/** get 用户绑定的手机号 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/** set 用户绑定的手机号 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
	}

	/** get 推荐码 */
	public String getRefOpenId() {
		return refOpenId;
	}

	/** set 推荐码 */
	public void setRefOpenId(String refOpenId) {
		this.refOpenId = refOpenId == null ? null : refOpenId.trim();
	}

	/** get 状态:1启用 0禁用 */
	public String getState() {
		return state;
	}

	/** set 状态:1启用 0禁用 */
	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	/** get 创建时间 */
	public Date getC_time() {
		return c_time;
	}

	/** set 创建时间 */
	public void setC_time(Date c_time) {
		this.c_time = c_time;
	}

	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	
}
package com.surpass.mvc.system.sys.model;

import java.util.Date;

/*t_sys_role*/
public class Role {
	/** 角色编号 */
	private String r_id;

	/** 角色名称 */
	private String r_name;

	/** 用户类型 */
	private Integer user_type;

	/** 角色代码 */
	private String r_code;

	/** 备注 */
	private String bz;

	/** 状态 0：禁用 1：启用 */
	private String state;

	/** 创建时间 */
	private Date c_time;

	/** 最后一次更新时间 */
	private Date u_time;

	/** get 角色编号 */
	public String getR_id() {
		return r_id;
	}

	/** set 角色编号 */
	public void setR_id(String r_id) {
		this.r_id = r_id == null ? null : r_id.trim();
	}

	/** get 角色名称 */
	public String getR_name() {
		return r_name;
	}

	/** set 角色名称 */
	public void setR_name(String r_name) {
		this.r_name = r_name == null ? null : r_name.trim();
	}

	/** get 用户类型 */
	public Integer getUser_type() {
		return user_type;
	}

	/** set 用户类型 */
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	/** get 角色代码 */
	public String getR_code() {
		return r_code;
	}

	/** set 角色代码 */
	public void setR_code(String r_code) {
		this.r_code = r_code == null ? null : r_code.trim();
	}

	/** get 备注 */
	public String getBz() {
		return bz;
	}

	/** set 备注 */
	public void setBz(String bz) {
		this.bz = bz == null ? null : bz.trim();
	}

	/** get 状态 0：禁用 1：启用 */
	public String getState() {
		return state;
	}

	/** set 状态 0：禁用 1：启用 */
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

	/** get 最后一次更新时间 */
	public Date getU_time() {
		return u_time;
	}

	/** set 最后一次更新时间 */
	public void setU_time(Date u_time) {
		this.u_time = u_time;
	}
}
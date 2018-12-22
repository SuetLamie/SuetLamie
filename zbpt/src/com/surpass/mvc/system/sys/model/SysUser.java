package com.surpass.mvc.system.sys.model;

import java.util.Date;

/*t_sys_user*/
public class SysUser {
	/** 主键 */
	private Integer id;

	/** 性别 1：男 0：女 */
	private String sex;

	/** 年龄 */
	private Integer age;

	/** 电话 */
	private String tel;

	/** 电子邮件 */
	private String email;
	
	/** 员工编号**/
	private String emp_id;
	
	/** 备注 */
	private String bz;
	
	/** 部门id */
	private Integer dept_id;

	/** 创建时间 */
	private Date c_time;

	/** 最后一次更新时间 */
	private Date u_time;

	/** get 主键 */
	public Integer getId() {
		return id;
	}

	/** set 主键 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** get 性别 1：男 0：女 */
	public String getSex() {
		return sex;
	}

	/** set 性别 1：男 0：女 */
	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	/** get 年龄 */
	public Integer getAge() {
		return age;
	}

	/** set 年龄 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/** get 电话 */
	public String getTel() {
		return tel;
	}

	/** set 电话 */
	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	/** get 电子邮件 */
	public String getEmail() {
		return email;
	}

	/** set 电子邮件 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/** get 备注 */
	public String getBz() {
		return bz;
	}

	/** set 备注 */
	public void setBz(String bz) {
		this.bz = bz == null ? null : bz.trim();
	}

	/** get 部门id */
	public Integer getDept_id() {
		return dept_id;
	}

	/** set 部门id */
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
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
	

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
}
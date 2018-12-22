/**
 * 
 */
package com.surpass.mvc.system.sys.model;

import java.io.Serializable;

/**
 * 类名称： AppVersion<br>
 * 类描述： <br>
 * 创建人：pfwang<br>
 * 创建时间：2017-4-18<br>
 * 修改时间：2017-4-18
 */
public class AppVersion implements Serializable {

	/**
	 * AppVersion.java
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String v_name;

	private String v_code;

	private String path;

	private String is_update;

	private String bz;

	private String create_time;
	
	private String is_rollback;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getV_name() {
		return v_name;
	}

	public void setV_name(String v_name) {
		this.v_name = v_name;
	}

	public String getV_code() {
		return v_code;
	}

	public void setV_code(String v_code) {
		this.v_code = v_code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIs_update() {
		return is_update;
	}

	public void setIs_update(String is_update) {
		this.is_update = is_update;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getIs_rollback() {
		return is_rollback;
	}

	public void setIs_rollback(String is_rollback) {
		this.is_rollback = is_rollback;
	}
	
}

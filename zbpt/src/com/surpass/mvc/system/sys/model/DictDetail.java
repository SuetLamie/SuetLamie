package com.surpass.mvc.system.sys.model;

import java.util.Date;

public class DictDetail {
	
	private String dict_detail_id;
	private String dict_detall_code;
	private String dict_detail_name;
	private String dict_detail_meanings;
	private String dict_id;
	private Integer dict_detail_order;
	private String dict_parent_id;
	private String dict_parent_name;
	private String state;
	private String create_user;
	private Date create_time;
	
	
	public String getDict_detail_id() {
		return dict_detail_id;
	}
	public void setDict_detail_id(String dict_detail_id) {
		this.dict_detail_id = dict_detail_id;
	}
	public String getDict_detall_code() {
		return dict_detall_code;
	}
	public void setDict_detall_code(String dict_detall_code) {
		this.dict_detall_code = dict_detall_code;
	}
	public String getDict_detail_name() {
		return dict_detail_name;
	}
	public void setDict_detail_name(String dict_detail_name) {
		this.dict_detail_name = dict_detail_name;
	}
	public String getDict_detail_meanings() {
		return dict_detail_meanings;
	}
	public void setDict_detail_meanings(String dict_detail_meanings) {
		this.dict_detail_meanings = dict_detail_meanings;
	}
	public String getDict_id() {
		return dict_id;
	}
	public void setDict_id(String dict_id) {
		this.dict_id = dict_id;
	}
	public Integer getDict_detail_order() {
		return dict_detail_order;
	}
	public void setDict_detail_order(Integer dict_detail_order) {
		this.dict_detail_order = dict_detail_order;
	}
	public String getDict_parent_id() {
		return dict_parent_id;
	}
	public void setDict_parent_id(String dict_parent_id) {
		this.dict_parent_id = dict_parent_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getDict_parent_name() {
		return dict_parent_name;
	}
	public void setDict_parent_name(String dict_parent_name) {
		this.dict_parent_name = dict_parent_name;
	}
	
}

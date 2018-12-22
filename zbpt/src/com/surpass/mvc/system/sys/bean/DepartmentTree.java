package com.surpass.mvc.system.sys.bean;

import com.surpass.mvc.system.sys.model.Department;

public class DepartmentTree extends Department {
	private String id;
	private Boolean leaf;

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

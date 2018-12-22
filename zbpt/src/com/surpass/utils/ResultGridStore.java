package com.surpass.utils;

import java.util.List;
/**
 * 注释:处理Ext grid store里面的数据
 * @author fangshuqiang
 * @version 1.0  2011-8-4
 *
 */
public class ResultGridStore {
	private Integer totalProperty;
	private List<?> list;

	public Integer getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(Integer totalProperty) {
		this.totalProperty = totalProperty;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

}

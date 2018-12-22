package com.surpass.utils;

/**
 * 处理增删改返回前台的数据
 * 
 * @author fangshuqiang
 * @version 1.0 2011-8-4
 */
public class ResultMsg {
	private boolean success;
	private String msg;
	private Object data;
	private boolean execute;

	public ResultMsg() {
		super();
		this.success = true;
		this.execute = false;
	}
	
	public ResultMsg(boolean success, String msg, Object data, boolean execute){
		this.success = success;
		this.msg = msg;
		this.data = data;
		this.execute = execute;
	}

	public boolean isExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

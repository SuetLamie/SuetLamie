package com.surpass.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * 类名称： ResultApp<br>
 * 类描述： 返回 app 端的数据 结果集<br>
 * 创建人：pfwang<br>
 * 创建时间：2016-11-2<br>
 * 修改时间：2016-11-2
 */
public class ResultApp {
	private Object pageData;
	private boolean success;
	private String msg;

	public Object getPageData() {
		if (null != pageData) {
			// bean中的null 保留 key 存在于json中
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
//			String rtVl = PoliceUtil.replaceIP(gson.toJson(pageData));
			String rtVl = gson.toJson(pageData);
			return gson.fromJson(rtVl, pageData.getClass());
		} else {
			return pageData;
		}
	}

	public void setPageData(Object pageData) {
		this.pageData = pageData;
	}

	public ResultApp() {
		super();
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

	public ResultApp(Object pageData, String msg, boolean success) {
		super();
		this.pageData = pageData;
		this.msg = msg;
		this.success = success;
	}

	public ResultApp(String msg, boolean success) {
		super();
		this.pageData = "[]";
		this.msg = msg;
		this.success = success;
	}

}

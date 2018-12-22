package com.surpass.mvc.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.surpass.mvc.system.sys.model.LoginAccount;
import com.surpass.mvc.system.sys.model.SysLog;
import com.surpass.mvc.system.sys.service.SysLogService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

public class BaseController {

	@Autowired
	private SysLogService slservice;

	@Autowired
	protected HttpSession session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@ModelAttribute
	public void setModelAttribute(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	public String getParameter(String name) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getParameter(name) == null ? "" : request.getParameter(name).trim();
	}

	/**
	* 方法名称：getParameterOrderBy<br>
	* 方法说明: 获取排序方式<br>
	* 创建人: 王鹏飞<br>
	* 创建日期: 2017-1-5<br>
	* @param def 默认排序
	* @return
	 */
	public String getParameterOrderBy(String def) {
		String sort = request.getParameter("sort");
		String dir = request.getParameter("dir");
		if (null != dir && null != sort) {
			return " ORDER BY " + sort.trim() + " " + dir.trim();
		} else {
			return def;
		}
	}

	public String getParameter(String name, String def) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getParameter(name) == null ? def : request.getParameter(name).trim();
	}

	/**
	 * 
	 * 方法名称：initBinder<br>
	 * 方法说明: 日期格式化<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2016-11-17<br>
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	public LoginAccount getLoginUser() {
		return (LoginAccount) request.getSession().getAttribute(Constants.session.LOGIN_USER);
	}

	/**
	 * 类描述：添加系统 查询 日志 创建人：pfwang
	 * 
	 * @param business
	 *            处理业务 R.business.xxx
	 * @param oper_type
	 *            操作类型 R.oper.xxx
	 * @param key
	 *            数据主键
	 * @param msg
	 *            返回前端的结果姐
	 */
	public void logWriteInfo(String business, String oper_type, boolean execute) {
		try {
			String content = business + "-" + oper_type;
			SysLog sl = new SysLog(business, oper_type, "", content, (execute ? "1" : "2"), getLoginUser().getUserId());
			slservice.insert(sl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 类描述：添加系统日志 创建人：pfwang
	 * 
	 * @param business
	 *            处理业务 R.business.xxx
	 * @param oper_type
	 *            操作类型 R.oper.xxx
	 * @param key
	 *            数据主键
	 * @param msg
	 *            返回前端的结果姐
	 */
	public void logWriteInfo(String business, String oper_type, String key, ResultMsg msg) {
		try {
			key = key.replace("'", "");
			String content = msg.getMsg() + "主键ID【" + key + "】";
			SysLog sl = new SysLog(business, oper_type, key, content, (msg.isExecute() == true ? "1" : "2"),
					StringUtil.isNotEmpty(getLoginUser()) ? getLoginUser().getUserId() : key);
			slservice.insert(sl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 方法名称：resourcesPath<br>
	 * 方法说明: 获取合同扫描件上传路径<br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-12-21<br>
	 */
	public String getResourcesPathHT(){
		String pathnew = "htsm\\";
		String realPath = request.getSession().getServletContext().getRealPath("/");
		return realPath.replace("pcst\\", pathnew) + getLoginUser().getAccount() + "\\";
	}
	
	/**
	 * 
	 * 方法名称：getResourcesPathZD<br>
	 * 方法说明: <br>
	 * 创建人: 侯智<br>
	 * 创建日期: 2017-5-17<br>
	 * @return
	 */
	public String getResourcesPathZD(){
		String pathnew = "zdsm\\";
		String realPath = request.getSession().getServletContext().getRealPath("/");
		return realPath.replace("pcst\\", pathnew) + getLoginUser().getAccount() + "\\";
	}
}

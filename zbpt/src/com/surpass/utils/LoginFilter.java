package com.surpass.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.surpass.mvc.system.sys.model.LoginAccount;

/**
 * 
 * 
 * 项目名称：wuzi <br>
 * 类名称： LoginFilter <br>
 * 类描述：判断登录的过滤器 <br>
 * 创建人： 方曙强 <br>
 * 创建时间：Sep 23, 2011 <br>
 * 修改人： 方曙强 <br>
 * 修改时间：Sep 23, 2011 <br>
 * 修改备注： <br>
 * 
 * @version 1.0 Sep 23, 2011 <br>
 */
public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String url = httpRequest.getRequestURI();
		String back = httpRequest.getContextPath() + "/system/sys/loginController/loginPage.action";
		LoginAccount sysUser = (LoginAccount) httpRequest.getSession().getAttribute(Constants.session.LOGIN_USER);
		if (url.indexOf("/system/sys/loginController/loginPage.action") > 0 || url.indexOf("/system/sys/loginController/login.action") > 0) {
			// 登录的全部通过
			chain.doFilter(request, response);
		} else if (url.indexOf("/app/") > 0) {
			// app 端通过
			httpResponse.setHeader("Access-Control-Allow-Origin", "*"); 
			chain.doFilter(request, response);
		} else if (null == sysUser) {
			// 拦截失去session的
			// 两种处理方式：ExtAjax,普通的
			String ajaxTag = httpRequest.getHeader("Request-By");// Ext
			if (ajaxTag == null || !ajaxTag.trim().equalsIgnoreCase("Ext")) {
				httpResponse.sendRedirect(back);
			} else {
				httpResponse.addHeader("sessionstatus", "timeout");
				PrintWriter out = httpResponse.getWriter();
				out.print("{success:false,timeout:true,redirectUri:'" + back + "'}");
				out.flush();
				out.close();
			}
		} else {
			chain.doFilter(request, response);
		}
	}
}

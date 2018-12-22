/**  
 * @Title: KeywordController.java 
 * @Package com.mvc.sys.controller 
 * @author pfwang
 * @date 2016-1-6 上午10:50:34 
 * @version V1.0  
 */
package com.surpass.mvc.system.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.sys.model.SysLog;
import com.surpass.mvc.system.sys.service.SysLogService;
import com.surpass.utils.Constants;
import com.surpass.utils.ResultGridStore;
import com.surpass.utils.ResultMsg;
import com.surpass.utils.StringUtil;

/**
 * 
 * 类名称： SysLogController<br>
 * 类描述： 系统日志<br>
 * 创建人： 王鹏飞<br>
 * 创建时间：2016-4-1<br>
 * 修改人： 王鹏飞<br>
 * 修改时间：2016-4-1
 */
@Controller
@RequestMapping("system/sys/sysLogController")
public class SysLogController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysLogController.class);
	@Autowired
	private SysLogService service;
	
	
	/**
	 * 
	 * 方法名称：queryMain<br>
	 * 方法说明: <br>
	 * 创建人: 王鹏飞<br>
	 * 创建日期: 2016-4-1<br>
	 * @return
	 */
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "sys/sysLog.jsp";
	}
	

	@RequestMapping("getGridStore")
	@ResponseBody
	public ResultGridStore getGridStore(@ModelAttribute(value = "sLog") SysLog sLog, Integer start, Integer limit) {

		String orderBy = "";

		return service.getGridStore(sLog, start, limit, orderBy);
	}

	@RequestMapping("delete")
	@ResponseBody
	public ResultMsg delete() {
		ResultMsg msg = new ResultMsg();
		String ids = request.getParameter("ids");
		if (StringUtil.isNotEmpty(ids)) {
			try {
				// service.delete(ids);
				msg.setExecute(true);
				msg.setMsg("删除系统关键字成功!");
			} catch (Exception e) {
				msg.setExecute(false);
				msg.setMsg("删除系统关键字失败!");
				LOGGER.error(msg.getMsg(), e);
			}
		} else {
			msg.setExecute(false);
			msg.setMsg("数据传输失败!");
		}
		return msg;
	}

}

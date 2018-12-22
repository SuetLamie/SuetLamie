/**
 * 
 */
package com.surpass.mvc.system.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.surpass.mvc.core.BaseController;
import com.surpass.utils.Constants;

/**
 * 
 * 类名称： RequestPageController<br>
 * 类描述： <br>
 * 创建人： 方曙强<br>
 * 创建时间：2017-11-10<br>
 * 修改人： 方曙强<br>
 * 修改时间：2017-11-10
 */
@Controller
@RequestMapping("system/sys/requestPageController")
public class RequestPageController extends BaseController {

	private static final Logger log4j = LoggerFactory.getLogger(RequestPageController.class);

	@RequestMapping("queryMain")
	public String queryMain(String pagePath, String modeId) {
		// 以后加判断
		return Constants.path.RETURN_PATH_JSP + pagePath;
	}

}

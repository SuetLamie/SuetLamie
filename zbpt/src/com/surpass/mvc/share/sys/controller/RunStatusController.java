package com.surpass.mvc.share.sys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.axis.utils.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.share.sys.bean.RunStatus;
import com.surpass.utils.StringUtil;

/***
 * 
 * 类名称： RunStatusController<br>
 * 类描述：运行状态Controller <br>
 * 创建人： 方曙强<br>
 * 创建时间：2017-2-28<br>
 * 修改人： 方曙强<br>
 * 修改时间：2017-2-28
 */
@Controller
@RequestMapping("share/sys/runStatusController")
public class RunStatusController extends BaseController {
	private static final Logger log4j = LoggerFactory.getLogger(RunStatusController.class);

	/**
	 * 
	 * 方法名称：getSysRunStatus<br>
	 * 方法说明: 获取系统运行内存情况<br>
	 * 创建人: 方曙强<br>
	 * 创建日期: 2017-2-28<br>
	 * 
	 * @return
	 */
	@RequestMapping("getSysRunStatus")
	@ResponseBody
	public RunStatus getSysRunStatus() {
		double total = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);
		double max = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);
		max = (int) (max * 100) / 100.0;
		double free = (Runtime.getRuntime().freeMemory()) / (1024.0 * 1024);
		double useMemory = (int) ((total - free) * 100) / 100.0;
		double freeMemory = (int) ((max - useMemory) * 100) / 100.0;
		return new RunStatus(freeMemory, max, useMemory);
	}
}

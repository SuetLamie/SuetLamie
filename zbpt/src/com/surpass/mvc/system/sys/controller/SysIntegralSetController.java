package com.surpass.mvc.system.sys.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.sys.model.SysIntegralSet;
import com.surpass.mvc.system.sys.service.SysIntegralSetService;
import com.surpass.utils.ResultGridStore;

@Controller
@RequestMapping("system/sys/sysIntegralSetController")
public class SysIntegralSetController extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SysIntegralSetService service;
	
	@RequestMapping("getGridStore")
	@ResponseBody
	public ResultGridStore getGridStore(SysIntegralSet sis, Integer start, Integer limit) {
		String orderBy = getParameter("orderBy", "");
		return service.getGridStore(sis, start, limit, orderBy);
	}
	
}

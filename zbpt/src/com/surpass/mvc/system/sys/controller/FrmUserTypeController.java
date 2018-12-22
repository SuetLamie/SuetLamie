package com.surpass.mvc.system.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.surpass.mvc.core.BaseController;
import com.surpass.mvc.system.sys.bean.ComboBox;
import com.surpass.mvc.system.sys.service.FrmUserTypeService;

@Controller
@RequestMapping("system/sys/frmUserTypeController")
public class FrmUserTypeController extends BaseController {
	@Autowired
	private FrmUserTypeService service;

	@RequestMapping("getUserTypeComboxData")
	@ResponseBody
	public List<ComboBox> getUserTypeComboxData() {
		try {
			return service.getUserTypeComboxData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

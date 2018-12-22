package com.surpass.mvc.system.bas.controller;

import java.io.Serializable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.surpass.mvc.core.BaseController;
import com.surpass.utils.Constants;

@Controller
@RequestMapping("system/bas/OrderController")
public class OrderController extends BaseController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@RequestMapping("queryMain")
	public String queryMain() {
		return Constants.path.RETURN_PATH_SYSTEM + "order/order.jsp";
	}	
}

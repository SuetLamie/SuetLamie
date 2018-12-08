package linfei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageJumpController {
	@RequestMapping("/project_1")
	public String project_1() {
		return "project_1";
	}
	@RequestMapping("/project_2")
	public String project_2() {
		return "project_2";
	}
	@RequestMapping("/project_3")
	public String project_3() {
		return "project_3";
	}
}

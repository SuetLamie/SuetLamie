package linfei.controller;
/**
 * “≥√ÊController
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageJumpController {
	@RequestMapping("/info")
	public String info() {
		return "info";
	}
	@RequestMapping("/collection")
	public String collection() {
		return "collection";
	}
	@RequestMapping("/visit")
	public String visit() {
		return "visit";
	}
}

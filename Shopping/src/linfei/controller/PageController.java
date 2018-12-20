package linfei.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import linfei.pojo.Product;
import linfei.pojo.User;
import linfei.service.ProductServiceDao;
import linfei.service.UserServiceDao;
import linfei.util.ShoppingCat;
import linfei.util.ShoppingItem;
/**
 * 页面Controller
 */
@Controller
@RequestMapping("/page")
public class PageController {
	@Autowired
	private UserServiceDao userservice;
	@Autowired
	private ProductServiceDao productservice;
	/**
	 * 登录-账号account 密码password
	 * @param user
	 * @param password
	 * @param model
	 * @return String
	 */
	@RequestMapping("/login")
	public String login(String user,String password,HttpSession session){
		ShoppingCat shoppingcat=(ShoppingCat) session.getAttribute("ShoppingCat");
		if(shoppingcat==null){
			shoppingcat=new ShoppingCat();
			shoppingcat.setShoppingItemlist(new ArrayList<ShoppingItem>());
		}
		System.out.println(shoppingcat);
		System.out.println(shoppingcat.getShoppingItemlist());
		session.setAttribute("ShoppingCat", shoppingcat);
		User uu=new User();
		uu.setName(user);
		uu.setPassword(password);
		User u=userservice.selectUserByUserAndPassword(uu);
		System.out.println(u);
		if(u!=null){
			session.setAttribute("login", "success");
			return "menu";
		}else{
			return "forward:/index.jsp";
		}		
	}
	/**
	 * 商品管理
	 * @return String
	 */
	@RequestMapping("/productsmanagement")
	public String productsmanagement(Model model){
		List<Product> list=productservice.selectAllProduct();
		model.addAttribute("productlist", list);
		return "productsmanagement";
	}
	/**
	 * 订单管理
	 * @return String
	 */
	@RequestMapping("/ordermanagement")
	public String ordermanagement(){
		return "ordermanagement";
	}
	/**
	 * 用户管理
	 * @return String
	 */
	@RequestMapping("/usermanagement")
	public String usermanagement(Model model){
		List<User> list=userservice.selectAllUser();
		model.addAttribute("userlist", list);
		return "usermanagement";
	}
	/**
	 * 购物车
	 * @return String
	 */
	@RequestMapping("/shoppingcat")
	public String shoppingcat(HttpSession session){
		return "shoppingcat";
	}
	/**
	 * 修改商品页面回显
	 * @return
	 */
	@RequestMapping("/updateproduct")
	public String updateproduct(HttpServletRequest req,Model model){
		String productid_str=req.getParameter("id");
		int productid=Integer.parseInt(productid_str);
		Product product=productservice.selectByPrimaryKey(productid);
		model.addAttribute("product", product);
		return "updateproduct";
	}
	/**
	 * 订单页面
	 * @return String
	 */
	@RequestMapping("/orderPage")
	public String orderPage(HttpSession session){
		return "suborder";
	}
}

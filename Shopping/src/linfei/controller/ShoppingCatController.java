package linfei.controller;
/**
 * 购物车Controller
 */

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import linfei.pojo.Product;
import linfei.service.ProductServiceDao;
import linfei.util.ShoppingCat;
import linfei.util.ShoppingItem;

@Controller
@RequestMapping("/ShoppingCat")
public class ShoppingCatController {
	@Autowired
	ProductServiceDao productservice;	
	@RequestMapping("/addproduct")
	@ResponseBody
	public int addProduct(HttpServletRequest req,HttpSession session){
		ShoppingCat shoppingcat=(ShoppingCat) session.getAttribute("ShoppingCat");
		ShoppingItem shoppingitem=new ShoppingItem();
		Integer cun=0;
		String productid_str=req.getParameter("id");
		Integer productid=Integer.parseInt(productid_str);
		Product product=productservice.selectByPrimaryKey(productid);
		/**
		 * 判断是否大于库存
		 */
		if(product.getNum()>=1){			
			shoppingitem.setProduct(product);
			shoppingitem.setNum(1);
			product.setNum(product.getNum()-1);
			cun=productservice.updateByPrimaryKey(product);
			System.out.println(cun);
			System.out.println(shoppingitem);
			List<ShoppingItem> ShoppingItemlist=shoppingcat.addShoppingItemlist(shoppingitem,shoppingcat.getShoppingItemlist());		
			System.out.println(ShoppingItemlist.size());
			shoppingcat.setShoppingItemlist(ShoppingItemlist);
			session.setAttribute("ShoppingCat", shoppingcat);			
			return cun;
		}else{
			return cun;
		}						
	}
}

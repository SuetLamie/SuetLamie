package linfei.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import linfei.pojo.Product;
import linfei.service.ProductServiceDao;
/**
 * 商品Controller
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductServiceDao productservice;
	/**
	 * 根据id删除商品
	 * @param req
	 * @return int
	 */
	@RequestMapping("/delproductbyid")
	@ResponseBody
	public int delproductbyid(HttpServletRequest req){
		String productid_str=req.getParameter("id");
		int productid=Integer.parseInt(productid_str);
		int cun=productservice.deleteByPrimaryKey(productid);
		return cun;
	}
	/**
	 * 根据id修改商品
	 * @param req
	 * @return int
	 */
	@RequestMapping("/updateproductbyid")
	public String updateproductbyid(@ModelAttribute("product")Product product){
		System.out.println(product.getName());
		productservice.updateByPrimaryKey(product);
		return "redirect:/page/productsmanagement";
	}
	/**
	 * 添加商品
	 * @param req
	 * @return int
	 */
	@RequestMapping("/addProduct")
	@ResponseBody
	public Product addProduct(@ModelAttribute("product")Product product){
		productservice.insert(product);
		return product;
	}
}
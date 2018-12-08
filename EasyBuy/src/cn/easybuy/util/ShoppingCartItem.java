package cn.easybuy.util;

import cn.easybuy.entity.Product;

/**
 * 购物车条目
 * @author 林飞
 *
 */
public class ShoppingCartItem {
	private Product product;	//商品
	private Integer quantity;	//商品数量
	private Integer countPrice;	//总价格
	public ShoppingCartItem(Product product,Integer quantity) {
		this.product=product;
		this.quantity=quantity;
		this.countPrice=product.getPrice()*quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return countPrice;
	}
	public void setPrice(Integer countPrice) {
		this.countPrice = countPrice;
	}
}

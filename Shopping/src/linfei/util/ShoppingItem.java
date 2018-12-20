package linfei.util;

import linfei.pojo.Product;

public class ShoppingItem {
	private Product product;	//商品
	private Integer num;	//商品数量
	private Double totalprice;	//商品总价格
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取总价格
	 * @return Double
	 */
	public Double getTotalprice() {
		return this.product.getPrice()*this.num;
	}
}

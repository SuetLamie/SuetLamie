package cn.easybuy.util;

import java.util.List;

/**
 * 购物车
 * @author 林飞
 *
 */
public class ShoppingCart {
	private List<ShoppingCartItem> items;	//条目
	private Double sum;	//总金额
	public List<ShoppingCartItem> getItems() {
		return items;
	}
	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
}

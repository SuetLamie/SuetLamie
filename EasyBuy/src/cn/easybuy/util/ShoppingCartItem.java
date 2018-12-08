package cn.easybuy.util;

import cn.easybuy.entity.Product;

/**
 * ���ﳵ��Ŀ
 * @author �ַ�
 *
 */
public class ShoppingCartItem {
	private Product product;	//��Ʒ
	private Integer quantity;	//��Ʒ����
	private Integer countPrice;	//�ܼ۸�
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

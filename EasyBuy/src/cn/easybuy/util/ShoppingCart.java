package cn.easybuy.util;

import java.util.List;

/**
 * ���ﳵ
 * @author �ַ�
 *
 */
public class ShoppingCart {
	private List<ShoppingCartItem> items;	//��Ŀ
	private Double sum;	//�ܽ��
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

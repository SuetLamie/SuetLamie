package linfei.util;

import java.util.List;


public class ShoppingCat {
	private	List<ShoppingItem> shoppingItemlist;	//购物项
	private Integer shoppingtotalnum;	//购物总数量
	private Double shoppingtotalprice;	//购物总价格
	public List<ShoppingItem> getShoppingItemlist() {
		return this.shoppingItemlist;
	}
	public void setShoppingItemlist(List<ShoppingItem> shoppingItemlist) {
		this.shoppingItemlist = shoppingItemlist;
	}
	/**
	 * 添加购物车
	 * @param shoppingItemlist
	 */
	public List<ShoppingItem> addShoppingItemlist(ShoppingItem shoppingitem,List<ShoppingItem> list) {
		/**
		 * 判断是否存在购物车中
		 */
		for(ShoppingItem shitem:list){
			//如果存在-相等
			if(shitem.getProduct().getId()==shoppingitem.getProduct().getId()){
				shitem.setNum(shitem.getNum()+shoppingitem.getNum());
				System.out.println("购物车存在");
				return list;
			}
		}		
		System.out.println("不存在");
		list.add(shoppingitem);
		return list;
	}
	/**
	 * 获取总数量
	 * @return Integer
	 */
	public Integer getShoppingtotalnum() {
		Integer totalnum=0;
		for(ShoppingItem shop:this.shoppingItemlist){
			totalnum=totalnum+shop.getNum();
		}
		return totalnum;
	}
	/**
	 * 获取总价格
	 * @return Double
	 */
	public Double getShoppingtotalprice() {
		Double totalprice=0.0;
		for(ShoppingItem shop:this.shoppingItemlist){
			totalprice=totalprice+shop.getTotalprice();
		}		
		return totalprice;
	}
	
}

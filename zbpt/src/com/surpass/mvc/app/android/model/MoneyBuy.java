package com.surpass.mvc.app.android.model;
/** 电子币支付*/
public class MoneyBuy {
	/**金额*/
	private Double money;
	/**课程编号*/
	private Integer courseid;
	/**商品编号*/
	private Integer shopid;
	/**类型1课程2购物*/
	private String type;
	/**会员编号*/
	private Integer memberid;
	/**电子币*/
	private Double electronicmoney;
	
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getCourseid() {
		return courseid;
	}
	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public Double getElectronicmoney() {
		return electronicmoney;
	}
	public void setElectronicmoney(Double electronicmoney) {
		this.electronicmoney = electronicmoney;
	}
}

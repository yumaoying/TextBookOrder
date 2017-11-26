package com.textbookorder.domain;

import java.math.BigDecimal;

/**
 * 购物车条目
 */

public class Buycar {
	private int carid; // 购物车编号
	private Stock stock;// 库存，对应图书
	private User user; // 所属用户
	private int buymount; // 购买数量

	public Buycar() {
	}

	/**
	 * 小计方法
	 * 
	 * @return
	 */
	public double getSubtotal() {// 小计方法，但它没有对应的成员！
		BigDecimal d1 = new BigDecimal(stock.getTextbook().getSaleprice() + "");
		BigDecimal d2 = new BigDecimal(buymount + "");
		return d1.multiply(d2).doubleValue();
	}

	public int getCarid() {
		return carid;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public User getUser() {
		return user;
	}

	public int getBuymount() {
		return buymount;
	}

	public void setCarid(int carid) {
		this.carid = carid;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBuymount(int buymount) {
		this.buymount = buymount;
	}

	@Override
	public String toString() {
		return "Buycar [carid=" + carid + ", stock=" + stock + ", user=" + user
				+ ", buymount=" + buymount + ", carItems=" + "]";
	}

}
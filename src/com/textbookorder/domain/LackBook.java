package com.textbookorder.domain;

import java.util.Date;

/**
 * 缺书单领域对象
 */

public class LackBook {
	private int lackid;
	private int amount;
	private Date lackdate; // 登记日期
	private int itemid; // 来源于订单号
	private int stockid;// 来源于库存编号
	private int uid; // 缺书用户

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getLackid() {
		return lackid;
	}

	public int getAmount() {
		return amount;
	}

	public Date getLackdate() {
		return lackdate;
	}

	public int getItemid() {
		return itemid;
	}

	public int getStockid() {
		return stockid;
	}

	public void setLackid(int lackid) {
		this.lackid = lackid;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setLackdate(Date lackdate) {
		this.lackdate = lackdate;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public void setStockid(int stockid) {
		this.stockid = stockid;
	}
}
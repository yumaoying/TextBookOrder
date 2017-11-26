package com.textbookorder.domain;

import java.util.Date;

/**
 * 出库图书领域对象
 */

public class Out {
	private int outid; // 出库编号
	private Stock stock; // 库存编号
	private int outamount; // 出库数量
	private User user; // 购买用户
	private Date outdate; // 出库日期
	private Admin admin;// 经办人员
	private int itemid; // 出库订单

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getOutid() {
		return outid;
	}

	public Stock getStock() {
		return stock;
	}

	public int getOutamount() {
		return outamount;
	}

	public Date getOutdate() {
		return outdate;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setOutid(int outid) {
		this.outid = outid;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setOutamount(int outamount) {
		this.outamount = outamount;
	}

	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	@Override
	public String toString() {
		return "Out [outid=" + outid + ", stock=" + stock + ", outamount="
				+ outamount + ", user=" + user + ", outdate=" + outdate
				+ ", admin=" + admin + ", itemid=" + itemid + "]";
	}

}
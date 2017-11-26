package com.textbookorder.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单条目
 * 
 * @author Administrator
 * 
 */
public class OrderItem implements Serializable {
	private int orderItemid;// 订单编号
	private String itemid; // 订单项编号
	private Orders order; // 所属订单号
	private Stock stock;// 购买图书
	private int buyamount; // 购买数量
	private double subtotal;// 小计
	private int islack = 0; // 是否登记缺书
	private int isregisbuy = 0; // 是否登记购书
	private Date regisdate; // 登记购书时间
	private int state = 0;// 订单状态订单状态0未付款，1已付款但未发货，2已发货但未领书,3领书成功）
	private int fckstate;// 一审状态，1审核通过，2审核未通过，3审核通过
	private Admin fckadmin;// 初审审核人员
	private Date fckdate; // 初审审核日期
	private int sckstate = 0;// 二审状态，1审核通过，2审核未通过，3审核通过
	private Admin sckadmin;// 二次审核人员
	private Date sckdate; // 二次审核日期
	private Date paydate; // 付款日期
	private Date outdate;// 发货日期
	private Date enddate; // 用户确认领书，即交易结束时间
	private int isdelete; // 删除

	/**
	 * 小计方法
	 * 
	 * @return
	 */
	public double getSubtotal() {
		BigDecimal d1 = new BigDecimal(stock.getTextbook().getSaleprice() + "");
		BigDecimal d2 = new BigDecimal(buyamount + "");
		return d1.multiply(d2).doubleValue();
	}

	public int getOrderItemid() {
		return orderItemid;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public Orders getOrder() {
		return order;
	}

	public Stock getStock() {
		return stock;
	}

	public int getBuyamount() {
		return buyamount;
	}

	public int getIslack() {
		return islack;
	}

	public int getIsregisbuy() {
		return isregisbuy;
	}

	public Date getRegisdate() {
		return regisdate;
	}

	public int getState() {
		return state;
	}

	public int getFckstate() {
		return fckstate;
	}

	public Admin getFckadmin() {
		return fckadmin;
	}

	public Date getFckdate() {
		return fckdate;
	}

	public int getSckstate() {
		return sckstate;
	}

	public Admin getSckadmin() {
		return sckadmin;
	}

	public Date getSckdate() {
		return sckdate;
	}

	public Date getPaydate() {
		return paydate;
	}

	public Date getOutdate() {
		return outdate;
	}

	public int getIsdelete() {
		return isdelete;
	}

	public void setOrderItemid(int orderItemid) {
		this.orderItemid = orderItemid;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setBuyamount(int buyamount) {
		this.buyamount = buyamount;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public void setIslack(int islack) {
		this.islack = islack;
	}

	public void setIsregisbuy(int isregisbuy) {
		this.isregisbuy = isregisbuy;
	}

	public void setRegisdate(Date regisdate) {
		this.regisdate = regisdate;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setFckstate(int fckstate) {
		this.fckstate = fckstate;
	}

	public void setFckadmin(Admin fckadmin) {
		this.fckadmin = fckadmin;
	}

	public void setFckdate(Date fckdate) {
		this.fckdate = fckdate;
	}

	public void setSckstate(int sckstate) {
		this.sckstate = sckstate;
	}

	public void setSckadmin(Admin sckadmin) {
		this.sckadmin = sckadmin;
	}

	public void setSckdate(Date sckdate) {
		this.sckdate = sckdate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}

	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}

}
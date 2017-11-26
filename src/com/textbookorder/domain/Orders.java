package com.textbookorder.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * 
 * @author Administrator
 * 
 */

@SuppressWarnings("serial")
public class Orders implements Serializable {
	private String orderid; // 订单编号
	private User owner;// 订单所有者
	private Date ordertime; // 下单日期
	private double total; // 总计
	private List<OrderItem> orderItemList;// 当前订单下所有条目

	/**
	 * 计算订单
	 * 
	 * @return
	 */
	public double getTotal() {
		// 合计=所有条目的小计之和
		BigDecimal total = new BigDecimal("0");
		for (OrderItem orderItem : orderItemList) {
			BigDecimal subtotal = new BigDecimal("" + orderItem.getSubtotal());
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public String getOrderid() {
		return orderid;
	}

	public User getOwner() {
		return owner;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOid(String orderid) {
		this.orderid = orderid;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Override
	public String toString() {
		return "Orders [orderid=" + orderid + ", owner=" + owner
				+ ", ordertime=" + ordertime + ", total=" + total
				+ ", orderItemList=" + orderItemList + "]";
	}

	@Override
	public int hashCode() {
		return this.orderid.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == null)
			return true;
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Orders or = (Orders) obj;
		if (this.orderid.equals(or.getOrderid())) {
			return true;
		}
		return false;
	}

}
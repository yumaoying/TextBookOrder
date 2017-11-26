package com.textbookorder.domain;

import java.util.Date;

/**
 * 领书单
 */

public class Getbook {
	private int gid;
	private TextBook textbook;
	private User user;
	private int amount;
	private String location;
	private Date date;
	private int oitemid;// 订单号

	// private List<Getbook> booklist = new ArrayList<Getbook>(); // 用户领书单信息

	public int getGid() {
		return gid;
	}

	public int getOitemid() {
		return oitemid;
	}

	public void setOitemid(int oitemid) {
		this.oitemid = oitemid;
	}

	public TextBook getTextbook() {
		return textbook;
	}

	public User getUser() {
		return user;
	}

	public int getAmount() {
		return amount;
	}

	public String getLocation() {
		return location;
	}

	public Date getDate() {
		return date;
	}

	// public List<Getbook> getBooklist() {
	// return booklist;
	// }

	public void setGid(int gid) {
		this.gid = gid;
	}

	public void setTextbook(TextBook textbook) {
		this.textbook = textbook;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Getbook [gid=" + gid + ", textbook=" + textbook + ", user="
				+ user + ", amount=" + amount + ", location=" + location
				+ ", date=" + date + ", oitemid=" + oitemid + "]";
	}

}
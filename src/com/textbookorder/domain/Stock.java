package com.textbookorder.domain;

/**
 * 库存领域对象
 */

public class Stock {
	private int sid;
	private TextBook textbook; // 库存图书
	private int stocknumer;// 库存数量
	private short islack; // 是否登记缺书

	public Stock() {
	}

	public TextBook getTextbook() {
		return textbook;
	}

	public int getStocknumer() {
		return stocknumer;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setTextbook(TextBook textbook) {
		this.textbook = textbook;
	}

	public void setStocknumer(int stocknumer) {
		this.stocknumer = stocknumer;
	}

	public short getIslack() {
		return islack;
	}

	public void setIslack(short islack) {
		this.islack = islack;
	}

	@Override
	public String toString() {
		return "Stock [sid=" + sid + ", textbook=" + textbook + ", stocknumer="
				+ stocknumer + ", islack=" + islack + "]";
	}

}
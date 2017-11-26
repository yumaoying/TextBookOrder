package com.textbookorder.domain;

import java.util.Date;

/**
 * 采购单领域对象
 * 
 * @author Administrator
 * 
 */

public class WaitBuyBook {
	private int wid;
	private String isbn;
	private String bookname;
	private int plantmount; // 计划采购数量
	private String author;
	private String publisher;
	private Date publishtime;

	public int getWid() {
		return wid;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getBookname() {
		return bookname;
	}

	public int getPlantmount() {
		return plantmount;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public void setPlantmount(int plantmount) {
		this.plantmount = plantmount;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	@Override
	public String toString() {
		return "WaitBuyBook [wid=" + wid + ", isbn=" + isbn + ", bookname="
				+ bookname + ", plantmount=" + plantmount + ", author="
				+ author + ", publisher=" + publisher + ", publishtime="
				+ publishtime + "]";
	}

}

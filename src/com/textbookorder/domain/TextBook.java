package com.textbookorder.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 教材领域对象Textbook
 */

public class TextBook implements Serializable {
	private static final long serialVersionUID = 3493101481323363418L;
	private int id;
	private String isbn; // 图书编号ISBN
	private String bookname; // 图书名称
	private String author; // 作者
	private String publiser; // 出版社
	private Date publishtime; // 出版时间
	private float price; // 定价
	private float comeprice; // 进价
	private float saleprice; // 卖价
	private String details; // 详细信息
	private String bookpicture; // 图书图片
	private BookClass bookclass; // 图书类别

	public TextBook() {
	}

	public TextBook(String isbn, String bookname) {
		this.isbn = isbn;
		this.bookname = bookname;
	}

	public TextBook(int id, String isbn, String bookname, String author,
			String publiser, Date publishtime, float comePrice, float price,
			float saleprice, String details, String bookpicture,
			BookClass bookclass) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.bookname = bookname;
		this.author = author;
		this.publiser = publiser;
		this.publishtime = publishtime;
		this.price = price;
		this.details = details;
		this.bookpicture = bookpicture;
		this.bookclass = bookclass;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubliser() {
		return this.publiser;
	}

	public void setPubliser(String publiser) {
		this.publiser = publiser;
	}

	public Date getPublishtime() {
		return this.publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getComeprice() {
		return comeprice;
	}

	public float getSaleprice() {
		return saleprice;
	}

	public void setComeprice(float comeprice) {
		this.comeprice = comeprice;
	}

	public void setSaleprice(float saleprice) {
		this.saleprice = saleprice;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getBookpicture() {
		return this.bookpicture;
	}

	public void setBookpicture(String bookpicture) {
		this.bookpicture = bookpicture;
	}

	public BookClass getBookclass() {
		return bookclass;
	}

	public void setBookclass(BookClass bookclass) {
		this.bookclass = bookclass;
	}

	@Override
	public String toString() {
		return "TextBook [id=" + id + ", isbn=" + isbn + ", bookname="
				+ bookname + ", author=" + author + ", publiser=" + publiser
				+ ", publishtime=" + publishtime + ", price=" + price
				+ ", comeprice=" + comeprice + ", saleprice=" + saleprice
				+ ", details=" + details + ", bookpicture=" + bookpicture
				+ ", bookclass=" + bookclass + "]";
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
		TextBook b = (TextBook) obj;
		if (this.isbn.equals(b.getIsbn())
				&& this.bookname.equals(b.getBookname())
				&& this.price == b.getPrice()
				&& this.comeprice == b.getComeprice()
				&& this.getSaleprice() == b.getSaleprice()) {
			return true;
		}
		return false;
	}

}
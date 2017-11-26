package com.textbookorder.domain;

/**
 * 图书分类
 * 
 * @author Administrator
 * 
 */
public class BookClass {
	private int cid;
	private String classname;// 图书分类

	public BookClass() {

	}

	public BookClass(String classname) {
		super();
		this.classname = classname;
	}

	public String getClassname() {
		return classname;
	}

	public void setcid(int cid) {
		this.cid = cid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public String toString() {
		return "BookClass [cid=" + cid + ", classname=" + classname + "]";
	}

}

package com.textbookorder.domain;

import java.util.Date;

/**
 * 图书入库领域对象
 */

public class Come {
	private int comeid; // 入库编号
	private TextBook textbook; // 进货图书
	private int comenumber; // 进货数量
	private Date comedate; // 进货日期
	private String suplyer; // 供应商
	private String suplyphone; // 供应商联系方式
	private Admin admin; // 采购人员

	public Come() {

	}

	public int getComeid() {
		return comeid;
	}

	public TextBook getTextbook() {
		return textbook;
	}

	public int getComenumber() {
		return comenumber;
	}

	public Date getComedate() {
		return comedate;
	}

	public String getSuplyer() {
		return suplyer;
	}

	public String getSuplyphone() {
		return suplyphone;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setComeid(int comeid) {
		this.comeid = comeid;
	}

	public void setTextbook(TextBook textbook) {
		this.textbook = textbook;
	}

	public void setComenumber(int comenumber) {
		this.comenumber = comenumber;
	}

	public void setComedate(Date comedate) {
		this.comedate = comedate;
	}

	public void setSuplyer(String suplyer) {
		this.suplyer = suplyer;
	}

	public void setSuplyphone(String suplyphone) {
		this.suplyphone = suplyphone;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Come [comeid=" + comeid + ", textbook=" + textbook
				+ ", comenumber=" + comenumber + ", comedate=" + comedate
				+ ", suplyer=" + suplyer + ", suplyphone=" + suplyphone
				+ ", admin=" + admin + "]";
	}

}
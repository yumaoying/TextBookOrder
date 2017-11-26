package com.textbookorder.domain;

/**
 * Admin领域对象，对应数据库和表单
 */

public class Admin {
	private int aid; // 编号
	private String adminid; // 账号
	private String pwd;
	private String name;
	private String rights;
	private String telphone;
	private String email;
	private String right[];

	// right[0]:教材发行人员
	// right[1]:教材采购人员
	// righgt[2]:用户管理员
	// right[3]:库存管理员
	// right[4]:订单管理员

	public String[] getRight() {
		// 权限
		this.right = rights.split(",");
		return right;
	}

	public Admin() {
	}

	public Admin(String adminid, String pwd) {
		this.adminid = adminid;
		this.pwd = pwd;
	}

	public Admin(String adminid, String pwd, String name, String rights,
			String realname, String telphone, String email) {
		this.adminid = adminid;
		this.pwd = pwd;
		this.name = name;
		this.rights = rights;
		this.telphone = telphone;
		this.email = email;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAdminid() {
		return this.adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRights() {
		return this.rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", adminid=" + adminid + ", pwd=" + pwd
				+ ", name=" + name + ", rights=" + rights + ", telphone="
				+ telphone + ", email=" + email + "]";
	}

}
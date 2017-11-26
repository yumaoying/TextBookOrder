package com.textbookorder.domain;

/**
 * User领域对象，对应数据库表user
 * 
 * @author Administrator
 * 
 */
public class User {
	private int uid;
	private String userid; // 用户账号
	private String username; // 用户姓名
	private String userpwd; // 用户密码
	private int idennty; // 用户身份（学生为0，教师为1）
	private String telphone; // 手机号码
	private String school; // 学校
	private String academy; // 学院
	private String major; // 专业
	private String grade; // 年级
	private String email; // 电子邮箱
	private String code; // 邮箱激活码
	private boolean isstate; // 是否激活

	public User() {
	}

	public User(String userid, String username, String userpwd, int idennty) {
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.idennty = idennty;
	}

	public User(String userid, String username, String userpwd, int idennty,
			String telphone, String school, String academy, String major,
			String grade, String email, String code, boolean isstate) {
		super();
		this.userid = userid;
		this.username = username;
		this.userpwd = userpwd;
		this.idennty = idennty;
		this.telphone = telphone;
		this.school = school;
		this.academy = academy;
		this.major = major;
		this.grade = grade;
		this.email = email;
		this.code = code;
		this.isstate = isstate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return this.userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public int getIdennty() {
		return this.idennty;
	}

	public void setIdennty(int idennty) {
		this.idennty = idennty;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAcademy() {
		return this.academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isIsstate() {
		return isstate;
	}

	public void setIsstate(boolean isstate) {
		this.isstate = isstate;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", userid=" + userid + ", username="
				+ username + ", userpwd=" + userpwd + ", idennty=" + idennty
				+ ", telphone=" + telphone + ", school=" + school
				+ ", academy=" + academy + ", major=" + major + ", grade="
				+ grade + ", email=" + email + ", code=" + code + ", isstate="
				+ isstate + "]";
	}

}
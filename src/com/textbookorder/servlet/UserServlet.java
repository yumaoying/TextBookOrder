package com.textbookorder.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.Buycar;
import com.textbookorder.domain.Car;
import com.textbookorder.domain.User;
import com.textbookorder.service.AdminService;
import com.textbookorder.service.BuyCarService;
import com.textbookorder.service.StoreService;
import com.textbookorder.service.UserException;
import com.textbookorder.service.UserService;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 前台教师学生表述层
 * 
 * @author Administrator
 * 
 */
public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();
	private AdminService adminService = new AdminService();
	private BuyCarService carService = new BuyCarService();

	/**
	 * 用户注册
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 封装表单
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		// form.setCode(CommonUtils.uuid()); // 设置激活码
		Map<String, String> errors = new HashMap<String, String>();
		String userid = form.getUserid();
		if (userid == null || form.getUserid().trim().isEmpty()) {
			errors.put("userid", "账号不能为空！");
		}
		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		}
		String userpwd = form.getUserpwd();
		if (userpwd == null || userpwd.trim().isEmpty()) {
			errors.put("userpwd", "密码不能为空！");
		} else if (userpwd.length() < 6) {
			errors.put("userpwd", "密码长度必须大于6！");
		} else {
			String surepwd = request.getParameter("surepwd");
			if (surepwd == null || surepwd.trim().isEmpty()) {
				errors.put("surepwd", "确认密码不能为空！");
			} else if (!surepwd.equals(form.getUserpwd())) {
				request.setAttribute("surepwd", surepwd);
				errors.put("surepwd", "确认密码与输入密码不一致！");
			}
		}
		String email = form.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "Eamil不能为空！");
		} else if (!email.matches("\\w+@\\w+\\.\\w+")) {
			System.out.println("panduan" + email.matches("\\w+@\\w+\\.\\w+"));
			errors.put("email", "Email格式错误！");
		}
		String school = form.getSchool();
		if (school == null || school.trim().isEmpty()) {
			errors.put("school", "学校不能为空！");
		}
		// 判断是否存在错误信息，存在则将错误信息保存到表单并转发
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/register.jsp";
		}
		try {
			userService.register(form);
			request.setAttribute("msg", "注册成功!");

			/*
			 * User user = userService.login(form);
			 * request.getSession().setAttribute("login_user", user);
			 */
			return "f:/login.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/register.jsp";
		}

		// Properties pro = new Properties();
		// pro.load(this.getClass().getClassLoader()
		// .getResourceAsStream("email_template.properties"));
		// String host = pro.getProperty("host");
		// String uname = pro.getProperty("uname");
		// String pwd = pro.getProperty("pwd");
		// String from = pro.getProperty("from");
		// String to = form.getEmail();
		// String subject = pro.getProperty("subject");
		// String content = pro.getProperty("content");
		// content = MessageFormat.format(content, form.getCode());
		//
		// Session session = MailUtils.createSession(host, uname, pwd);
		//
		// Mail mail = new Mail(from, to, subject, content);
		// try {
		// MailUtils.send(session, mail);
		// } catch (MessagingException e) {
		// request.setAttribute("msg", "注册成功!但发送邮件失败");
		// return "f:/msg.jsp";
		// }
		// 转发
		// request.setAttribute("msg", "注册成功!请到邮箱激活");
		// return "f:/index.jsp";
	}

	/**
	 * 激活邮箱
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	/*
	 * public String active(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String code =
	 * request.getParameter("code"); try { userService.active(code);
	 * request.setAttribute("msg", "恭喜，您激活成功了！请马上登录！"); } catch (UserException
	 * e) { request.setAttribute("msg", e.getMessage()); } return "f:/msg.jsp";
	 * 
	 * }
	 */
	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		String idennty = request.getParameter("idennty");
		Map<String, String> errors = new HashMap<String, String>();
		if (userid == null || userid.trim().isEmpty()) {
			request.setAttribute("msg", "账号不能为空！");
			return "f:/login.jsp";
		}
		if (userpwd == null || userpwd.trim().isEmpty()) {
			request.setAttribute("msg", "密码不能为空！");
			return "f:/login.jsp";
		}
		if (idennty.equals("2")) {
			// 管理员登录
			Admin form = new Admin();
			form.setAdminid(userid);
			form.setPwd(userpwd);
			try {
				Admin admin = adminService.login(form);
				request.getSession().setAttribute("admin", admin);
				// 正确，重定向
				return "r:/Admin/AIndex.jsp";
			} catch (UserException e) {
				// 错误，回显转发
				request.setAttribute("msg", e.getMessage());
				return "f:/login.jsp";
			}
		} else {
			// 教师学生登录
			User form = new User();
			form.setUserid(userid);
			form.setUserpwd(userpwd);
			try {
				User user = userService.login(form);
				HttpSession session = request.getSession();
				String remain = request.getParameter("rem"); // 记住我
				if (remain != null && remain.equals("1")) {
					// 获取session的id
					String value = session.getId();
					// 设置cookie的value值
					Cookie cookie = new Cookie("JSESSIONID", value);
					// 设置延长时间
					cookie.setMaxAge(60 * 60 * 24);
					String path = request.getContextPath();
					cookie.setPath(path);
					response.addCookie(cookie);
				}
				session.setAttribute("login_user", user);
				// 获取购物车
				Car car = new Car();
				Map<Integer, Buycar> map = carService.carItemMap(user);
				car.setMap(map);
				request.getSession().setAttribute("car", car);
				new StoreService().findAllStock();
				return "r:/TextBookServlet?method=findAllStock";
			} catch (UserException e) {
				request.setAttribute("msg", e.getMessage());
				return "f:/login.jsp";

			}

		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		Map<String, String> errors = new HashMap<String, String>();
		String userid = form.getUserid();
		System.out.println(userid);
		User usersession = (User) request.getSession().getAttribute(
				"login_user");
		if (usersession == null) {
			return "r:/login.jsp";
		}
		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		}
		String userpwd = form.getUserpwd();
		if (userpwd == null || userpwd.trim().isEmpty()) {
			errors.put("userpwd", "密码不能为空！");
		} else if (userpwd.length() < 6) {
			errors.put("userpwd", "密码长度必须大于6！");
		}
		String email = form.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("username", "邮箱不能为空！");
		}
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "Eamil不能为空！");
		} else if (!email.matches("\\w+@\\w+\\.\\w+")) {
			errors.put("email", "Email格式错误！");
		}
		String school = form.getSchool();
		if (school == null || school.trim().isEmpty()) {
			errors.put("school", "学校不能为空,教材将发货到学校教材库！");
		}

		// 判断是否存在错误信息，存在则将错误信息保存到表单并转发
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			return "f:/UserManage.jsp";
		}
		try {
			userService.modify(form);
			form.setUid(usersession.getUid());
			request.getSession().setAttribute("login_user", form);
			request.setAttribute("msg", "修改成功");
			return "f:/UserManage.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/UserManage.jsp";
		}

	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String modifyPwd(HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		String userpwd = request.getParameter("userpwd");
		String vcode = request.getParameter("vercode");
		String surepwd = request.getParameter("surepwd");
		Map<String, String> errors = new HashMap<String, String>();
		if (userpwd == null || userpwd.trim().isEmpty()) {
			errors.put("userpwd", "密码不能为空！");
		} else if (userpwd.length() < 6) {
			errors.put("userpwd", "密码长度必须大于6！");
		} else {
			if (surepwd == null || surepwd.trim().isEmpty()) {
				errors.put("surepwd", "确认密码不能为空！");
			} else if (!surepwd.equals(userpwd)) {
				request.setAttribute("surepwd", surepwd);
				errors.put("surepwd", "确认密码与输入密码不一致！");
			}
		}
		if (vcode == null || vcode.trim().isEmpty()) {
			request.setAttribute("msg", "验证码不能为空");
			errors.put("vcode", "验证码不能为空！");
		}
		String vCode = (String) request.getSession().getAttribute("vercode");
		if (!vCode.equalsIgnoreCase(vcode)) {
			errors.put("vcode", "验证码错误！");
		}
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			return "f:/modifyPassw.jsp";
		}
		try {
			user.setUserpwd(userpwd);
			userService.modify(user);
			request.getSession().setAttribute("login_user", user);
			return "f:/UserManage.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", user);
			return "f:/modifyPassw.jsp";
		}

	}

	/**
	 * 找回密码
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findPass(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String newpwd = request.getParameter("newpwd");
		String surepwd = request.getParameter("surepwd");
		String findpassvercode = request.getParameter("findpassvercode");
		if (newpwd == null || newpwd.trim().isEmpty()) {
			request.setAttribute("msg", "密码不能为空！");
			return "f:/findPassw.jsp";
		} else if (newpwd.length() < 6) {
			request.setAttribute("msg", "密码长度必须大于6！");
			return "f:/findPassw.jsp";
		} else {
			if (surepwd == null || surepwd.trim().isEmpty()) {
				request.setAttribute("msg", "确认密码不能为空");
				return "f:/findPassw.jsp";
			} else if (!surepwd.equals(newpwd)) {
				request.setAttribute("msg", "确认密码与输入密码不一致");
				return "f:/findPassw.jsp";
			}
		}
		if (findpassvercode == null || findpassvercode.trim().isEmpty()) {
			request.setAttribute("msg", "验证码不能为空");
			return "f:/findPassw.jsp";
		}
		String vCode = (String) request.getSession().getAttribute("vercode");
		if (!vCode.equalsIgnoreCase(findpassvercode)) {
			request.setAttribute("msg", "验证码错误！");
			return "f:/findPassw.jsp";
		}
		return "f:/findPassw.jsp";
	}

	/**
	 * 用户退出
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		return "r:/login.jsp";

	}
}

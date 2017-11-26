package com.textbookorder.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.User;
import com.textbookorder.service.UserException;
import com.textbookorder.service.UserService;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 管理员管理用户教师，学生
 * 
 * @author Administrator
 * 
 */
public class MemberServlet extends BaseServlet {
	private UserService userService = new UserService();

	/**
	 * 查找所有的用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		List<User> userlist = userService.findAllUser();
		request.setAttribute("userlist", userlist);
		return "f:/Admin/UserM/AhySearch.jsp";
	}

	/**
	 * 查找所有的老师
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllTeacher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		List<User> userlist = userService.findAllTeacher();
		request.setAttribute("userlist", userlist);
		return "f:/Admin/UserM/AhySearch.jsp";
	}

	/**
	 * 查找所有的学生
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		List<User> userlist = userService.findAllStudent();
		request.setAttribute("userlist", userlist);
		return "f:/Admin/UserM/AhySearch.jsp";
	}

	/**
	 * 用户查找
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findUserGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String userid = request.getParameter("userid");
		String lookup = request.getParameter("lookup");
		String find = request.getParameter("find");
		List<User> userlist = new ArrayList<User>();
		System.out.println("find" + find);
		if (userid == null || userid.trim().isEmpty()) {
			if (lookup != null && !lookup.trim().isEmpty()) {
				if (lookup.trim().equals("2")) {
					// 查找所有老师
					return findAllTeacher(request, response);
				} else if (lookup.trim().equals("3")) {
					// 查找所有学生
					return findAllStudent(request, response);
				}
			}
		} else {
			if (find != null && !find.trim().isEmpty()) {
				if (find.equals("1")) {
					User user = userService.load(userid);
					System.out.println(user);
					userlist.add(user);
					request.setAttribute("userlist", userlist);
					return "f:/Admin/AIndex.jsp";
				}
				if (find.equals("2")) {
					userlist = userService.findByName(userid);
					request.setAttribute("userlist", userlist);
					return "f:/Admin/UserM/AhySearch.jsp";
				}
				if (find.equals("3")) {
					userlist = userService.findByPhone(userid);
					request.setAttribute("userlist", userlist);
					return "f:/Admin/UserM/AhySearch.jsp";
				}
				if (find.equals("4")) {
					userlist = userService.findGroup(userid);
					request.setAttribute("userlist", userlist);
					return "f:/Admin/UserM/AhySearch.jsp";
				}
			}
		}
		// 查找全部用户
		return findAll(request, response);
	}

	/**
	 * 用户添加
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		// 封装表单
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
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
		}

		String email = form.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "Eamil不能为空！");
		} else if (!email.matches("\\w+@\\w+\\.\\w+")) {
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
			return "f:/Admin/UserM/AhyAdd.jsp";
		}
		try {
			userService.register(form);
			request.setAttribute("msg", "添加成功!");
			return findAll(request, response);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/Admin/UserM/AhyAdd.jsp";
		}
	}

	/**
	 * 修改用户信息前加载用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public String modifyPre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String userid = request.getParameter("userid");
		User form = userService.load(userid);
		request.setAttribute("form", form);
		return "f:/Admin/UserM/AhyModify.jsp";
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
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		Map<String, String> errors = new HashMap<String, String>();
		User usersession = userService.loadByuid(form.getUid());
		if (usersession == null) {
			request.setAttribute("msg", "该用户不存在，无法修改!");
			request.setAttribute("form", form);
			return "f:/Admin/UserM/AhyModify.jsp";
		}
		String username = form.getUsername();
		if (username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空");
			username = usersession.getUsername();
			form.setUsername(username);
		}
		String userpwd = form.getUserpwd();
		if (userpwd == null || userpwd.trim().isEmpty()) {
			errors.put("userpwd", "密码不能为空！");
		} else if (userpwd.length() < 6) {
			errors.put("userpwd", "密码长度必须大于6！");
		}
		String email = form.getEmail();
		if (email == null || email.trim().isEmpty()) {
			errors.put("email", "email不能为空");
			email = usersession.getEmail();
			form.setEmail(email);
		}
		String school = form.getSchool();
		if (school == null || school.trim().isEmpty()) {
			errors.put("school", "school不能为空");
			school = usersession.getSchool();
			form.setSchool(school);
		}

		// 判断是否存在错误信息，存在则将错误信息保存到表单并转发
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			return "f:/Admin/UserM/AhyModify.jsp";
		}
		try {
			userService.modify(form);
			request.setAttribute("msg", "用户信息修改成功!");
			return findAll(request, response);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/Admin/UserM/AhyModify.jsp";
		}
	}

	/**
	 * 修改密码前
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String modifyPwdPre(HttpServletRequest request,
			HttpServletResponse response) {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String userid = request.getParameter("userid");
		User form = userService.load(userid);
		request.setAttribute("form", form);
		return "f:/Admin/UserM/AhyModify.jsp";

	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public String modifyPwd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String userid = request.getParameter("userid");
		String userpwd = request.getParameter("userpwd");
		Map<String, String> errors = new HashMap<String, String>();
		if (userpwd == null || userpwd.trim().isEmpty()) {
			errors.put("userpwd", "密码不能为空！");
		} else if (userpwd.length() < 6) {
			errors.put("userpwd", "密码长度必须大于6！");
		} else {
			String surepwd = request.getParameter("surepwd");
			if (surepwd == null || surepwd.trim().isEmpty()) {
				errors.put("surepwd", "确认密码不能为空！");
			} else if (!surepwd.equals(userpwd)) {
				request.setAttribute("surepwd", surepwd);
				errors.put("surepwd", "确认密码与输入密码不一致！");
			}
		}
		User user = new User();
		user.setUserid(userid);
		user.setUserpwd(userpwd);
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", user);
			return "f:/Admin/UserM/AhyModify.jsp";
		}

		try {
			userService.modifyPwd(user);
			request.setAttribute("msg", "修改密码成功!");
			return findAll(request, response);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", user);
			return "f:/Admin/UserM/AhyModify.jsp";
		}

	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int uid = Integer.parseInt(request.getParameter("uid"));
		userService.delete(uid);
		request.setAttribute("msg", "删除成功!");
		return findAll(request, response);
	}

}

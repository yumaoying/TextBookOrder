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
import com.textbookorder.service.AdminService;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 用户管理表述层（管理员）
 * 
 * @author Administrator
 * 
 */
public class AdminUserServlet extends BaseServlet {
	private AdminService adminService = new AdminService();

	/**
	 * 查找所有管理员用户
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
			request.setAttribute("msg", "你没有管理用户的权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		request.setAttribute("adminList", adminService.findAll());
		return "f:/Admin/UserM/AadminSearch.jsp";
	}

	/**
	 * 用户查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		String find = request.getParameter("find");
		String lookup = request.getParameter("lookup");
		if (find == null || lookup == null || lookup.trim().isEmpty()) {
			request.setAttribute("adminList", adminService.findAll());
			return "f:/Admin/UserM/AadminSearch.jsp";
		}
		List<Admin> adminList = new ArrayList<Admin>();
		if (find.equals("1")) {
			Admin newadmin = adminService.findByAdminId(lookup);
			adminList.add(newadmin);
		}
		if (find.equals("2")) {
			adminList = adminService.findByAdminName(lookup);
		}
		if (find.equals("3")) {
			Admin newadmin = adminService.findByAdminPhone(lookup);
			adminList.add(newadmin);
		}
		if (find.equals("4")) {
			adminList = adminService.findGroup(lookup);
		}
		request.setAttribute("adminList", adminList);
		return "f:/Admin/UserM/AadminSearch.jsp";
	}

	/**
	 * 添加管理员
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，无法操作，请和系统管理员联系!");
		}
		Admin admin = CommonUtils
				.toBean(request.getParameterMap(), Admin.class);
		Map<String, String> errors = new HashMap<String, String>();
		String adminid = admin.getAdminid();
		if (adminid == null || adminid.trim().isEmpty()) {
			errors.put("adminid", "用户账号不能为空");
		}
		String name = admin.getName();
		if (name == null || name.trim().isEmpty()) {
			errors.put("name", "用户名不能为空");
		}
		String userpwd = admin.getPwd();
		if (userpwd == null || userpwd.trim().isEmpty()) {
			errors.put("pwd", "密码不能为空！");
		} else if (userpwd.length() < 6) {
			errors.put("pwd", "密码长度必须大于6！");
		} else {
			String surepwd = request.getParameter("surepwd");
			if (surepwd == null || surepwd.trim().isEmpty()) {
				errors.put("surepwd", "确认密码不能为空！");
			} else if (!surepwd.equals(userpwd)) {
				request.setAttribute("surepwd", surepwd);
				errors.put("surepwd", "确认密码与输入密码不一致！");
			}
		}
		// / 权限
		String rights = "";
		for (int i = 0; i < 5; i++) {
			String quanxianstr = "quanxian" + (i + 1);
			String quanxian = request.getParameter(quanxianstr);
			if (quanxian == null || quanxian.trim().isEmpty()) {
				if (i == 0) {
					rights = "0";
				} else {
					rights = rights + "," + "0";
				}
			} else {
				if (i == 0) {
					rights = quanxian;
				} else {
					rights = rights + "," + quanxian;
				}
			}
		}
		admin.setRights(rights);
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", admin);
			return "f:/Admin/UserM/AadminAdd.jsp";
		}
		try {
			adminService.add(admin);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", admin);
			return "f:/Admin/UserM/AadminAdd.jsp";
		}
		return findAll(request, response);
	}

	/**
	 * 删除管理员
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AuserManage.jsp";
		}
		String id = request.getParameter("aid");
		int aid = Integer.parseInt(id);
		adminService.delete(aid);
		return findAll(request, response);
	}

	/**
	 * 修改前加载管理员信息
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
			return "f:/Admin/AuserManage.jsp";
		}
		String id = request.getParameter("aid");
		int aid = Integer.parseInt(id);
		Admin admin = adminService.load(aid);
		request.setAttribute("form", admin);
		return "f:/Admin/UserM/AadminModify.jsp";
	}

	/**
	 * 修改管理员信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modifyAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[2].equals("0")) {
			request.setAttribute("msg", "你没有管理用户的权限，请和系统管理员联系!");
			return "f:/Admin/AuserManage.jsp";
		}
		Map<String, String> errors = new HashMap<String, String>();
		Admin admin = CommonUtils
				.toBean(request.getParameterMap(), Admin.class);
		String name = admin.getName();
		if (name == null || name.trim().isEmpty()) {
			errors.put("name", "用户账号不能为空");
		}
		String userpwd = admin.getPwd();
		if (userpwd == null || userpwd.trim().isEmpty()) {
			errors.put("pwd", "密码不能为空！");
		} else if (userpwd.length() < 6) {
			errors.put("pwd", "密码长度必须大于6！");
		}
		// 权限
		String rights = "";
		for (int i = 0; i < 5; i++) {
			String quanxianstr = "quanxian" + (i + 1);
			String quanxian = request.getParameter(quanxianstr);
			if (quanxian == null || quanxian.trim().isEmpty()) {
				if (i == 0) {
					rights = "0";
				} else {
					rights = rights + "," + "0";
				}
			} else {
				if (i == 0) {
					rights = quanxian;
				} else {
					rights = rights + "," + quanxian;
				}
			}
		}
		admin.setRights(rights);
		try {
			adminService.mpdify(admin);
			request.setAttribute("msg", "修改成功");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", admin);
			return "f:/Admin/UserM/AadminModify.jsp";
		}
		return findAll(request, response);
	}
}

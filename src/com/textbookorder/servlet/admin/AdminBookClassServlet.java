package com.textbookorder.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.BookClass;
import com.textbookorder.service.BookClassService;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

public class AdminBookClassServlet extends BaseServlet {
	private BookClassService classService = new BookClassService();

	/**
	 * 添加分类
	 * 
	 * @param request
	 * @param response
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
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String classname = request.getParameter("classname");
		if (classname == null || classname.trim().isEmpty()) {
			request.setAttribute("msg", "图书分类名不能为空");
			request.setAttribute("classname", classname);
			return findAll(request, response);
			// return "f:/Admin/bookM/AbookclassQuery.jsp";
		}
		try {
			classService.addBookClass(classname);
			request.setAttribute("msg", "添加成功");
			return findAll(request, response);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("classname", classname);
			return findAll(request, response);
			// return "f:/Admin/bookM/AbookclassQuery.jsp";
		}
	}

	/**
	 * 删除图书分类
	 */

	public String delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String id = request.getParameter("cid");
		int cid = Integer.parseInt(id);
		try {
			classService.delete(cid);
			request.setAttribute("msg", "删除成功");
			return findAll(request, response);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return findAll(request, response);
		}

	}

	/**
	 * 修改前加载图书分类
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
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String id = request.getParameter("cid");
		int cid = Integer.parseInt(id);
		BookClass bookclass = classService.load(cid);
		request.setAttribute("bookclass", bookclass);
		return findAll(request, response);
	}

	/**
	 * 修改图书分类
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
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		BookClass bookclass = CommonUtils.toBean(request.getParameterMap(),
				BookClass.class);
		try {
			classService.modify(bookclass);
			request.setAttribute("msg", "修改成功");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("bookclass", bookclass);
			return findAll(request, response);
		}
		return findAll(request, response);
	}

	/**
	 * 查找所有图书分类
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
		List<BookClass> classList = classService.findAll();
		request.setAttribute("classList", classList);
		return "f:/Admin/bookM/AbookclassQuery.jsp";
	}
}

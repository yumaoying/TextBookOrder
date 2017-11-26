package com.textbookorder.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.BookClass;
import com.textbookorder.domain.TextBook;
import com.textbookorder.service.BookClassService;
import com.textbookorder.service.TextBookService;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 图书管理表述层
 * 
 * @author Administrator
 * 
 */
public class AdminTextBookServlet extends BaseServlet {
	private TextBookService bookservice = new TextBookService();
	private BookClassService classService = new BookClassService();

	/**
	 * 在添加图书前，加载所有分类信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return findAll(request, response);
		}
		List<BookClass> classList = classService.findAll();
		request.setAttribute("classList", classList);
		return "f:/Admin/bookM/AbookAdd.jsp";
	}

	/**
	 * 查找所有图书
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
		request.setAttribute("bookList", bookservice.findAll());
		return "f:/Admin/bookM/AbookQuery.jsp";
	}

	/**
	 * 加载某本图书信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		String idstr = request.getParameter("id");
		int id = Integer.parseInt(idstr);
		request.setAttribute("textbook", bookservice.load(id));
		request.setAttribute("classList", classService.findAll());
		return "f:/Admin/bookM/AbookModify.jsp";
	}

	/**
	 * 删除图书
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
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		String idstr = request.getParameter("id");
		int id = Integer.parseInt(idstr);
		try {
			bookservice.delete(id);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return findAll(request, response);
		}
		request.setAttribute("msg", "删除成功");
		return findAll(request, response);
	}

	/**
	 * 修改图书
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
			return "r:/Admin/AIndex.jsp";
		}
		TextBook tetbook = CommonUtils.toBean(request.getParameterMap(),
				TextBook.class);
		BookClass bookclass = CommonUtils.toBean(request.getParameterMap(),
				BookClass.class);
		tetbook.setBookclass(bookclass);
		try {
			bookservice.modify(tetbook);
			request.setAttribute("msg", "修改成功");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/admin/AdminTextBookServlet?method=load&id="
					+ tetbook.getId();

		}
		return findAll(request, response);

	}

	/**
	 * 上传图片
	 * 
	 * @param id
	 * @param img
	 */
	public void uploadmg(int id, String img) {
		bookservice.uploadImg(id, img);
	}

	/**
	 * 多条件组合查询
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
		TextBook textbook = CommonUtils.toBean(request.getParameterMap(),
				TextBook.class);
		List<TextBook> bookList = bookservice.groupSelect(textbook);
		request.setAttribute("bookList", bookList);
		return "f:/Admin/bookM/AbookQuery.jsp";
	}

	/**
	 * 按条件查询+模糊查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		String lookup = request.getParameter("lookup");
		String find = request.getParameter("find");
		// 分类加载图书
		BookClass bookclass = CommonUtils.toBean(request.getParameterMap(),
				BookClass.class);
		int cid = bookclass.getCid();
		if (find != null && !find.trim().isEmpty() && lookup != null
				&& !lookup.trim().isEmpty()) {
			// isb查询
			if (find.equals("1")) {
				request.setAttribute("bookList", bookservice.findByIsbn(lookup));
				List<BookClass> classList = classService.findAll();
				request.setAttribute("classList", classList);
				return "f:/Admin/bookM/AbookQuery.jsp";
			}
			// 书名查询
			if (find.equals("2")) {
				request.setAttribute("bookList",
						bookservice.findByBookName(lookup));
				List<BookClass> classList = classService.findAll();
				request.setAttribute("classList", classList);
				return "f:/Admin/bookM/AbookQuery.jsp";
			}
			// 作者
			if (find.equals("3")) {
				request.setAttribute("bookList",
						bookservice.findByBookAuthor(lookup));
				List<BookClass> classList = classService.findAll();
				request.setAttribute("classList", classList);
				return "f:/Admin/bookM/AbookQuery.jsp";
			}
			if (find.equals("4")) {
				TextBook textbook = new TextBook();
				textbook.setIsbn(lookup);
				textbook.setBookname(lookup);
				textbook.setAuthor(lookup);
				List<TextBook> bookList = bookservice.mohuSelect(textbook);
				List<BookClass> classList = classService.findAll();
				request.setAttribute("classList", classList);
				request.setAttribute("bookList", bookList);
				return "f:/Admin/bookM/AbookQuery.jsp";
			}
		}

		if (cid != 0) {
			request.setAttribute("currentcid", cid);
			request.setAttribute("bookList", bookservice.findByCid(cid));
			List<BookClass> classList = classService.findAll();
			request.setAttribute("classList", classList);
			return "f:/Admin/bookM/AbookQuery.jsp";
		}
		return findAll(request, response);
	}

	/**
	 * 分类加载图书
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		String cid = (String) request.getAttribute("cid");
		if (cid == null || cid.trim().isEmpty()) {
			request.setAttribute("msg", "图书编号为空，无法加载！");
		}
		int clid = Integer.parseInt(cid);
		request.setAttribute("bookList", bookservice.findByCid(clid));
		return "f:/Admin/bookM/AbookQuery.jsp";
	}
}

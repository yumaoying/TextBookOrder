package com.textbookorder.servlet.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.BookClass;
import com.textbookorder.domain.Come;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.TextBook;
import com.textbookorder.service.AdminService;
import com.textbookorder.service.BookClassService;
import com.textbookorder.service.StoreService;
import com.textbookorder.service.TextBookService;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 图书库存管理表述层
 * 
 * @author Administrator
 * 
 */
public class AdminStoreSevrlet extends BaseServlet {
	private TextBookService bookService = new TextBookService();
	private BookClassService classService = new BookClassService();
	private AdminService adminService = new AdminService();
	private StoreService storeService = new StoreService();

	/**
	 * 加载某编号入库记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadByComeid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		Come come = CommonUtils.toBean(request.getParameterMap(), Come.class);
		Come newcome = storeService.loadByComeid(come.getComeid());
		request.setAttribute("come", newcome);
		return "f:/Admin/StorageM/ArkDetail.jsp";
	}

	/**
	 * 图书入库前准备
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String comePre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/AuserManage.jsp";
		}
		// 加载所有图书
		// 加载所有分类信息
		// 加载所有管理员
		request.setAttribute("bookList", bookService.findAll());
		request.setAttribute("classList", classService.findAll());
		request.setAttribute("adminList", adminService.findAll());
		return "f:/Admin/StorageM/Ark.jsp";
	}

	/**
	 * 已有图书入库
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String existBookCome(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		Come come = CommonUtils.toBean(request.getParameterMap(), Come.class);
		// 获取图书编号
		TextBook textbook = CommonUtils.toBean(request.getParameterMap(),
				TextBook.class);
		Admin admin = CommonUtils
				.toBean(request.getParameterMap(), Admin.class);
		come.setTextbook(textbook);
		come.setAdmin(admin);
		System.out.println(come);
		if (textbook.getId() == 0) {
			request.setAttribute("msg", "没有选择入库图书！");
			return comePre(request, response);
		}
		try {
			storeService.existBookCome(come);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		request.setAttribute("msg", "入库成功");
		return comePre(request, response);
	}

	/**
	 * 查询所有入库图书
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllCome(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		List<Come> comeList = storeService.findAllCome();
		request.setAttribute("comeList", comeList);
		return "f:/Admin/StorageM/ArkQuery.jsp";
	}

	/**
	 * 准备修改入库图书
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modifyComePre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 加载所有图书
		// 加载所有分类信息
		// 加载所有管理员

		Come come = CommonUtils.toBean(request.getParameterMap(), Come.class);
		Come newcome = storeService.loadByComeid(come.getComeid());
		request.setAttribute("come", newcome);
		request.setAttribute("adminList", adminService.findAll());
		request.setAttribute("classList", classService.findAll());
		return "f:/Admin/StorageM/ArkModify.jsp";
	}

	/**
	 * 修改入库信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modifyCome(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		Come come = CommonUtils.toBean(request.getParameterMap(), Come.class);
		// 获取图书编号
		TextBook textbook = CommonUtils.toBean(request.getParameterMap(),
				TextBook.class);
		BookClass bookclass = CommonUtils.toBean(request.getParameterMap(),
				BookClass.class);
		textbook.setBookclass(bookclass);
		Admin admin = CommonUtils
				.toBean(request.getParameterMap(), Admin.class);
		come.setTextbook(textbook);
		come.setAdmin(admin);
		storeService.modifyCome(come);
		storeService.loadByComeid(come.getComeid());
		request.setAttribute("msg", "修改成功");
		request.setAttribute("come", come);

		return "f:/Admin/StorageM/ArkDetail.jsp";
	}

	/**
	 * 删除入库记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteCome(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		Come come = CommonUtils.toBean(request.getParameterMap(), Come.class);
		storeService.deleteCome(come.getComeid());
		request.setAttribute("msg", "删除成功!");
		return findAllCome(request, response);
	}

	/**
	 * 多条件组合查询入库记录
	 */
	public String findByGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String isbn = request.getParameter("isbn");
		String bookname = request.getParameter("bookname");
		String[] params = { starttime, endtime, isbn, bookname };
		if ((starttime == null || starttime.trim().isEmpty())
				&& (endtime == null || endtime.trim().isEmpty())
				&& (isbn == null || isbn.trim().isEmpty())
				&& (bookname == null || bookname.trim().isEmpty())) {
			return findAllCome(request, response);
			/*
			 * request.setAttribute("msg", "查询条件为空"); return
			 * "f:/Admin/StorageM/ArkQuery.jsp";
			 */
		}
		List<Come> comeList = storeService.findByGroup(params);
		Map<String, String> select = new HashMap<String, String>();
		select.put("starttime", starttime);
		select.put("isbn", isbn);
		select.put("bookname ", bookname);
		request.setAttribute("select", select);
		request.setAttribute("comeList", comeList);
		return "f:/Admin/StorageM/ArkQuery.jsp";
	}

	/**
	 * 在查询库存前加载图书分类信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String stockPre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		/*
		 * if (loginAdmin.getRight()[3].equals("0")) {
		 * request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!"); return
		 * "r:/AuserManage.jsp"; }
		 */
		List<BookClass> classList = classService.findAll();
		request.setAttribute("classList", classList);
		return "f:/Admin/StorageM/AstorQuery.jsp";
	}

	/**
	 * 组合模糊查询库存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findGrooupStock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		/*
		 * if (loginAdmin.getRight()[3].equals("0")) {
		 * request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!"); return
		 * "r:/AuserManage.jsp"; }
		 */
		TextBook textbook = CommonUtils.toBean(request.getParameterMap(),
				TextBook.class);
		BookClass bookClass = CommonUtils.toBean(request.getParameterMap(),
				BookClass.class);
		textbook.setBookclass(bookClass);
		Stock stock = CommonUtils
				.toBean(request.getParameterMap(), Stock.class);
		stock.setTextbook(textbook);
		List<Stock> stockList = storeService.findGroupStock(stock);
		List<BookClass> classList = classService.findAll();
		request.setAttribute("classList", classList);
		request.setAttribute("stockList", stockList);
		return "f:/Admin/StorageM/AstorQuery.jsp";
	}

	public String loaBySid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		/*
		 * if (loginAdmin.getRight()[3].equals("0")) {
		 * request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!"); return
		 * "r:/AuserManage.jsp"; }
		 */
		int sid = Integer.parseInt(request.getParameter("sid"));
		Stock s = storeService.loadBySid(sid);
		request.setAttribute("stock", s);
		List<BookClass> classList = classService.findAll();
		request.setAttribute("classList", classList);
		return "f:/Admin/StorageM/Astormodiy.jsp";
	}

	/**
	 * 准备修改库存图书
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modifyStockPre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/AuserManage.jsp";
		}
		// 加载所有图书
		// 加载所有分类信息
		// 加载所有管理员
		int sid = Integer.parseInt(request.getParameter("sid"));
		Stock s = storeService.loadBySid(sid);
		request.setAttribute("stock", s);
		request.setAttribute("classList", classService.findAll());
		return "f:/Admin/StorageM/Astormodiy.jsp";
	}

	/**
	 * 修改库存信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modifyStock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		Stock stock = CommonUtils
				.toBean(request.getParameterMap(), Stock.class);
		// 获取图书编号
		TextBook textbook = CommonUtils.toBean(request.getParameterMap(),
				TextBook.class);
		BookClass bookclass = CommonUtils.toBean(request.getParameterMap(),
				BookClass.class);
		textbook.setBookclass(bookclass);
		stock.setTextbook(textbook);
		storeService.modifyStock(stock);
		request.setAttribute("msg", "修改成功!");
		request.setAttribute("stock", stock);
		request.setAttribute("classList", classService.findAll());
		return "f:/Admin/StorageM/Astormodiy.jsp";
	}

	/**
	 * 查找所有库存记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private String findAllStock(HttpServletRequest request,
			HttpServletResponse response) {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		/*
		 * if (loginAdmin.getRight()[3].equals("0")) {
		 * request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!"); return
		 * "r:/AuserManage.jsp"; }
		 */
		request.setAttribute("stockList", storeService.findAllStock());
		List<BookClass> classList = classService.findAll();
		request.setAttribute("classList", classList);
		return "f:/Admin/StorageM/AstorQuery.jsp";
	}

	/**
	 * 删除库存记录
	 * 
	 * @param sid
	 */
	public String deleteStock(HttpServletRequest request,
			HttpServletResponse response) {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		Stock stock = CommonUtils
				.toBean(request.getParameterMap(), Stock.class);
		try {
			storeService.deleteStock(stock.getSid());
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return findAllStock(request, response);
		}
		request.setAttribute("msg", "删除成功!");
		return findAllStock(request, response);
	}

}

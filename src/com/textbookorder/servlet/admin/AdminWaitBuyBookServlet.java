package com.textbookorder.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.LackBook;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.WaitBuyBook;
import com.textbookorder.service.StoreService;
import com.textbookorder.service.WaitBuyService;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 采购管理表述层
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class AdminWaitBuyBookServlet extends BaseServlet {
	private WaitBuyService waitService = new WaitBuyService();

	/**
	 * 查找所有采购单
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
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		List<WaitBuyBook> waitlist = waitService.findAll();
		request.setAttribute("waitlist", waitlist);
		return "f:/Admin/waitBuyM/AgsdQuery.jsp";
	}

	/**
	 * 转发到打印页面 查找所有采购单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllprint(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		List<WaitBuyBook> waitlist = waitService.findAll();
		request.setAttribute("waitlist", waitlist);
		return "f:/Admin/waitBuyM/AgsdQueryprint.jsp";
	}

	/**
	 * 添加采购记录
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
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		WaitBuyBook book = CommonUtils.toBean(request.getParameterMap(),
				WaitBuyBook.class);
		if ((book.getBookname() == null || book.getBookname().trim().isEmpty())
				&& (book.getIsbn() == null || book.getIsbn().trim().isEmpty())) {
			request.setAttribute("msg", "图书名称和isbn号不能同时为空！");
			return "f:/Admin/waitBuyM/AgAdd.jsp";
		}
		waitService.add(book);
		request.setAttribute("msg", "添加成功");
		return findAll(request, response);
	}

	/**
	 * 从缺书单中提取库存添加采购记录
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
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		LackBook lackbook = CommonUtils.toBean(request.getParameterMap(),
				LackBook.class);
		Stock stock = CommonUtils
				.toBean(request.getParameterMap(), Stock.class);
		StoreService stockService = new StoreService();
		Stock stocknew = stockService.loadBySid(stock.getSid());
		WaitBuyBook book = new WaitBuyBook();
		book.setIsbn(stocknew.getTextbook().getIsbn());
		book.setBookname(stocknew.getTextbook().getBookname());
		book.setPublisher(stocknew.getTextbook().getPubliser());
		book.setPublishtime(stock.getTextbook().getPublishtime());
		book.setAuthor(stock.getTextbook().getAuthor());
		book.setPlantmount(lackbook.getAmount());
		request.setAttribute("waitbuybook", book);
		request.setAttribute("msg", "添加成功");
		return "f:/Admin/waitBuyM/AgAdd.jsp";
	}

	/**
	 * 从缺书单中提取库存添加采购记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addStock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		LackBook lackbook = CommonUtils.toBean(request.getParameterMap(),
				LackBook.class);
		Stock stock = CommonUtils
				.toBean(request.getParameterMap(), Stock.class);
		StoreService stockService = new StoreService();
		Stock stocknew = stockService.loadBySid(stock.getSid());
		WaitBuyBook book = new WaitBuyBook();
		book.setIsbn(stocknew.getTextbook().getIsbn());
		book.setBookname(stocknew.getTextbook().getBookname());
		book.setPublisher(stocknew.getTextbook().getPubliser());
		book.setPublishtime(stock.getTextbook().getPublishtime());
		book.setAuthor(stock.getTextbook().getAuthor());
		book.setPlantmount(lackbook.getAmount());
		waitService.add(book);
		request.setAttribute("msg", "添加成功");
		return findAll(request, response);
	}

	/**
	 * 按采购编号查找
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findBywid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int wid = Integer.parseInt(request.getParameter("wid"));
		WaitBuyBook waitbuybook = waitService.findBywid(wid);
		request.setAttribute("waitbuybook", waitbuybook);
		return "f:/Admin/waitBuyM/AgsdModify.jsp";
	}

	/**
	 * 模糊查找按采购记
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
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String lookup = request.getParameter("lookup");
		List<WaitBuyBook> waitlist = waitService.find(lookup);
		request.setAttribute(" waitlist", waitlist);
		return "f:/Admin/waitBuyM/AgsdQuery.jsp";
	}

	/**
	 * 修改前加载采购记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String modifypre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int wid = Integer.parseInt(request.getParameter("wid"));
		WaitBuyBook waitbuybook = waitService.findBywid(wid);
		request.setAttribute("waitbuybook", waitbuybook);
		return "f:/Admin/waitBuyM/AgsdModify.jsp";
	}

	/**
	 * 修改采购记录
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
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		WaitBuyBook book = CommonUtils.toBean(request.getParameterMap(),
				WaitBuyBook.class);
		waitService.update(book);
		request.setAttribute("msg", "修改成功!");
		return findAll(request, response);
	}

	/**
	 * 删除所有采购记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteBywid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int wid = Integer.parseInt(request.getParameter("wid"));
		waitService.deleteBywid(wid);
		request.setAttribute("msg", "删除成功!");
		return findAll(request, response);
	}

	/**
	 * 删除所有采购记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		waitService.deleteAll();
		request.setAttribute("msg", "删除成功!");
		return findAll(request, response);
	}

}

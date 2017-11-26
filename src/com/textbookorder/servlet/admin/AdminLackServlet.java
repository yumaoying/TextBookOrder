package com.textbookorder.servlet.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.BookClass;
import com.textbookorder.domain.LackBook;
import com.textbookorder.domain.OrderItem;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.TextBook;
import com.textbookorder.domain.User;
import com.textbookorder.service.LackService;
import com.textbookorder.service.OrderService;
import com.textbookorder.service.StoreService;
import com.textbookorder.service.UserException;
import com.textbookorder.service.UserService;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 缺书单管理表述层
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class AdminLackServlet extends BaseServlet {
	private LackService lackservice = new LackService();
	private StoreService storeService = new StoreService();
	private UserService userService = new UserService();

	public String addLackPreStock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")
				|| loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		LackBook lackbook = new LackBook();
		int stockid = Integer.parseInt(request.getParameter("stockid"));
		lackbook.setStockid(stockid);
		// int sid = Integer.parseInt(request.getParameter("sid"));
		Stock s = storeService.loadBySid(stockid);
		request.setAttribute("stock", s);
		int amount = 0;
		lackbook.setAmount(amount);
		request.setAttribute("lackbook", lackbook);
		return "f:/Admin/booklistM/AqAdd.jsp";
	}

	// 登记缺书前准备
	public String addLackPre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")
				|| loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		LackBook lackbook = new LackBook();
		int stockid = Integer.parseInt(request.getParameter("stockid"));
		lackbook.setStockid(stockid);
		String itemidstr = request.getParameter("itemid");
		String uidstr = request.getParameter("uid");
		int amount = 0;
		// 如果缺书来自订单
		if (itemidstr != null && !itemidstr.trim().isEmpty()) {
			int itemid = Integer.parseInt(itemidstr);
			lackbook.setItemid(itemid);
			int stocknumer = Integer.parseInt(request
					.getParameter("stocknumer"));
			int buyamount = Integer.parseInt(request.getParameter("buyamount"));
			if (buyamount > stocknumer) {
				amount = buyamount - stocknumer;
			}
			// 设置缺书用户
			int uid = Integer.parseInt(uidstr);
			lackbook.setUid(uid);
			User user = userService.loadByuid(uid);
		}
		Stock s = storeService.loadBySid(stockid);
		request.setAttribute("stock", s);
		lackbook.setAmount(amount);
		request.setAttribute("lackbook", lackbook);
		return "f:/Admin/booklistM/AqAdd.jsp";
	}

	/**
	 * 添加缺书单
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
		if (loginAdmin.getRight()[4].equals("0")
				|| loginAdmin.getRight()[3].equals("0")
				|| loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		LackBook lackbook = CommonUtils.toBean(request.getParameterMap(),
				LackBook.class);
		lackbook.setLackdate(new Date());
		try {
			lackservice.add(lackbook);
			request.setAttribute("msg", "添加缺书单成功");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return findAll(request, response);
		}
		return findAll(request, response);

	}

	/**
	 * 查找所有缺书单信息
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
		List<LackBook> lacklist = lackservice.findAll();
		request.setAttribute("lacklist", lacklist);
		return "f:/Admin/booklistM/AqsdQuery.jsp";
	}

	/**
	 * 按库存编号查找缺书单信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findBysid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		int stockid = Integer.parseInt(request.getParameter("stockid"));
		LackBook lackbook = lackservice.findBysid(stockid);
		request.setAttribute("lackbook", lackbook);
		return "f:/Admin/booklistM/AqsdModify.jsp";
	}

	/**
	 * 修改数量
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateAmont(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")
				|| loginAdmin.getRight()[3].equals("0")
				|| loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		LackBook lackbook = CommonUtils.toBean(request.getParameterMap(),
				LackBook.class);
		lackservice.update(lackbook.getLackid(), lackbook.getAmount());
		request.setAttribute("msg", "修改成功！");
		request.setAttribute("lackbook", lackbook);
		return "f:/Admin/booklistM/AqsdModify.jsp";
	}

	/**
	 * 删除缺书单
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
		if (loginAdmin.getRight()[4].equals("0")
				|| loginAdmin.getRight()[3].equals("0")
				|| loginAdmin.getRight()[1].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int stockid = Integer.parseInt(request.getParameter("stockid"));
		String itemidstr = request.getParameter("itemid"); // 订单号
		int itemid = 0;
		if (itemidstr == null || itemidstr.trim().isEmpty()) {
			itemid = 0;
		}
		itemid = Integer.parseInt(itemidstr);
		try {
			lackservice.delete(stockid, itemid);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return findAll(request, response);
		}
		request.setAttribute("msg", "删除成功！");
		return findAll(request, response);
	}

	/**
	 * 查找脱销教材
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findLackOrderItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OrderService orderService = new OrderService();
		List<OrderItem> orderItemList = orderService.findLackOrderItem();
		request.setAttribute("orderItemList", orderItemList);
		return "f:/Admin/StorageM/AddLack.jsp";
	}

	/**
	 * 组合模糊查询库存量
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findGrooupStock(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TextBook textbook = CommonUtils.toBean(request.getParameterMap(),
				TextBook.class);
		BookClass bookClass = CommonUtils.toBean(request.getParameterMap(),
				BookClass.class);
		textbook.setBookclass(bookClass);
		Stock stock = CommonUtils
				.toBean(request.getParameterMap(), Stock.class);
		stock.setTextbook(textbook);
		StoreService storeService = new StoreService();
		List<Stock> stockList = storeService.findGroupStock(stock);
		request.setAttribute("stockList", stockList);
		return "f:/Admin/StorageM/AddLack.jsp";
	}
}

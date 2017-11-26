package com.textbookorder.servlet.admin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.OrderItem;
import com.textbookorder.domain.Out;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.User;
import com.textbookorder.service.AdminService;
import com.textbookorder.service.OrderService;
import com.textbookorder.service.OutService;
import com.textbookorder.service.StoreService;
import com.textbookorder.service.TextBookService;
import com.textbookorder.service.UserException;
import com.textbookorder.service.UserService;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 出库管理表述层
 * 
 * @author Administrator
 * 
 */
public class AdminOutServlet extends BaseServlet {
	private OutService outService = new OutService();
	private TextBookService bookService = new TextBookService();
	private UserService userService = new UserService();
	private AdminService adminService = new AdminService();
	private StoreService storeService = new StoreService();
	private OrderService orderService = new OrderService();

	/**
	 * 出库时加载库存信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String outPre(HttpServletRequest request,
			HttpServletResponse response) {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		request.setAttribute("adminList", adminService.findAll());
		request.setAttribute("userlist", userService.findAllUser());
		List<OrderItem> orderItemList = orderService.waitOut(-1);
		request.setAttribute("orderItemList", orderItemList);
		return "f:/Admin/StorageM/AckOrder.jsp";
	}

	/**
	 * 出库
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addOut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		Out out = CommonUtils.toBean(request.getParameterMap(), Out.class);
		Admin admin = CommonUtils
				.toBean(request.getParameterMap(), Admin.class);
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		Stock stock = CommonUtils
				.toBean(request.getParameterMap(), Stock.class);
		out.setAdmin(admin);
		out.setUser(user);
		out.setStock(stock);
		if (out.getOutdate() == null) {
			out.setOutdate(new Date());
		}
		if (admin == null || admin.getAid() == 0) {
			admin = (Admin) request.getSession().getAttribute("admin");
		}
		int itemid = Integer.parseInt(request.getParameter("itemid"));
		out.setItemid(itemid);
		try {
			outService.add(out);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/Admin/StorageM/Ack.jsp";
		}
		request.setAttribute("msg", "出库操作成功!");
		return outPre(request, response);
	}

	/**
	 * 修改前准备
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
			return "r:/Admin/AIndex.jsp";
		}
		int outid = Integer.parseInt(request.getParameter("outid"));
		Out out = outService.findByoutid(outid);
		request.setAttribute("outbook", out);
		request.setAttribute("adminList", adminService.findAll());
		return "f:/Admin/StorageM/Ackmodify.jsp";
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "r:/Admin/AIndex.jsp";
		}
		Out out = CommonUtils.toBean(request.getParameterMap(), Out.class);
		outService.update(out);
		request.setAttribute("msg", "修改成功！");
		return findAll(request, response);
	}

	/**
	 * 按出库编号查找
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByoutid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		int outid = Integer.parseInt(request.getParameter("outid"));
		Out out = outService.findByoutid(outid);
		request.setAttribute("outbook", out);
		return "f:/Admin/StorageM/Ackmodify.jsp";
	}

	/**
	 * 所有出库记录
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
		List<Out> outlist = outService.findAll();
		request.setAttribute("outlist", outlist);
		return "f:/Admin/StorageM/AckQuery.jsp";
	}

	/**
	 * 多条件组合查询出库记录
	 */
	public String findByGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		String starttime = request.getParameter("starttime");
		String endtime = request.getParameter("endtime");
		String isbn = request.getParameter("isbn");
		String bookname = request.getParameter("bookname");
		String adminid = request.getParameter("adminid");
		String userid = request.getParameter("userid");
		String[] params = { starttime, endtime, isbn, bookname, adminid, userid };
		if ((starttime == null || starttime.trim().isEmpty())
				&& (endtime == null || endtime.trim().isEmpty())
				&& (isbn == null || isbn.trim().isEmpty())
				&& (bookname == null || bookname.trim().isEmpty())) {
			return findAll(request, response);
		}
		List<Out> outList = outService.findByGroup(params);
		Map<String, String> select = new HashMap<String, String>();
		select.put("starttime", starttime);
		select.put("isbn", isbn);
		select.put("bookname", bookname);
		select.put("userid", userid);
		select.put("adminid", adminid);
		request.setAttribute("select", select);
		request.setAttribute("outlist", outList);
		return "f:/Admin/StorageM/AckQuery.jsp";
	}

	/**
	 * 待发货订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String waitOut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		List<OrderItem> orderItemList = orderService.waitOut(-1);
		request.setAttribute("orderItemList", orderItemList);
		return "f:/Admin/StorageM/AckOrder.jsp";
	}
}

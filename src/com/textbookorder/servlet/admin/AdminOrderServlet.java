package com.textbookorder.servlet.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.OrderItem;
import com.textbookorder.service.OrderService;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 订单管理表述层
 * 
 * @author Administrator
 * 
 */
public class AdminOrderServlet extends BaseServlet {
	private OrderService orderService = new OrderService();

	/**
	 * 查找订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String radstr = request.getParameter("lookup");
		String selvalstr = request.getParameter("selval");
		List<OrderItem> orderlist = new ArrayList<OrderItem>();
		String orderid = request.getParameter("content");
		String user = request.getParameter("content");
		String start = request.getParameter("starttime");
		String end = request.getParameter("endtime");
		if (radstr != null && !radstr.trim().isEmpty()) {
			int rad = Integer.parseInt(radstr);
			orderlist = findbyrad(rad, orderid, user, start, end);
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			request.setAttribute("orderList", orderlist);
			return "f:/Admin/orderM/AorderQuery.jsp";
		}
		if (selvalstr != null && !selvalstr.trim().isEmpty()) {
			int selval = Integer.parseInt(selvalstr);
			orderlist = findbyselval(selval);
			request.setAttribute("selelectvar", selval);
			request.setAttribute("orderList", orderlist);
			return "f:/Admin/orderM/AorderQuery.jsp";
		}
		return findAll(request, response);
	}

	/**
	 * 查找订单,返回审核页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findCheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String radstr = request.getParameter("lookup");
		// List<OrderItem> orderlist = new ArrayList<OrderItem>();
		// String orderid = request.getParameter("content");
		// String user = request.getParameter("content");
		// String start = request.getParameter("starttime");
		// String end = request.getParameter("endtime");
		// if (radstr != null && !radstr.trim().isEmpty()) {
		// int rad = Integer.parseInt(radstr);
		// orderlist = findbyrad(rad, orderid, user, start, end);
		// request.setAttribute("start", start);
		// request.setAttribute("end", end);
		// request.setAttribute("orderList", orderlist);
		// return "/system/order/check.jsp";
		// }
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		String selvalstr = request.getParameter("selval");
		List<OrderItem> orderlist = new ArrayList<OrderItem>();
		if (selvalstr != null && !selvalstr.trim().isEmpty()) {
			int selval = Integer.parseInt(selvalstr);
			orderlist = findbyselval(selval);
			request.setAttribute("selelectvar", selval);
			request.setAttribute("orderList", orderlist);
			return "f:/Admin/orderM/AorderCheck.jsp";
		}
		orderlist = orderService.LoadAllOrderItem();
		request.setAttribute("orderList", orderlist);
		return "f:/Admin/orderM/AorderCheck.jsp";
	}

	/**
	 * 按单选查询条件查询
	 * 
	 * @param rad
	 * @param orderid
	 * @param user
	 * @param start
	 * @param end
	 * @return
	 */
	public List<OrderItem> findbyrad(int rad, String orderid, String user,
			String start, String end) {
		List<OrderItem> orderlist = new ArrayList<OrderItem>();
		if (rad == 0) {
			orderlist = orderService.LoadAllOrderItem();
		}
		if (rad == 1) {
			// 订单号
			orderlist = orderService.LoadOrderItemByorderid(orderid);
		}
		if (rad == 2) {
			// 购买人
			orderService.findByUserOrderItem(user);
			orderlist = orderService.findByUserOrderItem(user);
		}
		if (rad == 3) {
			// 模糊查询
			orderlist = orderService.findGroupOrderItem(user);
		}
		if (rad == 4) {
			// 订单生成时间
			orderService.findByDate(start, end);
		}
		return orderlist;
	}

	/**
	 * 按条件查找订单
	 * 
	 * @param selval
	 */
	public List<OrderItem> findbyselval(int selval) {
		List<OrderItem> orderlist = new ArrayList<OrderItem>();
		if (selval == 1) {
			orderlist = orderService.waitCheckFckstate();
		}
		// 待初审
		if (selval == 2) {
			orderlist = orderService.waitCheckSckstate();
		}
		// 待审查
		if (selval == 3) {
			orderlist = orderService.waitCheck();
		}
		// 待付款
		if (selval == 4) {
			orderlist = orderService.orderState(-1, 0);
		}
		// 待发货
		if (selval == 5) {
			orderlist = orderService.orderState(-1, 1);
		}
		// 待领书
		if (selval == 6) {
			orderlist = orderService.orderState(-1, 2);
		}
		// 交易成功
		if (selval == 7) {
			orderlist = orderService.orderState(-1, 3);
		}
		return orderlist;
	}

	/**
	 * 查找所有订单
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
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		List<OrderItem> orderlist = orderService.LoadAllOrderItem();
		request.setAttribute("orderList", orderlist);
		return "f:/Admin/orderM/AorderQuery.jsp";
	}

	/**
	 * 查找某个子订单项
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findOrderItemByItemid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int orderItemid = Integer.parseInt(request.getParameter("orderItemid"));
		OrderItem orderItem = orderService.findOrderItemid(orderItemid);
		request.setAttribute("orderItem", orderItem);
		return "f:/Admin/orderM/AorderDetail.jsp";
	}

	/**
	 * 进入审核
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String entercheck(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int orderItemid = Integer.parseInt(request.getParameter("orderItemid"));
		OrderItem orderItem = orderService.findOrderItemid(orderItemid);
		List<OrderItem> orderlist = new ArrayList<OrderItem>();
		orderlist.add(orderItem);
		request.setAttribute("orderList", orderlist);
		return "f:/Admin/orderM/AorderCheck.jsp";
	}

	/**
	 * 修改一审审状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateFckstate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int orderItemid = Integer.parseInt(request.getParameter("orderItemid"));
		int fckstate = Integer.parseInt(request.getParameter("fckstate"));
		OrderItem orderItem = new OrderItem();
		orderItem.setFckstate(fckstate);
		orderItem.setOrderItemid(orderItemid);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Date date = new Date();
		orderItem.setFckadmin(admin);
		orderItem.setFckdate(date);
		orderService.updateFckstate(orderItem);
		return findCheck(request, response);
	}

	/**
	 * 修改二审状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateSckstate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int orderItemid = Integer.parseInt(request.getParameter("orderItemid"));
		int sckstate = Integer.parseInt(request.getParameter("sckstate"));
		OrderItem orderItem = new OrderItem();
		orderItem.setSckstate(sckstate);
		orderItem.setOrderItemid(orderItemid);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Date date = new Date();
		orderItem.setSckadmin(admin);
		orderItem.setSckdate(date);
		orderService.updateSckstate(orderItem);
		return findCheck(request, response);
	}

	/**
	 * 修改订单订单状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int orderItemid = Integer.parseInt(request.getParameter("orderItemid"));
		int state = Integer.parseInt(request.getParameter("state"));
		orderService.updateState(state, orderItemid);
		return findAll(request, response);
	}

	/**
	 * 删除订单
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
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int orderItemid = Integer.parseInt(request.getParameter("orderItemid"));
		try {
			orderService.deleteOrderItem(orderItemid);
			request.setAttribute("msg", "订单删除成功！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return findAll(request, response);
		}
		return findAll(request, response);
	}
}

package com.textbookorder.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Buycar;
import com.textbookorder.domain.Car;
import com.textbookorder.domain.OrderItem;
import com.textbookorder.domain.Orders;
import com.textbookorder.domain.User;
import com.textbookorder.service.BuyCarService;
import com.textbookorder.service.OrderService;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 教师学生订单表述层
 * 
 * @author Administrator
 * 
 */
public class OrderServlet extends BaseServlet {
	private BuyCarService buyCarService = new BuyCarService();
	private OrderService orderService = new OrderService();

	/**
	 * 添加订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		String[] orderArray = request.getParameterValues("order");
		if (orderArray == null) {
			return "r:/libraryQuery.jsp";
		}
		Orders order = new Orders();
		order.setOrderid(CommonUtils.uuid()); // 设置订单编号
		order.setOrdertime(new Date());// 设置下单时间
		order.setOwner(user);
		double tolprice = 0;
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		Car car = (Car) request.getSession().getAttribute("car");
		for (String orderItemstr : orderArray) {
			String[] strarray = orderItemstr.split(",");
			int carid = Integer.parseInt(strarray[0]);// 得到购物车编号
			int buyamount = Integer.parseInt(strarray[1]); // 得到购买数量
			for (Buycar cartItem : car.getCartItems()) {
				// 将页面传递值分解，设置订单条目
				if (cartItem.getCarid() == carid) {
					OrderItem orderItem = new OrderItem();
					orderItem.setItemid(CommonUtils.uuid());
					orderItem.setOrder(order);
					orderItem.setStock(cartItem.getStock());
					// 重新设置数量
					orderItem.setBuyamount(buyamount);
					// 添加订单条目
					orderItemList.add(orderItem);
					// 更新购物车记录，若订单提交失败则保留购物车
					Buycar buycar = new Buycar();
					buycar.setUser(user);
					buycar.setStock(cartItem.getStock());
					buycar.setBuymount(buyamount);

					break;
				}
			}
		}
		order.setOrderItemList(orderItemList);
		order.getTotal();
		try {
			orderService.add(order);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/orderSubFail.jsp";
		}
		request.setAttribute("orders", order);
		return "f:/orderSubSucc.jsp";
	}

	/**
	 * 查找某用户所有订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByUid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		List<OrderItem> orderItemlist = orderService.findByUid(user.getUid());
		List<Orders> orderlist = toOrderlist(orderItemlist);
		request.setAttribute("orderList", orderlist);
		return "/allOrder.jsp";
	}

	/**
	 * 查找某个订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadOrderByOid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		Orders order = orderService.loadOrderByOid(request
				.getParameter("orderid"));
		request.setAttribute("orders", order);
		return "/orderDetail.jsp";
	}

	/**
	 * 查找某个订单中可开发票的订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadOpenBill(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		Orders order = orderService.openBillOrder(request
				.getParameter("orderid"));
		request.setAttribute("orders", order);
		return "/myEInvoice.jsp";
	}

	/**
	 * 查找订单中已登记购书的订单项
	 * 
	 * @param order
	 * @return
	 */
	public Orders isRegiister(Orders order) {
		List<OrderItem> orderItemlist = order.getOrderItemList();
		if (orderItemlist == null) {
			return null;
		}
		// 订单中如果有未登记购书的项，则在订单中删除该订单项
		for (OrderItem orderItem : orderItemlist) {
			if (orderItem.getIsregisbuy() == 0) {
				order.getOrderItemList().remove(orderItem);
			}
		}
		return order;
	}

	/**
	 * 查找某订单项订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loadOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		OrderItem order = orderService.findItemid((request
				.getParameter("itemid")));
		request.setAttribute("orderItem", order);

		return "/purchaseTracking.jsp";
		// return "/orderlist.jsp";
	}

	/**
	 * 查找用户所有在审核状态的订单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String allCkstateByuid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		List<OrderItem> orderItemlist = orderService.waitCheck(user.getUid());
		List<Orders> orrderlist = toOrderlist(orderItemlist);
		request.setAttribute("orderList", orrderlist);
		return "f:/allOrder.jsp";
	}

	/**
	 * 将订单条目按总订单编号组织
	 * 
	 * @param orderlist
	 * @return
	 */
	public List<Orders> toOrderlist(List<OrderItem> orderItemlist) {
		if (orderItemlist == null) {
			return null;

		}
		List<Orders> orderlist = null;
		for (OrderItem orderItem : orderItemlist) {
			// 得到每个订单条目，判断它对应的总订单是否在订单列表中
			Orders order = orderItem.getOrder();
			if (orderlist == null) {
				orderlist = new ArrayList<Orders>();
				List<OrderItem> orderItemListNew = new ArrayList<OrderItem>();
				orderItemListNew.add(orderItem);
				order.setOrderItemList(orderItemListNew); // 将该订单加入到订单列表中
				orderlist.add(order);

			} else {
				boolean flag = true;
				for (int i = 0; i < orderlist.size(); i++) {
					if (orderlist.get(i).getOrderid()
							.equals(order.getOrderid())) {
						orderlist.get(i).getOrderItemList().add(orderItem);
						flag = false;
						break;
					}
				}
				if (flag) {
					List<OrderItem> orderItemListNew = new ArrayList<OrderItem>();
					orderItemListNew.add(orderItem);
					order.setOrderItemList(orderItemListNew); // 将该订单加入到订单列表中
					orderlist.add(order);
				}
			}
		}
		return orderlist;
	}

	/**
	 * 查找某用户订单某状态信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String orderState(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		int state = Integer.parseInt(request.getParameter("state"));
		List<OrderItem> orderItemlist = orderService.orderState(user.getUid(),
				state);
		// 定单状态订单状态0未付款，1已付款但未发货，2已发货但未领书,3领书成功）
		List<Orders> orderlist = toOrderlist(orderItemlist);
		request.setAttribute("orderList", orderlist);
		return "f:/allOrder.jsp";
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
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		int orderItemid = Integer.parseInt(request.getParameter("orderItemid"));
		try {
			orderService.deleteOrderItemByuser(orderItemid);
			request.setAttribute("msg", "订单删除成功");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			return findByUid(request, response);
		}
		return findByUid(request, response);
	}

}

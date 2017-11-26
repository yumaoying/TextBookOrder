package com.textbookorder.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.textbookorder.dao.BuyCarDao;
import com.textbookorder.dao.OrderDao;
import com.textbookorder.domain.Buycar;
import com.textbookorder.domain.OrderItem;
import com.textbookorder.domain.Orders;
import com.textbookorder.tools.jdbc.JdbcUtils;

/**
 * 订单业务层
 * 
 * @author Administrator
 * 
 */
public class OrderService {
	private OrderDao orderDao = new OrderDao();
	private BuyCarDao buycardao = new BuyCarDao();

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @throws UserException
	 */
	public void add(Orders order) throws UserException {
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			// 插入订单
			orderDao.addOrder(order);
			orderDao.addOrderItemList(order.getOrderItemList());// 插入订单中的所有条目
			// 删除购物车条目

			for (OrderItem orderItem : order.getOrderItemList()) {
				Buycar buycar = new Buycar();
				buycar.setUser(order.getOwner());
				buycar.setStock(orderItem.getStock());
				buycardao.delete(buycar);
			}

			// 提交事务
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			// 回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			// throw new UserException("订单提交失败" + e.getMessage());
		}
	}

	/**
	 * 查找某订单编号的总订单（包含子订单）
	 * 
	 * @param orderid
	 * @return
	 */
	public Orders loadOrderByOid(String orderid) {
		return orderDao.loadOrderByOid(orderid);
	}

	/**
	 * 查找某用户订单
	 * 
	 * @param uid
	 * @return
	 */
	public List<OrderItem> findByUid(int uid) {
		return orderDao.findByUid(uid);
	}

	/**
	 * 查找某订单项的信息
	 * 
	 * @param orderItemid
	 * @return
	 */
	public OrderItem findOrderItemid(int orderItemid) {
		return orderDao.findOrderItemid(orderItemid);
	}

	/**
	 * 查找某订单项的信息
	 * 
	 * @param orderItemid
	 * @return
	 */
	public OrderItem findItemid(String Itemid) {
		return orderDao.findItemid(Itemid);
	}

	/**
	 * 加载所有订单
	 * 
	 * @return
	 */
	/*
	 * public List<Orders> loadAllOrders() { return orderDao.loadAllOrders(); }
	 */

	/**
	 * 加载所有订单条目
	 * 
	 * @return
	 */
	public List<OrderItem> LoadAllOrderItem() {
		return orderDao.LoadAllOrderItem();
	}

	/**
	 * 加载某订单编号的订单条目
	 * 
	 * @return
	 */
	public List<OrderItem> LoadOrderItemByorderid(String orderid) {
		return orderDao.LoadAllOrderItem(orderid);
	}

	/**
	 * 查找某用户所有待审核的定单
	 */
	public List<OrderItem> waitCheck(int uid) {
		return orderDao.waitCheck(uid);
	}

	/**
	 * 查询某用户某状态订单 定单状态订单状态0未付款，1已付款但未发货，2已发货但未领书,3领书成功
	 * 
	 * @param uid
	 * @param state
	 * @return
	 */
	public List<OrderItem> orderState(int uid, int state) {
		return orderDao.orderState(uid, state);
	}

	/**
	 * 查询从start-end之间的订单
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<OrderItem> findByDate(String start, String end) {
		return orderDao.findByDate(start, end);
	}

	/**
	 * 删除订单项
	 * 
	 * @param orderItemid
	 * @throws UserException
	 */
	public void deleteOrderItem(int orderItemid) throws UserException {
		OrderItem orderItem = orderDao.findOrderItemid(orderItemid);
		if (orderItem == null) {
			throw new UserException("该订单不存在，无法删除！");
		}
		try {
			JdbcUtils.beginTransaction();
			orderDao.deleteOrderitem(orderItemid);
			Orders order = orderDao.loadOrderByOid(orderItem.getOrder()
					.getOrderid());
			if (order.getOrderItemList() == null
					|| order.getOrderItemList().size() == 0) {
				// 如果该订单项对应的总订单没有子订单，将总订单删除
				orderDao.deleteOrder(orderItem.getOrder().getOrderid());
			}
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			// 回滚事务
			try {
				JdbcUtils.rollbackTransaction();
				throw new UserException("订单删除失败");
			} catch (SQLException e1) {
			}
			throw new UserException("订单删除失败");
		}

	}

	/**
	 * 用户删除订单
	 * 
	 * @param orderItemid
	 * @throws UserException
	 */
	public void deleteOrderItemByuser(int orderItemid) throws UserException {
		OrderItem orderItem = orderDao.findOrderItemid(orderItemid);
		if (orderItem == null) {
			throw new UserException("该订单不存在，无法删除！");
		}
		if (orderItem.getFckstate() == 0 || orderItem.getFckstate() == 1
				|| orderItem.getSckstate() == 0 || orderItem.getSckstate() == 1
				|| orderItem.getState() == 0) {
			try {
				JdbcUtils.beginTransaction();
				orderDao.deleteOrderitem(orderItemid);
				Orders order = orderDao.loadOrderByOid(orderItem.getOrder()
						.getOrderid());
				if (order.getOrderItemList() == null
						|| order.getOrderItemList().size() == 0) {
					// 如果该订单项对应的总订单没有子订单，将总订单删除
					orderDao.deleteOrder(orderItem.getOrder().getOrderid());
				}
				JdbcUtils.commitTransaction();
			} catch (SQLException e) {
				// 回滚事务
				try {
					JdbcUtils.rollbackTransaction();
					throw new UserException("订单删除失败");
				} catch (SQLException e1) {
				}
				throw new UserException("订单删除失败");
			}

		} else if (orderItem.getState() == 3) {
			// 交易成功的定单，已经领书
			orderDao.updateOrderitemDelete(1, orderItemid);
		}
	}

	/**
	 * 查找某用户所有待审核的订单
	 */
	public List<OrderItem> waitCheck() {
		return orderDao.waitCheck();
	}

	/**
	 * 查找所有待初审审核的定单
	 * 
	 * @return
	 */
	public List<OrderItem> waitCheckFckstate() {
		return orderDao.waitCheckFckstate();
	}

	/**
	 * 查找所有待二审审核的定单
	 * 
	 * @return
	 */
	public List<OrderItem> waitCheckSckstate() {
		return orderDao.waitCheckSckstate();
	}

	/**
	 * 修改订单项初审核状态
	 * 
	 * @param orderid
	 * @param ckstate
	 */
	public void updateFckstate(OrderItem orderItem) {
		orderDao.updateFckstate(orderItem);
	}

	/**
	 * 修改订单项二审核状态
	 * 
	 * @param orderid
	 * @param ckstate
	 */
	public void updateSckstate(OrderItem orderItem) {
		orderDao.updateSckstate(orderItem);
	}

	/**
	 * 修改订单状态
	 * 
	 * @param orderid
	 * @param state
	 */
	public void updateState(int state, int orderItemid) {
		orderDao.updateState(state, orderItemid);
	}

	/**
	 * 修改缺书状态
	 * 
	 * @param islack
	 * @param orderItemid
	 */
	public void updateIslack(int islack, int orderItemid) {
		orderDao.updateIslack(islack, orderItemid);
	}

	/**
	 * 修改登记购买状态
	 * 
	 * @param isregisbuy
	 * @param orderItemid
	 */
	public void updateisregisbuy(int isregisbuy, Date regisgedate,
			int orderItemid) {
		orderDao.updateisregisbuy(isregisbuy, regisgedate, orderItemid);
	}

	/**
	 * 查找脱销教材
	 * 
	 * @return
	 */
	public List<OrderItem> findLackOrderItem() {
		return orderDao.findLackOrderItem();

	}

	/**
	 * 按用户信息模糊查询
	 * 
	 * @param user
	 * @return
	 */
	public List<OrderItem> findByUserOrderItem(String user) {
		return orderDao.findByUserOrderItem(user);

	}

	/**
	 * 按用户输入信息模糊查询
	 * 
	 * @param user
	 * @return
	 */
	public List<OrderItem> findGroupOrderItem(String user) {
		return orderDao.findGroupOrderItem(user);
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
					System.out.println("no" + orderlist);
				}
			}
		}
		return orderlist;
	}

	/**
	 * 加载某订单中可开发票的子订单(当登记购书后就可以开发票)
	 * 
	 * @param order
	 * @return
	 */
	public Orders openBillOrder(String orderid) {
		Orders order = orderDao.loadOrderByOid(orderid);
		order = isRegiister(order);
		return order;
	}

	/**
	 * 查找订单中已登记购书的订单项
	 * 
	 * @param order
	 * @return
	 */
	public Orders isRegiister(Orders order) {
		if (order == null) {
			return null;
		}
		List<OrderItem> orderItemlist = order.getOrderItemList();
		if (orderItemlist == null) {
			return null;
		}
		List<OrderItem> orderItemMo = new ArrayList<OrderItem>();

		// 订单中如果有未登记购书的项，则在订单中删除该订单项
		for (OrderItem orderItem : orderItemlist) {
			if (orderItem.getIsregisbuy() == 1) {
				orderItemMo.add(orderItem);
			}
		}
		if (orderItemMo != null) {
			order.setOrderItemList(orderItemMo);
			// order.getOrderItemList().removeAll(orderItemMo);
		}
		return order;
	}

	/**
	 * 查找待发货订单 （初审和二审已通过）
	 * 
	 * 库存量大于购买量
	 * 
	 * @param uid
	 * @param state
	 * @return
	 */
	public List<OrderItem> waitOut(int uid) {
		return orderDao.waitOut(uid);
	}

}

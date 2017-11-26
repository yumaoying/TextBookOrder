package com.textbookorder.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.OrderItem;
import com.textbookorder.domain.Orders;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.TextBook;
import com.textbookorder.domain.User;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 订单持久层
 * 
 * @author Administrator
 * 
 */

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();

	public OrderDao() {

	}

	/**
	 * 添加订单
	 * 
	 * @param order
	 */
	public void addOrder(Orders order) {
		try {
			String sql = "insert into orders (orderid,userid,ordertime,total) values(?,?,?,?)";
			/*
			 * 处理util的Date转换成sql的Timestamp
			 */
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = { order.getOrderid(), order.getOwner().getUid(),
					timestamp, order.getTotal() };
			System.out.println(params);
			System.out.println("获取的订单" + order);
			System.out.println("数据库加入订单：" + params);
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 插入订单条目
	 * 
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList) {
		/**
		 * QueryRunner类的batch(String sql, Object[][] params) 其中params是多个一维数组！
		 * 每个一维数组都与sql在一起执行一次，多个一维数组就执行多次
		 */
		try {
			String sql = "insert into orderitem(itemid,oid,bookid,buyamount,subtotal) values(?,?,?,?,?)";
			Object params[][] = new Object[orderItemList.size()][];
			for (int i = 0; i < orderItemList.size(); i++) {
				OrderItem orderItem = orderItemList.get(i);
				params[i] = new Object[] { /* orderItem.getOrderItemid(), */
				orderItem.getItemid(), orderItem.getOrder().getOrderid(),
						orderItem.getStock().getTextbook().getId(),
						orderItem.getBuyamount(), orderItem.getSubtotal() };
			}
			qr.batch(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载某订单编号订单
	 * 
	 * @param orderid
	 * @return
	 */
	public Orders loadOrderByOid(String orderid) {
		try {
			// 得到所有的订单
			String sql = "select * from orders o,user u where o.userid=u.uid and  orderid=? ";
			Map<String, Object> map = qr.query(sql, new MapHandler(), orderid);
			if (map == null) {
				return null;
			}
			Orders order = toOrders(map);
			loadOrderItems(order);
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把一个Map转换成一个Orders对象
	 * 
	 * @param map
	 * @return
	 */
	private Orders toOrders(Map<String, Object> map) {
		Orders order = CommonUtils.toBean(map, Orders.class);
		User owner = CommonUtils.toBean(map, User.class);
		order.setOwner(owner);
		return order;
	}

	/**
	 * 加载指定的订单所有的订单条目
	 * 
	 * @param order
	 * @throws SQLException
	 */
	private void loadOrderItems(Orders order) {
		String sql = "select * from orderitem i,stock s,textbook t where s.bookid=t.id and i.bookid=t.id and oid=?";
		List<Map<String, Object>> mapList = null;
		try {
			mapList = qr.query(sql, new MapListHandler(), order.getOrderid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}

	/**
	 * 查找某用户订单
	 * 
	 * @param uid
	 * @return
	 */
	public List<OrderItem> findByUid(int uid) {
		List<OrderItem> orderItemlist = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and o.userid=? order by ordertime desc ";
			maplist = qr.query(sql, new MapListHandler(), uid);
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderItemlist.add(orderItem);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orderItemlist;
	}

	/**
	 * 把mapList中每个Map转换成两个对象，并建立关系
	 * 
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (Map<String, Object> map : mapList) {
			OrderItem item = toOrderItem(map);
			orderItemList.add(item);
		}
		return orderItemList;
	}

	/**
	 * 把一个Map转换成一个OrderItem对象
	 * 
	 * @param map
	 * @return
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Stock stock = CommonUtils.toBean(map, Stock.class);
		TextBook book = CommonUtils.toBean(map, TextBook.class);
		Orders order = toOrders(map);
		orderItem.setOrder(order);
		stock.setTextbook(book);
		orderItem.setStock(stock);
		selectFckInfo(orderItem); // 加载订单初审信息
		selectSckInfo(orderItem); // 加载订单二审信息
		return orderItem;
	}

	/**
	 * 查找某订单项的信息
	 * 
	 * @param orderItemid
	 * @return
	 */
	public OrderItem findOrderItemid(int orderItemid) {
		String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and orderItemid=?";
		Map<String, Object> map = null;
		try {
			map = qr.query(sql, new MapHandler(), orderItemid);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (map == null) {
			return null;
		}
		OrderItem orderItem = toOrderItem(map);
		return orderItem;
	}

	/**
	 * 按子订单号查找
	 * 
	 * @param itemid
	 * @return
	 */
	public OrderItem findItemid(String itemid) {
		String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and  itemid=?";
		Map<String, Object> map = null;
		try {
			map = qr.query(sql, new MapHandler(), itemid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (map == null) {
			return null;
		}
		OrderItem orderItem = toOrderItem(map);
		return orderItem;
	}

	/**
	 * 按订单号查找某订单项的信息
	 * 
	 * @param orderItemid
	 * @return
	 */
	public OrderItem findByItemid(String itemid) {
		String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and orderItemid=?";
		Map<String, Object> map = null;
		try {
			map = qr.query(sql, new MapHandler(), itemid);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (map == null) {
			return null;
		}
		OrderItem orderItem = toOrderItem(map);
		return orderItem;
	}

	/**
	 * 加载所有订单
	 * 
	 * @return
	 */
	/*
	 * public List<Orders> loadAllOrders() { String sql =
	 * "select * from orders o,user u where o.userid=u.uid order by ordertime desc"
	 * ; List<Orders> orderList = new ArrayList<Orders>(); List<Map<String,
	 * Object>> maplist = new ArrayList<Map<String, Object>>(); try { maplist =
	 * qr.query(sql, new MapListHandler()); for (Map<String, Object> map :
	 * maplist) { Orders order = toOrders(map); loadOrderItems(order);
	 * orderList.add(order); } return orderList; } catch (SQLException e) {
	 * throw new RuntimeException(e); } }
	 */

	/**
	 * 修改订单条目初审审核状态
	 * 
	 * @param oid
	 * @param state
	 * @return
	 */
	public void updateFckstate(OrderItem orderItem) {
		try {
			String sql = "update orderitem set fckstate=?,fckaid=?,fckdate=? where orderItemid=?";
			Object[] params = { orderItem.getFckstate(),
					orderItem.getFckadmin().getAid(), orderItem.getFckdate(),
					orderItem.getOrderItemid() };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改订单条目二审审核状态
	 * 
	 * @param oid
	 * @param state
	 * @return
	 */
	public void updateSckstate(OrderItem orderItem) {
		try {
			String sql = "update orderitem set sckstate=?,sckaid=?,sckdate=? where orderItemid=?";
			Object[] params = { orderItem.getSckstate(),
					orderItem.getSckadmin().getAid(), orderItem.getSckdate(),
					orderItem.getOrderItemid() };
			qr.update(sql, params);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找订单条目的一审信息并返回
	 * 
	 * @param orderItem
	 */
	public void selectFckInfo(OrderItem orderItem) {
		try {
			String sql = "select * from orderitem o,admin a where o.fckaid=a.aid and orderItemid=?";
			Map<String, Object> map = qr.query(sql, new MapHandler(),
					orderItem.getOrderItemid());
			OrderItem orderItem2 = CommonUtils.toBean(map, OrderItem.class);
			Admin fckadmin = CommonUtils.toBean(map, Admin.class);
			orderItem.setFckadmin(fckadmin);
			orderItem.setFckdate(orderItem2.getFckdate());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找订单条目的二审信息，并返回
	 * 
	 * @param orderItem
	 */
	public void selectSckInfo(OrderItem orderItem) {
		try {
			String sql = "select * from orderitem o,admin a where o.sckaid=a.aid and orderitemid=?";
			Map<String, Object> map = qr.query(sql, new MapHandler(),
					orderItem.getOrderItemid());
			OrderItem orderItem2 = CommonUtils.toBean(map, OrderItem.class);
			Admin sckadmin = CommonUtils.toBean(map, Admin.class);
			orderItem.setSckadmin(sckadmin);
			orderItem.setSckdate(orderItem2.getSckdate());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改订单条目的订单状态
	 * 
	 * @param oid
	 * @param state
	 * @return
	 */
	public void updateState(int state, int orderItemid) {
		try {
			String sql = "update orderitem set state=? where orderItemid=?";
			qr.update(sql, state, orderItemid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载所有订单条目
	 * 
	 * @return
	 */
	public List<OrderItem> LoadAllOrderItem() {
		List<OrderItem> orderItemlist = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid";
			maplist = qr.query(sql, new MapListHandler());
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderItemlist.add(orderItem);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orderItemlist;
	}

	/**
	 * 加载某订单的订单条目
	 * 
	 * @return
	 */
	public List<OrderItem> LoadAllOrderItem(String orderid) {
		List<OrderItem> orderItemlist = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and i.oid=?";
			maplist = qr.query(sql, new MapListHandler(), orderid);
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderItemlist.add(orderItem);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orderItemlist;

	}

	/**
	 * 查找所有待初审审核的订单
	 * 
	 * @return
	 */
	public List<OrderItem> waitCheckFckstate() {
		List<OrderItem> orderItemlist = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and fckstate=0";
			maplist = qr.query(sql, new MapListHandler());
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderItemlist.add(orderItem);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orderItemlist;
	}

	/**
	 * 查找所有待二审审核的定单
	 * 
	 * @return
	 */
	public List<OrderItem> waitCheckSckstate() {
		List<OrderItem> orderItemlist = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and sckstate=0";
			maplist = qr.query(sql, new MapListHandler());
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderItemlist.add(orderItem);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orderItemlist;
	}

	/**
	 * 查找所有待审核的定单
	 * 
	 * @return
	 */
	public List<OrderItem> waitCheck() {
		List<OrderItem> orderItemlist = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and ( fckstate=0  or sckstate=0 ) ";
			maplist = qr.query(sql, new MapListHandler());
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderItemlist.add(orderItem);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orderItemlist;
	}

	/**
	 * 查找某用户所有待审核的定单
	 */
	public List<OrderItem> waitCheck(int uid) {
		List<OrderItem> orderItemlist = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and ( fckstate=0  or sckstate=0 )and o.userid=? ";
			maplist = qr.query(sql, new MapListHandler(), uid);
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderItemlist.add(orderItem);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orderItemlist;
	}

	/**
	 * 查询某用户某状态订单,窜入uid=-1即查全部用户
	 * 
	 * 
	 * 
	 * @param uid
	 * @param state
	 * @return
	 */
	public List<OrderItem> orderState(int uid, int state) {
		String sql = "";
		List<OrderItem> orderItemlist = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			if (uid != -1) {
				sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and   state=? and o.userid=? ";
				maplist = qr.query(sql, new MapListHandler(), state, uid);
			} else {
				sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and state=? ";
				maplist = qr.query(sql, new MapListHandler(), state);
			}
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderItemlist.add(orderItem);
			}
			return orderItemlist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
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
		String sql = "";
		List<OrderItem> orderItemlist = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			if (uid != -1) {
				sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and fckstate=2  and  sckstate=2 and  s.stocknumer>i.buyamount  and  state=1  and o.userid=? ";
				maplist = qr.query(sql, new MapListHandler(), uid);
			} else {
				sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid and  fckstate=2  and   sckstate=2 and  s.stocknumer>i.buyamount  and state=1 ";
				maplist = qr.query(sql, new MapListHandler());
			}
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderItemlist.add(orderItem);
			}
			return orderItemlist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询日期
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<OrderItem> findByDate(String starttime, String endtime) {
		String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid ";
		List<Object> paramlist = new ArrayList<Object>();
		if (starttime != null && !starttime.trim().isEmpty()) {
			sql = sql + " and ordertime between ?";
			paramlist.add(starttime);
			if (endtime == null || endtime.trim().isEmpty()) {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");
				endtime = sm.format(new Date());

			}
			sql = sql + " and ?";
			paramlist.add(endtime);
		}
		List<OrderItem> orderList = new ArrayList<OrderItem>();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {

			maplist = qr.query(sql, new MapListHandler(), paramlist.toArray());
			for (Map<String, Object> map : maplist) {
				OrderItem orderItem = toOrderItem(map);
				orderList.add(orderItem);
			}
			return orderList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除订单项
	 * 
	 * @param orderItemid
	 * @param order
	 */
	public void deleteOrderitem(int orderItemid) {
		String sql = "delete from orderitem where orderItemid=?";
		try {
			qr.update(sql, orderItemid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改订单项的删除状态
	 * 
	 * @param orderItemid
	 * @param order
	 */
	public void updateOrderitemDelete(int isdelete, int orderItemid) {
		String sql = "update  orderitem  set isdelete=?  where orderItemid=?";
		try {
			qr.update(sql, isdelete, orderItemid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除总订单
	 * 
	 * @param orderid
	 */
	public void deleteOrder(String orderid) {
		String sql = "delete from orders where orderid=?";
		try {
			qr.update(sql, orderid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改某订单缺书状态
	 * 
	 * @param islack
	 * @param orderItemid
	 */
	public void updateIslack(int islack, int orderItemid) {
		String sql = "update orderitem set islack=? where orderItemid=?";
		try {
			qr.update(sql, islack, orderItemid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改某订单的登记购书状态
	 * 
	 * @param isregisbuy
	 * @param orderItemid
	 */
	public void updateisregisbuy(int isregisbuy, Date regisdate, int orderItemid) {
		String sql = "update orderitem set isregisbuy=?,regisdate=? where orderItemid=?";
		try {
			qr.update(sql, isregisbuy, regisdate, orderItemid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把mapList中每个Map转换成两个对象，并建立关系
	 * 
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemListConOrder(
			List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for (Map<String, Object> map : mapList) {
			OrderItem item = toOrderItem(map);
			Orders order = toOrders(map);
			item.setOrder(order);
			orderItemList.add(item);
		}
		return orderItemList;
	}

	/**
	 * 脱销教材
	 * 
	 * @return
	 */
	public List<OrderItem> findLackOrderItem() {
		String sql = "select * from orderitem i,stock s,textbook t where s.bookid=t.id and i.bookid=t.id and i.buyamount>=s.stocknumer";
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {
			mapList = qr.query(sql, new MapListHandler());
			List<OrderItem> orderItemList = toOrderItemList(mapList);
			return orderItemList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 模糊查询
	 * 
	 * @param user
	 * @return
	 */
	public List<OrderItem> findGroupOrderItem(String user) {
		String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid";
		List<Object> paramlist = new ArrayList<Object>();
		if (user != null && !user.trim().isEmpty()) {
			sql = sql + " and  (u.userid like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or u.username like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or u.telphone like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or u.email like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or u.school like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or u.academy like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or u.major like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or t.isbn like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or t.bookname like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or t.author like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or t.publiser like ? ";
			paramlist.add("%" + user + "%");
			sql = sql + " or i.oid like ?) ";
			paramlist.add("%" + user + "%");
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {
			mapList = qr.query(sql, new MapListHandler(), paramlist.toArray());
			List<OrderItem> orderItemList = toOrderItemList(mapList);
			return orderItemList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按用户信息
	 * 
	 * @param user
	 * @return
	 */
	public List<OrderItem> findByUserOrderItem(String user) {
		String sql = "select * from  orderitem i,orders o,user u,stock s,textbook t where o.userid=u.uid and s.bookid=t.id and i.bookid=t.id  and i.oid=o.orderid ";
		List<Object> paramlist = new ArrayList<Object>();
		if (user != null && !user.trim().isEmpty()) {
			sql = sql + " and  (u.userid =?";
			paramlist.add(user);
			sql = sql + " or u.username =?";
			paramlist.add(user);
			sql = sql + " or u.telphone=? ";
			paramlist.add(user);
			sql = sql + " or u.email=? )";
			paramlist.add(user);
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {
			mapList = qr.query(sql, new MapListHandler(), paramlist.toArray());
			List<OrderItem> orderItemList = toOrderItemList(mapList);
			return orderItemList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改某用户的购书登记状态
	 * 
	 * @param isregisbuy
	 * @param regisdate
	 * @param userid
	 */
	public void updateisregisbuybyuid(int isregisbuy, Date regisdate, int userid) {
		String sql = "update orderitem i,orders o set isregisbuy=?,regisdate=?  where i.oid=o.orderid and o.userid=? ";
		try {
			qr.update(sql, isregisbuy, regisdate, userid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 用户确认领书时，修改领书状态
	 * 
	 * @param uid
	 * @param date
	 */
	public void updateEnddateByid(int orderItemid, Date date) {
		String sql = "update orderitem   set enddate=?  where    orderItemid=?";
		try {
			qr.update(sql, date, orderItemid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 某订单确认领书时，修改领书状态
	 * 
	 * @param uid
	 * @param date
	 */
	public void updateEnddate(int uid, Date date) {
		String sql = "update orderitem i,orders o set enddate=? where i.oid=o.orderid  and  o.userid=? ";
		try {
			qr.update(sql, date, uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}

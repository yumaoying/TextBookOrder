package com.textbookorder.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Buycar;
import com.textbookorder.domain.Car;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.TextBook;
import com.textbookorder.domain.User;
import com.textbookorder.service.BuyCarService;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 购物车表示层
 * 
 * @author Administrator
 * 
 */
public class BuyCarServlet extends BaseServlet {
	private BuyCarService carService = new BuyCarService();
	private static boolean first = true;

	/**
	 * 添加到购物车
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addToCar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		Buycar buycar = new Buycar();
		buycar.setUser(user);
		String strid = request.getParameter("id");
		if (strid == null) {
			return "r:/TextBookServlet/findAllStock";
		}
		int id = Integer.parseInt(strid);
		TextBook textbook = new TextBook();
		textbook.setId(id);
		Stock stock = new Stock();
		stock.setTextbook(textbook);
		int buymount = Integer.parseInt(request.getParameter("buymount"));
		buycar.setBuymount(buymount);
		buycar.setStock(stock);
		System.out.println("购物车" + buycar);
		/*
		 * Stock stock = CommonUtils .toBean(request.getParameterMap(),
		 * Stock.class); TextBook textbook =
		 * CommonUtils.toBean(request.getParameterMap(), TextBook.class);
		 * stock.setTextbook(textbook); Buycar buycar =
		 * CommonUtils.toBean(request.getParameterMap(), Buycar.class);
		 * buycar.setStock(stock);
		 */
		Car car = (Car) request.getSession().getAttribute("car");
		if (car == null) {
			car = new Car();
			request.getSession().setAttribute("car", car);
		}
		System.out.println(buycar);
		carService.addToCar(buycar);
		// 加载所有购物车信息
		Map<Integer, Buycar> map = carService.carItemMap(user);
		car.setMap(map);
		System.out.println(car);
		return "f:/shoppingtrolley.jsp";
		// // 用户没有登录,也可以加入购物车，购物信息放在session中
		// if (user == null) {
		// if (car == null) {
		// car = new Car();
		// }
		// car.add(buycar);
		// } else {
		// // 用户第一次登录
		// if (car == null) {
		// car = new Car();
		// carService.addToCar(buycar);
		// } else {
		// // 购物车不为空，遍历session,将session的
		// Set<Integer> keys = car.getMap().keySet();
		// Iterator<Integer> it = keys.iterator();
		// while (it.hasNext()) {
		// Buycar bc = (Buycar) car.getMap().get(it.next());
		// carService.addToCar(bc);
		// }
		// }
		// Map<Integer, Buycar> map = carService.getCar(user);
		// car.setMap(map);
		// }
	}

	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		Buycar buycar = new Buycar();
		buycar.setUser(user);
		String strid = request.getParameter("id");
		if (strid == null) {
			request.setAttribute("msg", "未选择购买图书");
			return "f:/TextBookServlet/findAllStock";
		}
		int id = Integer.parseInt(strid);
		TextBook textbook = new TextBook();
		textbook.setId(id);
		Stock stock = new Stock();
		stock.setTextbook(textbook);
		int buymount = Integer.parseInt(request.getParameter("buymount"));
		buycar.setBuymount(buymount);
		buycar.setStock(stock);
		Car car = (Car) request.getSession().getAttribute("car");
		if (car == null) {
			car = new Car();
			request.getSession().setAttribute("car", car);
		}
		carService.addToCar(buycar);
		// 加载所有购物车信息
		Map<Integer, Buycar> map = carService.carItemMap(user);
		car.setMap(map);
		request.setAttribute("msg", "教材已添加到购物车！");
		return "f:/TextBookServlet?method=findAllStock";
	}

	/**
	 * 清空购物车
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		Car car = (Car) request.getSession().getAttribute("car");
		if (car == null || car.getCartItems().size() == 0) {
			request.setAttribute("msg", "购物车无可清空图书！");
			return "f:/shoppingtrolley.jsp";
		}
		carService.clear(user);
		Map<Integer, Buycar> map = carService.carItemMap(user);
		car.setMap(map);
		return "f:/shoppingtrolley.jsp";
	}

	/**
	 * 删除条目
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
		Buycar buycar = new Buycar();
		String strid = request.getParameter("id");
		System.out.println(strid);
		if (strid == null) {
			return "r:/TextBookServlet?method=findAllStock";
		}
		int id = Integer.parseInt(strid);
		TextBook textbook = new TextBook();
		textbook.setId(id);
		Stock stock = new Stock();
		stock.setTextbook(textbook);
		buycar.setUser(user);
		buycar.setStock(stock);
		carService.delete(buycar);
		Car car = (Car) request.getSession().getAttribute("car");
		Map<Integer, Buycar> map = carService.carItemMap(user);
		car.setMap(map);
		return "f:/shoppingtrolley.jsp";

	}

	/**
	 * 查看购物车
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String lookcar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "f:/login.jsp";
		}
		Car car = (Car) request.getSession().getAttribute("car");
		Map<Integer, Buycar> map = carService.carItemMap(user);
		car.setMap(map);
		System.out.println(car);
		return "f:/shoppingtrolley.jsp";
	}

}

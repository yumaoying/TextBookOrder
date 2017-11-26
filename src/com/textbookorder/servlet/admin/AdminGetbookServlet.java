package com.textbookorder.servlet.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.Getbook;
import com.textbookorder.domain.TextBook;
import com.textbookorder.domain.User;
import com.textbookorder.service.GetBookService;
import com.textbookorder.service.OrderService;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 领书管理表述层
 * 
 * @author Administrator
 * 
 */
public class AdminGetbookServlet extends BaseServlet {
	private GetBookService getService = new GetBookService();
	private OrderService orderService = new OrderService();

	/**
	 * 登记购书时，生成领书单
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
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int orderItemid = Integer.parseInt(request.getParameter("orderItemid"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String location = request.getParameter("location");
		TextBook textbook = new TextBook();
		User user = new User();
		textbook.setId(bid);
		user.setUid(uid);
		Getbook getbook = new Getbook();
		getbook.setUser(user);
		getbook.setLocation(location);
		getbook.setTextbook(textbook);
		getbook.setAmount(amount);
		getbook.setDate(new Date());
		getbook.setOitemid(orderItemid);
		try {
			getService.add(getbook);
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return findAll(request, response);
	}

	/**
	 * 查找所有领书单
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
		List<Getbook> getbooklist = getService.findAll();
		request.setAttribute("getbooklist", getbooklist);
		return "/Admin/booklistM/AlsdQuery.jsp";
	}

	/**
	 * 查找某个订单项的领书单信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByoitemid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		int oitemid = Integer.parseInt(request.getParameter("oitemid"));
		Getbook getbook = getService.findByoitemid(oitemid);
		request.setAttribute("getbook", getbook);
		return "/Admin/booklistM/AlsdModify.jsp";
	}

	public String findGroup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String lookup = request.getParameter("lookup");
		List<Getbook> getbooklist = getService.findGroup(lookup);
		request.setAttribute("getbooklist", getbooklist);
		return "/Admin/booklistM/AlsdQuery.jsp";
	}

	/**
	 * 由账号查找用户领书单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String finfByuid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		String userid = request.getParameter("userid");
		if (userid == null || userid.trim().isEmpty()) {
			request.setAttribute("msg", "用户账号为空");
			return "/Admin/booklistM/AlsdUserQuery.jsp";
		}
		List<Getbook> getbooklist = getService.findByUid(userid);
		request.setAttribute("getbooklist", getbooklist);
		return "/Admin/booklistM/AlsdUserQuery.jsp";
	}

	/**
	 * 修改某条领书单记录前加载该信息到修改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateBygidPre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		int gid = Integer.parseInt(request.getParameter("gid"));
		Getbook getbook = getService.findByGid(gid);
		request.setAttribute("getbook", getbook);
		return "/Admin/booklistM/AlsdModify.jsp";
	}

	/**
	 * 修改某条领书单记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public String updateBygid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		Getbook getbook = CommonUtils.toBean(request.getParameterMap(),
				Getbook.class);
		TextBook textbook = CommonUtils.toBean(request.getParameterMap(),
				TextBook.class);
		getbook.setUser(user);
		getbook.setTextbook(textbook);
		System.out.println(getbook);
		getService.updateBygid(getbook);
		request.setAttribute("msg", "修改成功！");
		request.setAttribute("getbook", getbook);
		return "/Admin/booklistM/AlsdModify.jsp";
	}

	/**
	 * 删除某用户领书单,并取消登记
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteByuid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			getService.noRegisterByuid(uid);
			request.setAttribute("msg", "操作成功！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return findAll(request, response);
	}

	/**
	 * 修改某个用户领书时间和地点
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
		if (loginAdmin.getRight()[4].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		Getbook getbook = CommonUtils.toBean(request.getParameterMap(),
				Getbook.class);
		TextBook textbook = CommonUtils.toBean(request.getParameterMap(),
				TextBook.class);
		getbook.setUser(user);
		getbook.setTextbook(textbook);
		System.out.println(getbook);
		getService.update(getbook);
		return finfByuid(request, response);
	}

	/**
	 * 删除某条领书单记录，不修改登记状态
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteBygid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")
				|| loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int gid = Integer.parseInt(request.getParameter("gid"));
		getService.deleteBygid(gid);
		request.setAttribute("msg", "操作成功！");
		return findAll(request, response);
	}

	/**
	 * 删除某条领书单记录,并取消登记领书
	 * 
	 * @param userid
	 * @return
	 */
	public String deleteoitemid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")
				|| loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int oitemid = Integer.parseInt(request.getParameter("oitemid"));
		try {
			getService.quitregister(oitemid);
			request.setAttribute("msg", "删除成功,已取消订单登记购书状态！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return findAll(request, response);
	}

	/**
	 * 用户确认领书
	 * 
	 * @param userid
	 * @return
	 */
	public String sureGetbook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")
				|| loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			getService.sureGetBook(uid);
			request.setAttribute("msg", "领书成功！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return findAll(request, response);
	}

	/**
	 * 某订单确认领书
	 * 
	 * @param userid
	 * @return
	 */
	public String sureGetbooByoid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			return "r:/login.jsp";
		}
		if (loginAdmin.getRight()[4].equals("0")
				|| loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有该操作的权限，请和系统管理员联系!");
			return "f:/Admin/AIndex.jsp";
		}
		int oid = Integer.parseInt(request.getParameter("0id"));
		int gid = Integer.parseInt(request.getParameter("gid"));
		try {
			getService.sureGetBookByoid(oid, gid);
			request.setAttribute("msg", "领书成功！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return findAll(request, response);
	}
}

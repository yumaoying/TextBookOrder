package com.textbookorder.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.Getbook;
import com.textbookorder.domain.User;
import com.textbookorder.service.GetBookService;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 教师学生领书单表示层
 * 
 * @author Administrator
 * 
 */
public class GetBookServlet extends BaseServlet {
	private GetBookService getService = new GetBookService();

	/**
	 * 查找用户领书单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByuid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		User usersession = (User) request.getSession().getAttribute(
				"login_user");
		if (usersession == null) {
			return "r:/login.jsp";
		}
		List<Getbook> getbooklist = getService.findByUid(user.getUid());
		request.setAttribute("getbooklist", getbooklist);
		return "/allOrder.jsp";
	}

	/**
	 * 用户确认领书
	 * 
	 * @param userid
	 * @return
	 */
	public String sureGetbook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("login_user");
		if (user == null) {
			return "r:/login.jsp";
		}
		int uid = Integer.parseInt(request.getParameter("uid"));
		try {
			getService.sureGetBook(uid);
			request.setAttribute("msg", "领书成功！");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		// 返回教材库
		return "f:/TextBookServlet?method=findAllStock";
	}
}

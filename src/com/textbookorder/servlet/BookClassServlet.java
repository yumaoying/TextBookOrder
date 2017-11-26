package com.textbookorder.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.BookClass;
import com.textbookorder.service.BookClassService;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 前台图书分类表示层
 * 
 * @author Administrator
 * 
 */
public class BookClassServlet extends BaseServlet {
	private BookClassService classService = new BookClassService();

	/**
	 * 查找所有分类图书
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<BookClass> classList = classService.findAll();
		request.setAttribute("classList", classList);
		return "f:/system/bookClass/list.jsp";
	}
}

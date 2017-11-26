package com.textbookorder.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.domain.BookClass;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.TextBook;
import com.textbookorder.service.BookClassService;
import com.textbookorder.service.StoreService;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.servlet.BaseServlet;

/**
 * 教材信息表示层
 * 
 * @author Administrator
 * 
 */
public class TextBookServlet extends BaseServlet {
	private StoreService storeService = new StoreService();
	private BookClassService classService = new BookClassService();

	/**
	 * 加载所有库存图书信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAllStock(HttpServletRequest request,
			HttpServletResponse response) {
		List<Stock> stockList = storeService.findAllStock();
		request.setAttribute("stockList", stockList);
		List<BookClass> classList = classService.findAll();
		request.setAttribute("classList", classList);

		return "f:/index.jsp";
	}

	/**
	 * 加载某本图书信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) {
		int sid = Integer.parseInt(request.getParameter("sid"));
		Stock stock = storeService.loadBySid(sid);
		request.setAttribute("stock", stock);
		return "f:/bookDetail.jsp";
	}

	/**
	 * 组合模糊查询库存
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
		List<Stock> stockList = storeService.findGroupStock(stock);
		List<BookClass> classList = classService.findAll();
		request.setAttribute("classList", classList);
		request.setAttribute("stockList", stockList);
		return "f:/libraryQuery.jsp";
	}
}

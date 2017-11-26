package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.textbookorder.domain.BookClass;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.TextBook;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 库存Stock持久层
 * 
 * @author Administrator
 * 
 */
public class StockDao {
	private QueryRunner qr;

	public StockDao(QueryRunner qr2) {
		// TODO Auto-generated constructor stub
		this.qr = qr2;
	}

	public StockDao() {
		qr = new TxQueryRunner();
	}

	/**
	 * 添加库存记录
	 * 
	 * @param stock
	 */
	public void addStock(Stock stock) {
		String sql = "insert into stock(bookid,stocknumer) values(?,?)";
		try {
			qr.update(sql, stock.getTextbook().getId(), stock.getStocknumer());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改图书库存量
	 * 
	 * @param bookid
	 */
	public void modify(int bookid, int stocknumer) {
		String sql = "update stock set stocknumer=? where bookid=?";
		try {
			qr.update(sql, stocknumer, bookid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 在库存表中查找图书
	 * 
	 * @param bookid
	 * @return
	 */
	public Stock findBybookid(int bookid) {
		String sql = "select * from stock s,textbook t,bookclass c where s.bookid=t.id and t.cid=c.cid and bookid=?";
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), bookid);
			TextBook textbook = CommonUtils.toBean(map, TextBook.class);
			BookClass bookclass = CommonUtils.toBean(map, BookClass.class);
			textbook.setBookclass(bookclass);
			Stock s = CommonUtils.toBean(map, Stock.class);
			s.setTextbook(textbook);
			return s;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按库存编号查找图书
	 * 
	 * @param Sid
	 * @return
	 */
	public Stock findBySid(int sid) {
		String sql = "select * from stock s,textbook t,bookclass c where s.bookid=t.id and t.cid=c.cid  and  s.sid=?";
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), sid);
			TextBook textbook = CommonUtils.toBean(map, TextBook.class);
			BookClass bookclass = CommonUtils.toBean(map, BookClass.class);
			textbook.setBookclass(bookclass);
			Stock s = CommonUtils.toBean(map, Stock.class);
			s.setTextbook(textbook);
			return s;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 查找所有库存信息
	 * 
	 * @return
	 */
	public List<Stock> findAll() {
		String sql = "select * from stock s,textbook t,bookclass c where s.bookid=t.id and t.cid=c.cid ";
		List<Stock> stockList = new ArrayList<Stock>();
		List<Map<String, Object>> maplist;
		try {
			maplist = qr.query(sql, new MapListHandler());
			if (maplist == null) {
				return null;
			}
			for (Map<String, Object> map : maplist) {
				TextBook textbook = CommonUtils.toBean(map, TextBook.class);
				BookClass bookclass = CommonUtils.toBean(map, BookClass.class);
				textbook.setBookclass(bookclass);
				Stock s = CommonUtils.toBean(map, Stock.class);
				s.setTextbook(textbook);
				stockList.add(s);
			}
			return stockList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 多条件组合查询
	 * 
	 * @param stock
	 * @param stocknumermin2
	 * @param stocknumermin
	 * @return
	 */
	public List<Stock> findGroupStock(Stock stock) {
		String sql = "select * from stock s,textbook t,bookclass c where s.bookid=t.id and t.cid=c.cid ";
		List<Object> params = new ArrayList<Object>();
		String isbn = stock.getTextbook().getIsbn();
		if (isbn != null && !isbn.trim().isEmpty()) {
			sql = sql + " and isbn like ?";
			params.add("%" + isbn + "%");
		}
		String bookname = stock.getTextbook().getBookname();
		if (bookname != null && !bookname.trim().isEmpty()) {
			sql = sql + " and bookname like ?";
			params.add("%" + bookname + "%");
		}
		String author = stock.getTextbook().getAuthor();
		if (author != null && !author.trim().isEmpty()) {
			sql = sql + " and author like ?";
			params.add("%" + author + "%");
		}
		String publisher = stock.getTextbook().getPubliser();
		if (publisher != null && !publisher.trim().isEmpty()) {
			sql = sql + " and publiser like ?";
			params.add("%" + publisher + "%");
		}
		int bc = stock.getTextbook().getBookclass().getCid();
		if (bc > 0) {
			sql = sql + " and c.cid=?";
			params.add(bc);
		}
		if (stock.getStocknumer() > 0) {
			sql = sql + " and s.stocknumer<?";
			params.add(stock.getStocknumer());
		}
		try {
			List<Stock> stockList = new ArrayList<Stock>();
			List<Map<String, Object>> maplist = qr.query(sql,
					new MapListHandler(), params.toArray());
			if (maplist == null) {
				return null;
			}
			for (Map<String, Object> map : maplist) {
				TextBook textbook = CommonUtils.toBean(map, TextBook.class);
				BookClass bookclass = CommonUtils.toBean(map, BookClass.class);
				textbook.setBookclass(bookclass);
				Stock s = CommonUtils.toBean(map, Stock.class);
				s.setTextbook(textbook);
				stockList.add(s);
			}
			return stockList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除库存记录
	 * 
	 * @param sid
	 * @throws UserException
	 */
	public void delete(int sid) throws UserException {
		String sql = "delete from stock where sid=?";
		try {
			qr.update(sql, sid);
		} catch (SQLException e) {
			throw new UserException("该表与其他表关联，无法删除");
		}
	}

	/**
	 * 修改缺书状态
	 * 
	 * @param islack
	 * @param sid
	 */
	public void updateIslack(int islack, int sid) {
		String sql = "update stock  set islack=? where sid=?";
		try {
			qr.update(sql, islack, sid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

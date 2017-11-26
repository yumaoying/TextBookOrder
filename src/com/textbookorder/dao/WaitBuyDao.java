package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.textbookorder.domain.WaitBuyBook;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 采购数据层持久层
 * 
 * @author Administrator
 * 
 */
public class WaitBuyDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 添加采购记录
	 * 
	 * @param book
	 */
	public void add(WaitBuyBook book) {
		String sql = "insert into waitbuy(isbn,bookname,plantmount,author,publisher,publishtime) values(?,?,?,?,?,?)";
		Object[] params = { book.getIsbn(), book.getBookname(),
				book.getPlantmount(), book.getAuthor(), book.getPublisher(),
				book.getPublishtime() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按编号查找采购表
	 * 
	 * @param sid
	 */
	public WaitBuyBook findBywid(int wid) {
		String sql = "select * from waitbuy where wid=?";
		try {
			return qr.query(sql,
					new BeanHandler<WaitBuyBook>(WaitBuyBook.class), wid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 所有采购记录
	 * 
	 * @param sid
	 */
	public List<WaitBuyBook> findAll() {
		String sql = "select * from waitbuy";
		List<WaitBuyBook> waitlist = new ArrayList<WaitBuyBook>();

		try {
			waitlist = qr.query(sql, new BeanListHandler<WaitBuyBook>(
					WaitBuyBook.class));
			return waitlist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改采购表
	 * 
	 * @param book
	 */
	public void update(WaitBuyBook book) {
		String sql = "update waitbuy set isbn=?,bookname=?,plantmount=?,author=?,publisher=?,publishtime=? where wid=?";
		Object[] params = { book.getIsbn(), book.getBookname(),
				book.getPlantmount(), book.getAuthor(), book.getPublisher(),
				book.getPublishtime(), book.getWid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除某采购记录
	 * 
	 * @param wid
	 */
	public void deleteBywid(int wid) {
		String sql = "delete from waitbuy where  wid=?";
		try {
			qr.update(sql, wid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除所有采购记录
	 */
	public void deleteAll() {
		String sql = "delete from waitbuy";
		try {
			qr.update(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<WaitBuyBook> find(String lookup) {
		String sql = "select * from waitbuy where 1=1  ";
		List<Object> params = new ArrayList<Object>();
		if (lookup != null && !lookup.trim().isEmpty()) {
			sql = sql + "  and  (bookname  like  ?";
			params.add("%" + lookup + "%");
			sql = sql + "  or    isbn like  ?";
			params.add("%" + lookup + "%");
			sql = sql + "  or    author  like  ?";
			params.add("%" + lookup + "%");
			sql = sql + " or   publisher  like ?";
			params.add("%" + lookup + "%)");
		}
		try {
			return qr.query(sql, new BeanListHandler<WaitBuyBook>(
					WaitBuyBook.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

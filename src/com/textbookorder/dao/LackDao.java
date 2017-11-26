package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.textbookorder.domain.LackBook;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 缺书单持久层
 * 
 * @author Administrator
 * 
 */
public class LackDao {
	private QueryRunner qr = new TxQueryRunner();

	/***
	 * 登记缺书
	 * 
	 * @param lackbook
	 */
	public void add(LackBook lackbook) {
		String sql = "insert into lackbook(amount,lackdate,stockid,itemid,uid) values(?,?,?,?,?)";
		Object[] params = { lackbook.getAmount(), lackbook.getLackdate(),
				lackbook.getStockid(), lackbook.getItemid(), lackbook.getUid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 所有的缺书单信息
	 * 
	 * @return
	 */
	public List<LackBook> findAll() {
		String sql = "select * from lackbook ";
		List<LackBook> lacklist = new ArrayList<LackBook>();
		try {
			lacklist = qr.query(sql, new BeanListHandler<LackBook>(
					LackBook.class));
			return lacklist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改缺书记录
	 * 
	 * @param lackid
	 * @param amount
	 */
	public void update(int lackid, int amount) {
		String sql = "update lackbook set amount=? where lackid=?";
		try {
			qr.update(sql, amount, lackid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除缺书单信息
	 * 
	 * @param lackid
	 */
	public void delete(int lackid) {
		String sql = "delete from lackbook where lackid=?";
		try {
			qr.update(sql, lackid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找某缺书编号的信息
	 * 
	 * @param lackid
	 * @return
	 */
	public LackBook findBylackid(int lackid) {
		String sql = "select * from lackbook where lackid=?";
		try {
			return qr.query(sql, new BeanHandler<LackBook>(LackBook.class),
					lackid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找某库存编号的缺书信息
	 * 
	 * @param lackid
	 * @return
	 */
	public LackBook findBysid(int stockid) {
		String sql = "select * from lackbook where stockid=?";
		try {
			return qr.query(sql, new BeanHandler<LackBook>(LackBook.class),
					stockid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除某库存编号的缺书信息
	 * 
	 * @param lackid
	 * @return
	 */
	public void deleteBysid(int stockid) {
		String sql = "delete from lackbook where stockid=?";
		try {
			qr.update(sql, stockid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

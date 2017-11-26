package com.textbookorder.service;

import java.sql.SQLException;
import java.util.List;

import com.textbookorder.dao.LackDao;
import com.textbookorder.dao.OrderDao;
import com.textbookorder.dao.StockDao;
import com.textbookorder.domain.LackBook;
import com.textbookorder.tools.jdbc.JdbcUtils;

/*
 * 缺书管理业务层
 */
public class LackService {
	private LackDao lackdao = new LackDao();
	private StockDao stockdao = new StockDao();
	private OrderDao orderdao = new OrderDao();

	/***
	 * 登记缺书
	 * 
	 * @param lackbook
	 * @throws UserException
	 */
	public void add(LackBook lackbook) throws UserException {
		// 开启事务
		try {
			JdbcUtils.beginTransaction();
			stockdao.updateIslack(1, lackbook.getStockid()); // 更新库存为缺书
			if (lackbook.getItemid() > 0) {
				orderdao.updateIslack(1, lackbook.getItemid()); // 更新订单为缺书状态
			}
			lackdao.add(lackbook);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new UserException("登记缺书信息失败! " + e.getMessage());
			}
			throw new UserException("登记缺书信息失败! " + e.getMessage());
		}

	}

	public List<LackBook> findAll() {
		return lackdao.findAll();
	}

	/**
	 * 修改缺书记录
	 * 
	 * @param lackid
	 * @param amount
	 */
	public void update(int lackid, int amount) {
		lackdao.update(lackid, amount);
	}

	/**
	 * 删除缺书单信息,同时更改库存的缺书状态为未登记缺书，订单的缺书状态为未缺书
	 * 
	 * @param lackid
	 */
	public void delete(int stockid, int itemid) throws UserException {
		try {
			JdbcUtils.beginTransaction();
			stockdao.updateIslack(0, stockid);
			lackdao.delete(stockid);
			if (itemid != 0) {
				orderdao.updateIslack(1, itemid);
			}
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new UserException("删除缺书信息失败! " + e.getMessage());
			}
			throw new UserException("删除缺书信息失败! " + e.getMessage());

		}
	}

	/**
	 * 查找某缺书编号的库存信息
	 * 
	 * @param lackid
	 * @return
	 */
	public LackBook findBylackid(int lackid) {
		return lackdao.findBylackid(lackid);

	}

	/**
	 * 查找某库存编号的缺书信息
	 * 
	 * @param lackid
	 * @return
	 */
	public LackBook findBysid(int stockid) {
		return lackdao.findBysid(stockid);
		// return lackdao.findBylackid(stockid);
	}
}

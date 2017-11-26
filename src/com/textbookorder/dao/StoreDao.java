package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.textbookorder.domain.Come;
import com.textbookorder.domain.LackBook;
import com.textbookorder.domain.Stock;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.jdbc.JdbcUtils;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 库存管理持久层
 * 
 * @author Administrator
 * 
 */
public class StoreDao {
	private QueryRunner qr = new TxQueryRunner();
	private TextBookDao bookDao = new TextBookDao(qr);
	private ComeDao comeDao = new ComeDao(qr);
	private StockDao stockDao = new StockDao(qr);
	private LackDao lackdao = new LackDao();
	private OrderDao orderDao = new OrderDao();

	/**
	 * 新图书入库
	 * 
	 * @param come
	 * @throws UserException
	 */
	public void newBookCome(Come come) throws UserException {
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			// 在图书表中添加图书
			bookDao.add(come.getTextbook());
			/*
			 * TextBook textbook = bookDao
			 * .findByIsbn(come.getTextbook().getIsbn());
			 */
			come.setTextbook(come.getTextbook());
			// 图书入库
			comeDao.add(come);
			// 修改库存记录
			Stock addStock = new Stock();
			addStock.setTextbook(come.getTextbook());
			addStock.setStocknumer(come.getComenumber());
			stockDao.addStock(addStock);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new UserException("数据库操作异常，入库失败");
			}
			throw new UserException("数据库操作异常，入库失败");
		}
	}

	/**
	 * 已有图书入库
	 * 
	 * @param come
	 * @throws UserException
	 */
	public void existBookCome(Come come) throws UserException {
		System.out.println(come.getTextbook().getId());
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			// 图书入库
			comeDao.add(come);
			// 查找在库存表中是否存在记录
			Stock stock = stockDao.findBybookid(come.getTextbook().getId());
			// 库存表中无记录则在库存表中插入
			if (stock == null) {
				Stock addStock = new Stock();
				addStock.setTextbook(come.getTextbook());
				addStock.setStocknumer(come.getComenumber());
				stockDao.addStock(addStock);
			} else {
				// 修改库存量
				stockDao.modify(come.getTextbook().getId(),
						stock.getStocknumer() + come.getComenumber());
				// 查找是否存该库存编号的缺书信息
				LackBook lackbook = lackdao.findBysid(stock.getSid());
				if (lackbook != null) {
					// 如果登记缺书数量小于进货数量，更新缺书数量
					if (lackbook.getAmount() > come.getComenumber()) {
						lackdao.update(lackbook.getLackid(),
								lackbook.getAmount() - come.getComenumber());
					} else {
						// 删除缺书记录
						lackdao.deleteBysid(stock.getSid());
						// 如果有该缺书记录来源于订单,修改订单缺书状态为未缺书
						if (lackbook.getItemid() != 0) {
							orderDao.updateIslack(0, lackbook.getItemid());
						}
					}

				}

			}
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new UserException("数据库操作异常，操作失败");
			}
			throw new UserException("数据库操作异常，操作失败");
		}

	}

	/**
	 * 查找所有入库图书
	 */
	public List<Come> findAllCome() {
		return comeDao.findAll();
	}

	public void deleteCome(int id) {
		comeDao.delete(id);
	}

	/**
	 * 修改入库记录
	 * 
	 * @param come
	 */
	public void modifyCome(Come come) {
		try {
			JdbcUtils.beginTransaction();
			bookDao.modify(come.getTextbook());
			comeDao.modify(come);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}

	}

	/**
	 * 按入库编号查找图书
	 * 
	 * @param comeid
	 * @return
	 */
	public Come loadByComeid(int comeid) {
		return comeDao.load(comeid);
	}

	public List<Come> findByGroup(String[] params) {
		return comeDao.findByGroup(params);

	}

	/**
	 * 组合查询库存
	 * 
	 * @param stock
	 * @param stocknumermax
	 * @param stocknumermin
	 * @return
	 */
	public List<Stock> findGroupStock(Stock stock) {
		return stockDao.findGroupStock(stock);
	}

	/**
	 * 库存修改
	 * 
	 * @param stock
	 */
	public void modifyStock(Stock stock) {
		try {
			JdbcUtils.beginTransaction();
			bookDao.modify(stock.getTextbook());
			stockDao.modify(stock.getTextbook().getId(), stock.getStocknumer());
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找所有库存记录
	 * 
	 * @return
	 */
	public List<Stock> findAllStock() {
		return stockDao.findAll();

	}

	public Stock loadBySid(int sid) {
		return stockDao.findBySid(sid);
	}

	/**
	 * 删除库存记录
	 * 
	 * @param sid
	 * @throws UserException
	 */
	public void deleteStock(int sid) throws UserException {
		stockDao.delete(sid);

	}

	/**
	 * 按图书编号查找
	 * 
	 * @param bid
	 * @return
	 */
	public Stock findBybookid(int bid) {
		return stockDao.findBybookid(bid);
	}

	/**
	 * 修改缺书状态
	 * 
	 * @param islack
	 * @param sid
	 */
	public void updateIslack(int islack, int sid) {
		stockDao.updateIslack(islack, sid);

	}
}

package com.textbookorder.service;

import java.sql.SQLException;
import java.util.List;

import com.textbookorder.dao.OrderDao;
import com.textbookorder.dao.OutDao;
import com.textbookorder.dao.StockDao;
import com.textbookorder.domain.Out;
import com.textbookorder.domain.Stock;
import com.textbookorder.tools.jdbc.JdbcUtils;

/**
 * 出库管理业务层
 * 
 * @author Administrator
 * 
 */
public class OutService {
	private OutDao outDao = new OutDao();
	private OrderDao orderDao = new OrderDao();
	private StockDao stockDao = new StockDao();

	/**
	 * 出库
	 * 
	 * @param out
	 * @throws UserException
	 */
	public void add(Out out) throws UserException {
		try {
			JdbcUtils.beginTransaction();
			// 修改订单为发货状态，并添加出库记录，减少库存量
			Stock stock = stockDao.findBySid(out.getStock().getSid());
			if (stock == null || stock.getSid() == 0) {
				throw new UserException("该库存信息不存在，无法操作!");
			}
			if (stock.getStocknumer() < out.getOutamount()) {
				throw new UserException("当前库存不足，无法操作!");
			}
			orderDao.updateState(2, out.getItemid());
			outDao.add(out);
			int nowstocknumer = stock.getStocknumer() - out.getOutamount();
			stockDao.modify(stock.getTextbook().getId(), nowstocknumer);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.commitTransaction();
			} catch (SQLException e1) {
				throw new UserException("数据库操作失败!");
			}
			throw new UserException("操作失败!");
		}

	}

	public void update(Out out) {
		outDao.update(out);
	}

	/**
	 * 按出库编号查找
	 * 
	 * @param outid
	 * @return
	 */
	public Out findByoutid(int outid) {
		return outDao.findByoutid(outid);
	}

	/**
	 * 查找所有的出库记录
	 * 
	 * @param outid
	 * @return
	 */
	public List<Out> findAll() {
		return outDao.findAll();
	}

	public List<Out> findByGroup(String[] params) {
		return outDao.findByGroup(params);
	}
}

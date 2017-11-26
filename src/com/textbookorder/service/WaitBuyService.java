package com.textbookorder.service;

import java.util.List;

import com.textbookorder.dao.StockDao;
import com.textbookorder.dao.WaitBuyDao;
import com.textbookorder.domain.WaitBuyBook;

/*
 *采购业务层
 */
public class WaitBuyService {
	private WaitBuyDao waitDao = new WaitBuyDao();
	private StockDao stockDao = new StockDao();

	/**
	 * 添加采购记录
	 * 
	 * @param book
	 * @throws UserException
	 */
	public void add(WaitBuyBook book) {
		waitDao.add(book);
	}

	/**
	 * 所有采购记录
	 * 
	 * @param sid
	 */
	public List<WaitBuyBook> findAll() {
		return waitDao.findAll();
	}

	/**
	 * 按编号查找采购表
	 * 
	 * @param sid
	 */
	public WaitBuyBook findBywid(int wid) {
		return waitDao.findBywid(wid);
	}

	/**
	 * 修改采购表
	 * 
	 * @param book
	 */
	public void update(WaitBuyBook book) {
		waitDao.update(book);
	}

	/**
	 * 删除某采购记录
	 * 
	 * @param wid
	 */
	public void deleteBywid(int wid) {
		waitDao.deleteBywid(wid);
	}

	/**
	 * 删除所有采购记录
	 */
	public void deleteAll() {
		waitDao.deleteAll();
	}

	/**
	 * 模糊查询
	 * 
	 * @param lookup
	 * @return
	 */
	public List<WaitBuyBook> find(String lookup) {
		return waitDao.find(lookup);
	}

}

package com.textbookorder.service;

import java.util.List;

import com.textbookorder.dao.StoreDao;
import com.textbookorder.dao.TextBookDao;
import com.textbookorder.domain.Come;
import com.textbookorder.domain.Stock;

/**
 * 库存管理业务层
 * 
 * @author Administrator
 * 
 */
public class StoreService {
	private StoreDao storeDao = new StoreDao();
	private TextBookDao bookDao = new TextBookDao();

	/**
	 * 新书图书入库
	 * 
	 * @param come
	 * @throws UserException
	 */
	public void newBookCome(Come come) throws UserException {
		/*
		 * TextBook textbook = bookDao.findByIsbn(come.getTextbook().getIsbn());
		 * if (textbook != null) { throw new
		 * UserException("该isbn的图书已存在！你可以在已有图书中选择入库"); }
		 */

		storeDao.newBookCome(come);
	}

	/**
	 * 已有图书入库
	 * 
	 * @param come
	 * @throws UserException
	 */
	public void existBookCome(Come come) throws UserException {

		storeDao.existBookCome(come);

	}

	/**
	 * 查看所有入库图书
	 * 
	 * @return
	 */
	public List<Come> findAllCome() {
		return storeDao.findAllCome();
	}

	/**
	 * 按入库编号查找
	 * 
	 * @param comeid
	 * @return
	 */
	public Come loadByComeid(int comeid) {
		return storeDao.loadByComeid(comeid);

	}

	/**
	 * 修改入库
	 * 
	 * @param come
	 */
	public void modifyCome(Come come) {
		storeDao.modifyCome(come);

	}

	/**
	 * 删除入库记录
	 * 
	 * @param comeid
	 */
	public void deleteCome(int comeid) {
		storeDao.deleteCome(comeid);

	}

	public List<Come> findByGroup(String[] params) {
		return storeDao.findByGroup(params);

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
		return storeDao.findGroupStock(stock);

	}

	/**
	 * 修改库存
	 * 
	 * @param stock
	 */
	public void modifyStock(Stock stock) {
		storeDao.modifyStock(stock);

	}

	/**
	 * 查找所有库存记录
	 * 
	 * @return
	 */
	public List<Stock> findAllStock() {
		return storeDao.findAllStock();

	}

	/**
	 * 按库存编号加载图书
	 * 
	 * @param sid
	 * @return
	 */
	public Stock loadBySid(int sid) {
		return storeDao.loadBySid(sid);

	}

	public Stock findBybookid(int bookid) {
		return storeDao.findBybookid(bookid);
	}

	/**
	 * 删除库存记录
	 * 
	 * @param sid
	 * @throws UserException
	 */
	public void deleteStock(int sid) throws UserException {
		storeDao.deleteStock(sid);

	}

	/**
	 * 修改图片
	 * 
	 * @param img
	 */
	public void uploadImg(int id, String img) {
		// TODO Auto-generated method stub
		bookDao.uploadImg(id, img);
	}

	/**
	 * 修改缺书状态
	 * 
	 * @param islack
	 * @param sid
	 */
	public void updateIslack(int islack, int sid) {
		storeDao.updateIslack(islack, sid);
	}
}

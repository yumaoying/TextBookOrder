package com.textbookorder.service;

import java.sql.SQLException;
import java.util.List;

import com.textbookorder.dao.StockDao;
import com.textbookorder.dao.TextBookDao;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.TextBook;
import com.textbookorder.tools.jdbc.JdbcUtils;

public class TextBookService {
	private TextBookDao bookDao = new TextBookDao();

	/**
	 * 查询所有图书
	 * 
	 * @return
	 */
	public List<TextBook> findAll() {
		return bookDao.findAll();
	}

	/**
	 * 加载某本图书
	 * 
	 * @param id
	 * @return
	 */
	public TextBook load(int id) {
		return bookDao.findById(id);

	}

	/**
	 * 分类查询
	 * 
	 * @param cid
	 * @return
	 */
	public List<TextBook> findByCid(int cid) {
		return bookDao.findByCid(cid);
	}

	/**
	 * 多条件组合查询
	 * 
	 * @param textbook
	 * @return
	 */
	public List<TextBook> groupSelect(TextBook textbook) {
		return bookDao.groupSelect(textbook);
	}

	/**
	 * 模糊查询
	 * 
	 * @param textbook
	 * @return
	 */
	public List<TextBook> mohuSelect(TextBook textbook) {
		return bookDao.mohuSelect(textbook);
	}

	/***
	 * 添加图书
	 * 
	 * @param textbook
	 * @throws UserException
	 */
	public void add(TextBook textbook) throws UserException {
		StockDao stockDao = new StockDao();
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			// 在图书表中添加图书
			bookDao.add(textbook);
			List<TextBook> booklist = bookDao.findByIsbn(textbook.getIsbn());
			int index = booklist.lastIndexOf(textbook);
			TextBook textbook2 = booklist.get(index);
			// 修改库存记录
			Stock addStock = new Stock();
			addStock.setTextbook(textbook2);
			addStock.setStocknumer(0);
			stockDao.addStock(addStock);
			JdbcUtils.commitTransaction();
			// 查找库存信息并返回
			// Stock s = stockDao.findBybookid(textbook.getId());

			// return s;
		} catch (SQLException e) {
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new UserException("数据库操作异常，添加失败");
			}
			throw new UserException("数据库操作异常，添加失败");
		}
		/*
		 * TextBook book = bookDao.findByIsbn(textbook.getIsbn()); if (book !=
		 * null) { throw new UserException("该isbn的图书已存在"); }
		 */
	}

	/**
	 * 删除图书
	 * 
	 * @param id
	 * @throws UserException
	 */
	public void delete(int id) throws UserException {
		bookDao.delete(id);
	}

	/**
	 * 修改图书
	 * 
	 * @param textbook
	 * @throws UserException
	 */
	public void modify(TextBook textbook) throws UserException {
		/*
		 * // 判断修改的图书isbn号是否已存在 TextBook book =
		 * bookDao.findByIsbn(textbook.getIsbn()); if (book != null &&
		 * book.getId() != textbook.getId()) { throw new
		 * UserException("该isbn已存在，无法修改！"); }
		 */
		bookDao.modify(textbook);
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
	 * 按isbn号查找
	 * 
	 * @param isbn
	 * @return
	 */
	public List<TextBook> findByIsbn(String isbn) {
		return bookDao.findByIsbn(isbn);
	}

	/**
	 * 按图书名号查找
	 * 
	 * @param isbn
	 * @return
	 */
	public List<TextBook> findByBookName(String bookname) {
		return bookDao.findByBookName(bookname);
	}

	public List<TextBook> findByBookAuthor(String author) {
		return bookDao.findByBookAuthor(author);
	}

}

package com.textbookorder.service;

import java.util.List;

import com.textbookorder.dao.BookClassDao;
import com.textbookorder.dao.TextBookDao;
import com.textbookorder.domain.BookClass;

/**
 * 业务层（图书分类）
 * 
 * @author Administrator
 * 
 */
public class BookClassService {
	private BookClassDao classDao = new BookClassDao();
	private TextBookDao bookDao = new TextBookDao();

	/**
	 * 按分类名查找图书分类
	 * 
	 * @param className
	 */
	public BookClass findByClassName(String className) {
		return classDao.findByClassName(className);
	}

	/**
	 * 加载图书分类信息
	 * 
	 * @param id
	 */
	public BookClass load(int id) {
		return classDao.load(id);
	}

	/**
	 * 查找所有分类图书
	 * 
	 * @return
	 */
	public List<BookClass> findAll() {

		return classDao.findAll();
	}

	/**
	 * 添加图书分类
	 * 
	 * @param className
	 * @throws UserException
	 */
	public void addBookClass(String className) throws UserException {
		BookClass bookClass = classDao.findByClassName(className);
		if (bookClass != null) {
			throw new UserException("该图书分类已存在！");
		}
		classDao.addBookClass(className);
	}

	/**
	 * 删除图书分类
	 * 
	 * @param id
	 * @throws UserException
	 */
	public void delete(int cid) throws UserException {
		int count = bookDao.findCountByCid(cid);
		if (count > 0) {
			throw new UserException("该分类下存在图书，无法删除！");
		}
		classDao.delete(cid);
	}

	/**
	 * 修改图书分类
	 * 
	 * @param bookclass
	 * @throws UserException
	 */
	public void modify(BookClass bookclass) throws UserException {
		BookClass bc = classDao.findByClassName(bookclass.getClassname());
		if (bc != null) {
			throw new UserException("该图书分类已存在!");
		}
		bc = classDao.load(bookclass.getCid());
		if (bc == null) {
			throw new UserException("该图书分类不存在!");
		}
		classDao.modify(bookclass);
	}

}

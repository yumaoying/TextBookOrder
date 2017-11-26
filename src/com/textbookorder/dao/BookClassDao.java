package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.textbookorder.domain.BookClass;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 图书分类持久层
 * 
 * @author Administrator
 * 
 */
public class BookClassDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 按图书分类名查找图书
	 * 
	 * @param bookclss
	 * @return
	 */
	public BookClass findByClassName(String classname) {
		String sql = "select * from bookclass where classname=?";
		try {
			return qr.query(sql, new BeanHandler<BookClass>(BookClass.class),
					classname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 按编号查找图书分类
	 */
	public BookClass load(int cid) {
		String sql = "select * from bookclass where cid=?";
		try {
			return qr.query(sql, new BeanHandler<BookClass>(BookClass.class),
					cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 添加图书分类
	 * 
	 * @param bookclass
	 */
	public void addBookClass(String bookclass) {
		String sql = "insert into bookclass(classname) values(?)";
		try {
			qr.update(sql, bookclass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询所有图书分类
	 * 
	 * @return
	 */
	public List<BookClass> findAll() {
		String sql = "select *  from  bookclass";
		try {
			return qr.query(sql,
					new BeanListHandler<BookClass>(BookClass.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除图书分类
	 */

	public void delete(int cid) {
		String sql = "delete from bookclass where cid=?";
		try {
			qr.update(sql, cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 修改图书分类
	 */
	public void modify(BookClass bookclass) {
		String sql = "update bookclass set classname=? where cid=?";
		try {
			qr.update(sql, bookclass.getClassname(), bookclass.getCid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

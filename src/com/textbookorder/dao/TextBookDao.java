package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.textbookorder.domain.BookClass;
import com.textbookorder.domain.TextBook;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 教材信息持久层
 * 
 * @author Administrator
 * 
 */
public class TextBookDao {
	private QueryRunner qr = new TxQueryRunner();

	public TextBookDao() {
		super();
	}

	/**
	 * 
	 * @param qr
	 */
	public TextBookDao(QueryRunner qr) {
		super();
		this.qr = qr;
	}

	/**
	 * 查询所有图书
	 * 
	 * @return
	 */
	public List<TextBook> findAll() {
		String sql = "select * from textbook t,bookclass c where  t.cid=c.cid ";
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			maplist = qr.query(sql, new MapListHandler());
			return bookList(maplist);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param maplist
	 * @return
	 */
	public List<TextBook> bookList(List<Map<String, Object>> maplist) {
		List<TextBook> bookList = new ArrayList<TextBook>();
		for (Map<String, Object> map : maplist) {
			TextBook textBook = textBook(map);
			bookList.add(textBook);
		}
		return bookList;
	}

	/**
	 * 将map进行转换
	 * 
	 * @param map
	 * @return
	 */
	public TextBook textBook(Map<String, Object> map) {
		TextBook textBook = CommonUtils.toBean(map, TextBook.class);
		BookClass bookClass = CommonUtils.toBean(map, BookClass.class);
		textBook.setBookclass(bookClass);
		return textBook;
	}

	/**
	 * 某个图书信息
	 * 
	 * @param id
	 * @return
	 */
	public TextBook findById(int id) {
		String sql = "select * from textbook t,bookclass c where  t.cid=c.cid  and id=?";
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), id);
			return textBook(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 分类查询
	 * 
	 * @param cid
	 * @return
	 */
	public List<TextBook> findByCid(int cid) {
		String sql = "select * from textbook t,bookclass c where  t.cid=c.cid  and  c.cid=?";
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			maplist = qr.query(sql, new MapListHandler(), cid);
			return bookList(maplist);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按 isbn查找
	 * 
	 * @param isbn
	 * @return
	 */
	public List<TextBook> findByIsbn(String isbn) {
		String sql = "select * from textbook t,bookclass c  where  t.cid=c.cid  and  isbn=?";
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			maplist = qr.query(sql, new MapListHandler(), isbn);
			return bookList(maplist);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按图书名查找
	 * 
	 * @param bookname
	 * @return
	 */
	public List<TextBook> findByBookName(String bookname) {
		String sql = "select * from textbook t,bookclass c where  t.cid=c.cid  and  bookname=?";
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			maplist = qr.query(sql, new MapListHandler(), bookname);
			return bookList(maplist);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按作者查询
	 * 
	 * @param author
	 * @return
	 */
	public List<TextBook> findByBookAuthor(String author) {
		String sql = "select * from textbook t,bookclass c where  t.cid=c.cid  and author=?";
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			maplist = qr.query(sql, new MapListHandler(), author);
			return bookList(maplist);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加图书
	 * 
	 * @param textbook
	 */
	public void add(TextBook textbook) {
		String sql = "insert into textbook(isbn,bookname,cid,author,publiser,publishtime,price,comeprice,saleprice,details,bookpicture) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { textbook.getIsbn(), textbook.getBookname(),
				textbook.getBookclass().getCid(), textbook.getAuthor(),
				textbook.getPubliser(), textbook.getPublishtime(),
				textbook.getPrice(), textbook.getComeprice(),
				textbook.getSaleprice(), textbook.getDetails(),
				textbook.getBookpicture() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除图书
	 * 
	 * @param id
	 * @throws UserException
	 */
	public void delete(int id) throws UserException {
		String sql = "delete from  textbook where id=?";
		try {
			qr.update(sql, id);
		} catch (SQLException e) {
			throw new UserException("无法删除,该图书与其他表关联");
		}
	}

	/**
	 * 修改图书
	 * 
	 * @param textbook
	 */
	public void modify(TextBook textbook) {
		String sql = "update textbook set ISBN=?, bookname=?,cid=?,author=?,publiser=?,publishtime=?,price=?,comeprice=?,saleprice=?,details=?,bookpicture=? where id=?";
		Object[] params = { textbook.getIsbn(), textbook.getBookname(),
				textbook.getBookclass().getCid(), textbook.getAuthor(),
				textbook.getPubliser(), textbook.getPublishtime(),
				textbook.getPrice(), textbook.getComeprice(),
				textbook.getSaleprice(), textbook.getDetails(),
				textbook.getBookpicture(), textbook.getId() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询某类图书下的图书数量
	 * 
	 * @param id
	 * @return
	 */
	public int findCountByCid(int cid) {
		String sql = "select count(*) from textbook where cid=?";
		try {
			Number cn = (Number) qr.query(sql, new ScalarHandler(), cid);
			return cn.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 多条件组合查询
	 * 
	 * 
	 * @param textbook
	 * @return
	 */
	public List<TextBook> groupSelect(TextBook textbook) {
		String sql = "select * from textbook where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		String isbn = textbook.getIsbn();
		if (isbn != null && !isbn.trim().isEmpty()) {
			sql = sql + " and  ISBN like ?";
			params.add("%" + isbn + "%");
		}
		String bookname = textbook.getBookname();
		if (bookname != null && !bookname.trim().isEmpty()) {
			sql = sql + " and  bookname like ?";
			params.add("%" + bookname + "%");
		}
		String author = textbook.getAuthor();
		if (author != null && !author.trim().isEmpty()) {
			sql = sql + " and  author like ?";
			params.add("%" + author + "%");
		}
		try {
			return qr.query(sql, new BeanListHandler<TextBook>(TextBook.class),
					params.toArray());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 模糊查询
	 * 
	 * @param textbook
	 * @return
	 */
	public List<TextBook> mohuSelect(TextBook textbook) {
		String sql = "select * from textbook where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		String isbn = textbook.getIsbn();
		if (isbn != null && !isbn.trim().isEmpty()) {
			sql = sql + " and  ( ISBN like ?";
			params.add("%" + isbn + "%");
		}
		String bookname = textbook.getBookname();
		if (bookname != null && !bookname.trim().isEmpty()) {
			sql = sql + " or   bookname like ?";
			params.add("%" + bookname + "%");
		}
		String author = textbook.getAuthor();
		if (author != null && !author.trim().isEmpty()) {
			sql = sql + " or   author like ? )";
			params.add("%" + author + "%");
		}
		try {
			return qr.query(sql, new BeanListHandler<TextBook>(TextBook.class),
					params.toArray());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 上传图片
	 * 
	 * @param id
	 * @param img
	 */
	public void uploadImg(int id, String img) {
		String sql = "update textbook set bookpicture=? where id=?";
		try {
			qr.update(sql, img, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

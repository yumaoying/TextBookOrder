package com.textbookorder.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.Come;
import com.textbookorder.domain.TextBook;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 入库持久层
 * 
 * @author Administrator
 * 
 */
public class ComeDao {
	private QueryRunner qr;

	public ComeDao() {
		qr = new TxQueryRunner();
	}

	public ComeDao(QueryRunner qr) {
		super();
		this.qr = qr;
	}

	/**
	 * 图书入库
	 * 
	 * @param come
	 */
	public void add(Come come) {
		String sql = "insert into come(bookid,comenumber,comedate,suplyer,suplyphone,adminid) values(?,?,?,?,?,?)";
		Object[] params = { come.getTextbook().getId(), come.getComenumber(),
				come.getComedate(), come.getSuplyer(), come.getSuplyphone(),
				come.getAdmin().getAid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按入库编号查找
	 * 
	 * @param comeid
	 * @return
	 */
	public Come load(int comeid) {
		String sql = "select * from come c1,textbook b1 ,admin a1 where c1.bookid=b1.id and c1.adminid=a1.aid and comeid=?";
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), comeid);
			Come come = toCover(map);
			return come;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找所有入库图书
	 * 
	 * @return
	 */
	public List<Come> findAll() {
		String sql = "select *  from come c1,textbook b1 ,admin a1 where c1.bookid=b1.id and c1.adminid=a1.aid order by comedate desc";
		try {
			List<Come> comeList = new ArrayList<Come>();
			List<Map<String, Object>> maplist = qr.query(sql,
					new MapListHandler());
			if (maplist == null) {
				return null;
			}
			for (Map<String, Object> map : maplist) {
				Come come = toCover(map);
				comeList.add(come);
			}
			return comeList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	public Come toCover(Map<String, Object> map) {
		TextBook textbook = CommonUtils.toBean(map, TextBook.class);
		Admin admin = CommonUtils.toBean(map, Admin.class);
		Come come = CommonUtils.toBean(map, Come.class);
		come.setTextbook(textbook);
		come.setAdmin(admin);
		return come;
	}

	/**
	 * 删除库存记录
	 * 
	 * @param id
	 */
	public void delete(int comeid) {
		String sql = "delete from come where comeid=?";
		try {
			qr.update(sql, comeid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void modify(Come come) {
		String sql = "update come set comenumber=?,comedate=?,suplyer=?,suplyphone=?,adminid=?  where comeid=?";
		Object[] parames = { come.getComenumber(), come.getComedate(),
				come.getSuplyer(), come.getSuplyphone(),
				come.getAdmin().getAid(), come.getComeid() };
		try {
			qr.update(sql, parames);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 多条件组合查询入库记录
	 * 
	 * @param params
	 * @return
	 */
	public List<Come> findByGroup(String[] params) {
		String sql = "select *  from come c1,textbook b1 ,admin a1 where c1.bookid=b1.id and c1.adminid=a1.aid ";
		String starttime = params[0];
		String endtime = params[1];
		String isbn = params[2];
		String bookname = params[3];
		List<Object> paramlist = new ArrayList<Object>();
		if (starttime != null && !starttime.trim().isEmpty()) {
			sql = sql + " and comedate between ?";
			paramlist.add(starttime);
			if (endtime == null || endtime.trim().isEmpty()) {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");
				endtime = sm.format(new Date());

			}
			sql = sql + " and ?";
			paramlist.add(endtime);
		}

		if (isbn != null && !isbn.trim().isEmpty()) {
			sql = sql + " and isbn like ?";
			paramlist.add("%" + isbn + "%");
		}
		if (bookname != null && !bookname.trim().isEmpty()) {
			sql = sql + " and bookname like ?";
			paramlist.add("%" + bookname + "%");
		}
		try {
			List<Come> comeList = new ArrayList<Come>();
			List<Map<String, Object>> maplist = qr.query(sql,
					new MapListHandler(), paramlist.toArray());
			if (maplist == null) {
				return null;
			}
			for (Map<String, Object> map : maplist) {
				Come come = toCover(map);
				comeList.add(come);
			}
			return comeList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

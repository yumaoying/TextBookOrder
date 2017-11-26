package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.textbookorder.domain.Getbook;
import com.textbookorder.domain.TextBook;
import com.textbookorder.domain.User;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/*
 * 领书单持久层
 */
public class GetBookDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 添加领书单
	 * 
	 * @param getBook
	 */
	public void add(Getbook getBook) {
		String sql = "insert into getbook(oitemid,bookid,userid,amount,location,date) values(?,?,?,?,?,?)";
		Object[] params = { getBook.getOitemid(),
				getBook.getTextbook().getId(), getBook.getUser().getUid(),
				getBook.getAmount(), getBook.getLocation(), getBook.getDate() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找某领书单信息
	 * 
	 * @param gid
	 * @return
	 */
	public Getbook findByGid(int gid) {
		String sql = "select * from getbook g,textbook t,user u where g.bookid=t.id and u.uid=g.userid and  gid=?";
		Map<String, Object> map;
		try {
			map = qr.query(sql, new MapHandler(), gid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return toGetbook(map);
	}

	/**
	 * 查找用户的领书单
	 * 
	 * @param uid
	 * @return
	 */
	public List<Getbook> findByUid(int uid) {
		String sql = "select * from getbook g,textbook t,user u where g.bookid=t.id and u.uid=g.userid and  u.uid=?";
		List<Map<String, Object>> mapList = null;
		try {
			mapList = qr.query(sql, new MapListHandler(), uid);
			if (mapList == null) {
				return null;
			}
			return toGetbooklist(mapList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 查找某用户的领书单
	 * 
	 * @param userid
	 * @return
	 */
	public List<Getbook> findByUid(String userid) {
		String sql = "select * from getbook g,textbook t,user u where g.bookid=t.id and u.uid=g.userid and  u.userid=?";
		List<Map<String, Object>> mapList = null;
		try {
			mapList = qr.query(sql, new MapListHandler(), userid);
			if (mapList == null) {
				return null;
			}
			return toGetbooklist(mapList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找所有的领书单
	 * 
	 * @return
	 */
	public List<Getbook> findAll() {
		String sql = "select * from getbook g,textbook t,user u where g.bookid=t.id and u.uid=g.userid";
		List<Map<String, Object>> mapList = null;
		try {
			mapList = qr.query(sql, new MapListHandler());
			if (mapList == null) {
				return null;
			}
			return toGetbooklist(mapList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除某用户的领书单
	 * 
	 * @param userid
	 */
	public void delete(int userid) {
		String sql = "delete from getbook where userid=?";
		try {
			qr.update(sql, userid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除某订单条目的领书单记录
	 * 
	 * @param userid
	 */
	public void deleteByOitemid(int oitemid) {
		String sql = "delete from getbook where oitemid=?";
		try {
			qr.update(sql, oitemid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除某条领书单记录
	 * 
	 * @param userid
	 */
	public void deleteBygid(int gid) {
		String sql = "delete from getbook where gid=?";
		try {
			qr.update(sql, gid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改某用户领书时间和地点
	 * 
	 * @param getBook
	 */
	public void update(Getbook getBook) {
		String sql = "update getbook set date=? ,location=? where userid=?";
		Object[] params = { getBook.getDate(), getBook.getLocation(),
				getBook.getUser().getUid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 更新某条领书单记录
	 * 
	 * @param getbook
	 */
	public void updateBygid(Getbook getbook) {
		String sql = "update getbook set    date=? ,location=? where   gid=?";
		Object[] params = { getbook.getDate(), getbook.getLocation(),
				getbook.getUser().getUid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将Getbook转化为list
	 * 
	 * @param mapList
	 * @return
	 */
	public List<Getbook> toGetbooklist(List<Map<String, Object>> mapList) {
		List<Getbook> booklist = new ArrayList<Getbook>();
		for (Map<String, Object> map : mapList) {
			Getbook getbook = toGetbook(map);
			booklist.add(getbook);
		}
		return booklist;

	}

	public Getbook toGetbook(Map<String, Object> map) {
		Getbook getbook = CommonUtils.toBean(map, Getbook.class);
		TextBook textbook = CommonUtils.toBean(map, TextBook.class);
		User user = CommonUtils.toBean(map, User.class);
		getbook.setTextbook(textbook);
		getbook.setUser(user);
		return getbook;

	}

	public List<Getbook> findGroup(String lookup) {
		String sql = "select * from getbook g,textbook t,user u where g.bookid=t.id and u.uid=g.userid  ";
		List<Object> params = new ArrayList<Object>();
		if (lookup != null && !lookup.trim().isEmpty()) {
			sql = sql + "    and  ( u.userid  like  ?";
			params.add("%" + lookup + "%");
			sql = sql + "  or   u.username  like  ? ";
			params.add("%" + lookup + "%");
			sql = sql + "  or  t.isbn   like  ? ";
			params.add("%" + lookup + "%");
			sql = sql + "  or  t.bookname   like  ? ";
			params.add("%" + lookup + "%");
			sql = sql + "  or  g. location  ? ";
			params.add("%" + lookup + "% )");
		}
		List<Map<String, Object>> mapList = null;
		try {
			mapList = qr.query(sql, new MapListHandler(), params.toArray());
			if (mapList == null) {
				return null;
			}
			return toGetbooklist(mapList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}

package com.textbookorder.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.Out;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.TextBook;
import com.textbookorder.domain.User;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 出库持久层
 * 
 * @author Administrator
 * 
 */
public class OutDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 出库
	 * 
	 * @param out
	 */
	public void add(Out out) {
		String sql = "insert into outbook(sid,itemid,userid,outamount,outdate,adminid) values(?,?,?,?,?,?)";
		Object[] params = { out.getStock().getSid(), out.getItemid(),
				out.getUser().getUid(), out.getOutamount(), out.getOutdate(),
				out.getAdmin().getAid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改出库记录
	 * 
	 * @param out
	 */
	public void update(Out out) {
		String sql = "update outbook set sid=?,userid=?,outamount=?,outdate=?,adminid=? where outid=?";
		Object[] params = { out.getStock().getSid(), out.getUser().getUid(),
				out.getOutamount(), out.getOutdate(), out.getAdmin().getAid(),
				out.getOutid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按出库编号查找
	 * 
	 * @param outid
	 * @return
	 */
	public Out findByoutid(int outid) {
		String sql = "select * from outbook o,stock s,textbook t,user u,admin a where o.sid=s.sid and s.bookid=t.id and o.userid=u.uid and o.adminid=a.aid and outid=?";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = qr.query(sql, new MapHandler(), outid);
			return toOut(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找所有的出库记录
	 * 
	 * @param outid
	 * @return
	 */
	public List<Out> findAll() {
		String sql = "select * from outbook o,stock s,textbook t,user u,admin a where o.sid=s.sid and s.bookid=t.id and o.userid=u.uid and o.adminid=a.aid ";
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		try {
			maplist = qr.query(sql, new MapListHandler());
			return toOutList(maplist);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将每个Maplist转化为出库列表
	 * 
	 * @param maplist
	 * @return
	 */
	public List<Out> toOutList(List<Map<String, Object>> maplist) {
		List<Out> outlist = new ArrayList<Out>();
		for (Map<String, Object> map : maplist) {
			Out out = toOut(map);
			outlist.add(out);
		}
		return outlist;
	}

	/**
	 * 将map转换为其他对象
	 * 
	 * @param map
	 * @return
	 */
	public Out toOut(Map<String, Object> map) {
		Out out = CommonUtils.toBean(map, Out.class);
		Stock stock = CommonUtils.toBean(map, Stock.class);
		TextBook textbook = CommonUtils.toBean(map, TextBook.class);
		stock.setTextbook(textbook);
		User user = CommonUtils.toBean(map, User.class);
		Admin admin = CommonUtils.toBean(map, Admin.class);
		out.setStock(stock);
		out.setAdmin(admin);
		out.setUser(user);
		return out;
	}

	/**
	 * 多条件组合查询出库记录
	 * 
	 * @param params
	 * @return
	 */
	public List<Out> findByGroup(String[] params) {
		String sql = "select * from outbook o,stock s,textbook t,user u,admin a where o.sid=s.sid and s.bookid=t.id and o.userid=u.uid and o.adminid=a.aid ";
		String starttime = params[0];
		String endtime = params[1];
		String isbn = params[2];
		String bookname = params[3];
		String adminid = params[4];
		String userid = params[5];
		List<Object> paramlist = new ArrayList<Object>();
		if (starttime != null && !starttime.trim().isEmpty()) {
			sql = sql + " and outdate between ?";
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

		if (adminid != null && !adminid.trim().isEmpty()) {
			sql = sql + " and  a.adminid like ? or a.name like ?";
			paramlist.add("%" + adminid + "%");
			sql = sql + "  or a.name like ?";
			paramlist.add("%" + adminid + "%");
		}
		if (userid != null && !userid.trim().isEmpty()) {
			sql = sql + " and  u.userid like ? or a.name like ?";
			paramlist.add("%" + userid + "%");
			sql = sql + "  or u.username like ?";
			paramlist.add("%" + userid + "%");
		}
		try {
			List<Out> outList = new ArrayList<Out>();
			List<Map<String, Object>> maplist = qr.query(sql,
					new MapListHandler(), paramlist.toArray());
			if (maplist == null) {
				return null;
			}
			for (Map<String, Object> map : maplist) {
				Out come = toOut(map);
				outList.add(come);
			}
			return outList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

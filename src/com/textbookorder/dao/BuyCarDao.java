package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.textbookorder.domain.Buycar;
import com.textbookorder.domain.Stock;
import com.textbookorder.domain.TextBook;
import com.textbookorder.domain.User;
import com.textbookorder.tools.commons.CommonUtils;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 购物车持久层
 * 
 * @author Administrator
 * 
 */
public class BuyCarDao {
	private QueryRunner qr = new TxQueryRunner();
	private StockDao stockDao = new StockDao();

	/**
	 * 查找某用户的购物车条目
	 * 
	 * @param user
	 * @return
	 */
	public List<Buycar> carItems(User user) {
		String sql = "select * from  buycar c,stock s  where c.bookid=s.bookid and c.userid=?";
		try {
			List<Map<String, Object>> maplist = qr.query(sql,
					new MapListHandler(), user.getUid());
			List<Buycar> carList = new ArrayList<Buycar>();
			for (Map<String, Object> map : maplist) {
				Stock stock = CommonUtils.toBean(map, Stock.class);
				stock = stockDao.findBybookid(stock.getTextbook().getId());
				Buycar buycar = CommonUtils.toBean(map, Buycar.class);
				buycar.setStock(stock);
				buycar.setUser(user);
				carList.add(buycar);
			}
			return carList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 得到购物车条目map
	 */
	public Map<Integer, Buycar> carItemMap(User user) {
		String sql = "select * from  buycar c,textbook t where c.bookid=t.id and c.userid=?";
		try {
			List<Map<String, Object>> maplist = qr.query(sql,
					new MapListHandler(), user.getUid());
			Map<Integer, Buycar> carItemMap = new HashMap<Integer, Buycar>();
			for (Map<String, Object> map : maplist) {
				Buycar buycar = CommonUtils.toBean(map, Buycar.class);
				TextBook textbook = CommonUtils.toBean(map, TextBook.class);
				Stock stock = stockDao.findBybookid(textbook.getId());
				buycar.setStock(stock);
				buycar.setUser(user);
				carItemMap.put(stock.getTextbook().getId(), buycar);
			}

			return carItemMap;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找某用户的某书是否存在
	 * 
	 * @param user
	 * @param bid
	 * @return
	 */
	public Buycar findByBid(User user, int bid) {
		String sql = "select * from  buycar  where bookid=? and userid=?";
		try {
			return qr.query(sql, new BeanHandler<Buycar>(Buycar.class), bid,
					user.getUid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 加载某用户某图书的购物车条目
	 * 
	 * @param bid
	 * @return
	 */
	public Buycar load(User user, int bid) {
		String sql = "select * from  buycar c,stock s  where c.bookid=s.bookid and c.bookid=? and userid=?";
		try {
			Map<String, Object> map = qr.query(sql, new MapHandler(), bid,
					user.getUid());
			if (map == null) {
				return null;
			}
			Stock stock = CommonUtils.toBean(map, Stock.class);
			stock = stockDao.findBybookid(stock.getTextbook().getId());
			Buycar buycar = CommonUtils.toBean(map, Buycar.class);
			buycar.setStock(stock);
			buycar.setUser(user);
			return buycar;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 添加新的购物车条目
	 * 
	 * @param buycar
	 */
	public void add(Buycar buycar) {
		String sql = "insert into buycar(userid,bookid,buymount) values(?,?,?)";
		try {
			qr.update(sql, buycar.getUser().getUid(), buycar.getStock()
					.getTextbook().getId(), buycar.getBuymount());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 更新数量
	 * 
	 * @param buycar
	 */
	public void updateBuymount(Buycar buycar) {
		String sql = "update buycar set buymount=? where bookid=? and userid=?";
		try {
			qr.update(sql, buycar.getBuymount(), buycar.getStock()
					.getTextbook().getId(), buycar.getUser().getUid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除某用户的某购物条目
	 * 
	 * @param buycar
	 */
	public void delete(Buycar buycar) {
		String sql = "delete from  buycar where bookid=? and userid=?";
		try {
			qr.update(sql, buycar.getStock().getTextbook().getId(), buycar
					.getUser().getUid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 清除某用户的购物车
	 * 
	 * @param user
	 */
	public void clear(User user) {
		String sql = "delete from  buycar where userid=?";
		try {
			qr.update(sql, user.getUid());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

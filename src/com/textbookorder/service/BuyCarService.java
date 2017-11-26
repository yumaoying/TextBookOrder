package com.textbookorder.service;

import java.util.Map;

import com.textbookorder.dao.BuyCarDao;
import com.textbookorder.domain.Buycar;
import com.textbookorder.domain.User;

/**
 * 购物车管理业务层
 * 
 * @author Administrator
 * 
 */
public class BuyCarService {
	private BuyCarDao buydao = new BuyCarDao();

	// /**
	// * 查找某用户的购物车条目
	// *
	// * @param user
	// * @return
	// */
	// public List<Buycar> carItems(User user) {
	// return buydao.carItems(user);
	// }

	/**
	 * 查找某用户某图书的购物车条目
	 * 
	 * @param bid
	 * @return
	 */
	public Buycar findByBid(User user, int bid) {
		return buydao.findByBid(user, bid);
	}

	/**
	 * 加载某购物车的某条详细信息
	 * 
	 * @param user
	 * @param bid
	 * @return
	 */
	public Buycar load(User user, int bid) {
		return buydao.findByBid(user, bid);
	}

	/**
	 * 删除某购物条目
	 * 
	 * @param buycar
	 */
	public void delete(Buycar buycar) {
		buydao.delete(buycar);
	}

	/**
	 * 清除某用户的购物车
	 * 
	 * @param user
	 */
	public void clear(User user) {
		buydao.clear(user);
	}

	/**
	 * 添加购物车条目
	 * 
	 * @param buycar
	 */
	public void addToCar(Buycar buycar) {
		Buycar b = buydao.findByBid(buycar.getUser(), buycar.getStock()
				.getTextbook().getId());
		if (b == null) {
			// 没有该用户的对应购物条目，直接插入
			buydao.add(buycar);
		} else {
			// 更新数量
			int currentNum = b.getBuymount() + buycar.getBuymount();
			buycar.setBuymount(currentNum);
			buydao.updateBuymount(buycar);
		}
	}

	/**
	 * 更新数量
	 * 
	 * @param buycar
	 */
	public void updateBuymount(Buycar buycar) {
		buydao.updateBuymount(buycar);
	}

	/**
	 * 获得用户购物车
	 * 
	 * @param carItems
	 * @return
	 */
	public Map<Integer, Buycar> carItemMap(User user) {
		return buydao.carItemMap(user);
	}

}

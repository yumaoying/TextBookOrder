package com.textbookorder.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车
 * 
 * @author Administrator
 * 
 */
public class Car {
	private Map<Integer, Buycar> map = new LinkedHashMap<Integer, Buycar>();

	public Map<Integer, Buycar> getMap() {
		return map;
	}

	public void setMap(Map<Integer, Buycar> map) {
		this.map = map;
	}

	public Car() {
		super();
	}

	/**
	 * 计算合计
	 * 
	 * @return
	 */
	public double getTotal() {
		// 合计=所有条目的小计之和
		BigDecimal total = new BigDecimal("0");
		for (Buycar buycar : map.values()) {
			BigDecimal subtotal = new BigDecimal("" + buycar.getSubtotal());
			total = total.add(subtotal);
		}
		return total.doubleValue();
	}

	// /**
	// * 添加条目到车中
	// *
	// * @param bc
	// */
	// public void add(Buycar bc) {
	// if (map.containsKey(bc.getStock().getTextbook().getId())) {//
	// 判断原来车中是否存在该条目
	// Buycar c = map.get(bc.getStock().getTextbook().getId());
	// c.setBuymount(c.getBuymount() + bc.getBuymount());
	// map.put(bc.getStock().getTextbook().getId(), c);
	// } else {
	// map.put(bc.getStock().getTextbook().getId(), bc);
	// }
	// }
	//
	// /**
	// * 清空所有条目
	// */
	// public void clear() {
	// map.clear();
	// }
	//
	// /**
	// * 删除指定条目
	// *
	// * @param bid
	// */
	// public void delete(String bid) {
	// map.remove(bid);
	// }

	/**
	 * 获取所有条目
	 * 
	 * @return
	 */
	public Collection<Buycar> getCartItems() {
		return map.values();
	}

	@Override
	public String toString() {
		return "Car [map=" + map + "]";
	}

}

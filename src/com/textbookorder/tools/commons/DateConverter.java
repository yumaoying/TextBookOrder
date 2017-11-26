package com.textbookorder.tools.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

/**
 * 把String转换为java.util.Date类型
 * 
 * @author Administrator
 * 
 */
public class DateConverter implements Converter {

	public Object convert(Class type, Object value) {
		if (value == null)
			return null;// 如果要转换成值为null，那么直接返回null
		if (!(value instanceof String)) {// 如果要转换的值不是String，那么就不转换了，直接返回
			return value;
		}
		String val = (String) value;// 把值转换成String
		if (val.trim().isEmpty()) {
			return null;
		}
		// 使用SimpleDateFormat进行转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(val);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}

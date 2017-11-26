package com.textbookorder.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@Entity
public class EncodingRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	private String charset;

	public EncodingRequest(HttpServletRequest request, String charset) {
		super(request);
		this.request = request;
		this.charset = charset;
	}

	/*
	 * // 重写getParameter(getParameter(String name) 方法 public String
	 * getParameter(String name) {
	 * 
	 * HttpServletRequest request = (HttpServletRequest) getRequest(); String
	 * method = request.getMethod(); if (method.equalsIgnoreCase("post")) { //
	 * 如果是post请求，直接设置编码，并返回 try { request.setCharacterEncoding(charset); } catch
	 * (UnsupportedEncodingException e) { } } else if
	 * (method.equalsIgnoreCase("get")) { // 如果是get请求，获取参数，将参数转码，返回转码后的值 String
	 * value = request.getParameter(name); if (value == null) return null; try {
	 * value = new String(value.getBytes("ISO-8859-1"), charset); } catch
	 * (UnsupportedEncodingException e) { } return value; } return
	 * request.getParameter(name);
	 * 
	 * // 获取参数 String value = request.getParameter(name); if (value == null)
	 * return null;// 如果为null，直接返回null try { // 对参数进行编码处理后返回 return new
	 * String(value.getBytes("ISO-8859-1"), charset); } catch
	 * (UnsupportedEncodingException e) { throw new RuntimeException(e); } }
	 */

	@Override
	public Map getParameterMap() {
		Map<String, String[]> map = request.getParameterMap();
		if (map == null)
			return map;
		// 遍历map，对每个值进行编码处理
		for (String key : map.keySet()) {
			String[] values = map.get(key);
			for (int i = 0; i < values.length; i++) {
				try {
					values[i] = new String(values[i].getBytes("ISO-8859-1"),
							charset);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
		}
		// 处理后返回
		return map;
	}

	@Override
	public String getParameter(String name) {
		// 获取参数
		String value = request.getParameter(name);
		if (value == null)
			return null;// 如果为null，直接返回null
		try {
			// 对参数进行编码处理后返回
			return new String(value.getBytes("ISO-8859-1"), charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		for (int i = 0; i < values.length; i++) {
			try {
				values[i] = new String(values[i].getBytes("ISO-8859-1"),
						charset);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return values;
	}

}

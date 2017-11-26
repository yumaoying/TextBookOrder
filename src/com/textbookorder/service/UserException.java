package com.textbookorder.service;

/**
 * 异常处理
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class UserException extends Exception {
	public UserException() {
		super();
	}

	public UserException(String message) {
		super(message);
	}

}

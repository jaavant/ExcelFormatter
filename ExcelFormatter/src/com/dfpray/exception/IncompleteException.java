package com.dfpray.exception;

public class IncompleteException extends Exception {
	/**
	 *  Incomplete code
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	
	public IncompleteException(String msg){
		this.msg = msg;
	}
}

package com.dfpray.exception;

public class EmptyListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String msg;
	
	
	public EmptyListException(String msg){
		this.msg = msg;
	}


	public EmptyListException() {
	}
}

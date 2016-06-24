package com.dfpray.exception;

public class CardNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg;

	public CardNotFoundException(){
		
	}
	
	public CardNotFoundException(String msg){
		this.msg = msg;
	}
}

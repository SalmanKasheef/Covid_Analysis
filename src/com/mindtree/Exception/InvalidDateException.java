package com.mindtree.Exception;

public class InvalidDateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidDateException(String Message) {
		System.out.println(Message);
	}
}

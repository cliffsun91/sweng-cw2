package com.acmetelecom.exception;

public class CustomerNameMismatchException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public CustomerNameMismatchException(String message) {
		super(message);
	}

}

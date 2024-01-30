package com.fetch.processor.exception;

public class ReceiptFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 2L;

	public ReceiptFoundException(String message) {	
		super(message);	
	}

}

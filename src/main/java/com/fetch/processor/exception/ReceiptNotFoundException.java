package com.fetch.processor.exception;

public class ReceiptNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ReceiptNotFoundException(String message) {	
		super(message);	
	}
}

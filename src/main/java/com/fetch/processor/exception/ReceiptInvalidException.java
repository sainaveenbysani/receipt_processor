package com.fetch.processor.exception;

public class ReceiptInvalidException extends RuntimeException {
	
	private static final long serialVersionUID = 3L;
	
	public ReceiptInvalidException(String message) {
		super(message);
	}
}

package com.fetch.processor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ReceiptNotFoundException.class)
	public ResponseEntity<ErrorResponse> ReceiptNotFoundExceptionHandler(ReceiptNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND) ;	
	}
	
	@ExceptionHandler(ReceiptFoundException.class)
	public ResponseEntity<ErrorResponse> ReceiptFoundExceptionHandler(ReceiptFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.ALREADY_REPORTED) ;	
	}
	
	@ExceptionHandler(ReceiptInvalidException.class)
	public ResponseEntity<ErrorResponse> ReceiptInvalidExceptionHandler(ReceiptInvalidException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST) ;	
	}
	 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = "The receipt is invalid";
        return ResponseEntity.badRequest().body(errorMessage);
    }
}

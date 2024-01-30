package com.fetch.processor.exception;

import lombok.Data;

@Data
public class ErrorResponse {

	private Integer status;
	
	private String errorMessage;
	
	public ErrorResponse(String errorMessage, Integer status) {
	 this.status = status;
	 this.errorMessage = errorMessage;
	}
		
	@Override
	public String toString() {
		return "Error Response { Message :" +
				errorMessage + " , Status : " + status + "}";
	}

}

package com.nagarro.orderservice.exception.response;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;


public class ExceptionResponse {

	private final String message;
	
	private final HttpStatus status;
	
	private final ZonedDateTime timestamp;

	public ExceptionResponse(String message, HttpStatus status, ZonedDateTime timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	
	
	
	
}

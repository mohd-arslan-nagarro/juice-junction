package com.nagarro.orderservice.exception;

public class OrderAllreadyPresentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderAllreadyPresentException(String message) {
		super(message);

	}
}

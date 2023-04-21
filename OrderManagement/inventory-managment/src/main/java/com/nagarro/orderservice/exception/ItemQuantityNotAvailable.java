package com.nagarro.orderservice.exception;

public class ItemQuantityNotAvailable extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ItemQuantityNotAvailable(String message) {
		super(message);

	}
}

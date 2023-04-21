package com.nagarro.orderservice.exception;

@SuppressWarnings("serial")
public class ItemNotFoundException extends RuntimeException{

	public ItemNotFoundException(String message) {
		super(message);
		
	}

}

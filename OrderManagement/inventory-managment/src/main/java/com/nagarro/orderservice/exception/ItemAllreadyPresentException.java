package com.nagarro.orderservice.exception;

@SuppressWarnings("serial")
public class ItemAllreadyPresentException extends RuntimeException{

	public ItemAllreadyPresentException(String message) {
		super(message);
		
	}

}

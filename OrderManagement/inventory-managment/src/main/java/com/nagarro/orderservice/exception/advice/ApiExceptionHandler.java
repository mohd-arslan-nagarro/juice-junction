package com.nagarro.orderservice.exception.advice;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nagarro.orderservice.exception.CustomerNotFound;
import com.nagarro.orderservice.exception.ItemAllreadyPresentException;
import com.nagarro.orderservice.exception.ItemNotFoundException;
import com.nagarro.orderservice.exception.ItemQuantityNotAvailable;
import com.nagarro.orderservice.exception.OrderAllreadyPresentException;
import com.nagarro.orderservice.exception.OrderNotFound;
import com.nagarro.orderservice.exception.RequstNotValidException;
import com.nagarro.orderservice.exception.response.ExceptionResponse;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { RequstNotValidException.class })
	public ResponseEntity<ExceptionResponse> handleRequstNotValidException(RequstNotValidException e) {
		ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(value = { ItemNotFoundException.class })
	public ResponseEntity<ExceptionResponse> handleItemNotFoundException(ItemNotFoundException e) {
		ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { ItemAllreadyPresentException.class })
	public ResponseEntity<ExceptionResponse> handleItemAllreadyPresentException(ItemAllreadyPresentException e) {
		ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE,
				ZonedDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = { ItemQuantityNotAvailable.class })
	public ResponseEntity<ExceptionResponse> handleItemQuantityNotAvailable(ItemQuantityNotAvailable e) {
		ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE,
				ZonedDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = { CustomerNotFound.class })
	public ResponseEntity<ExceptionResponse> handleCustomerNotFound(CustomerNotFound e) {
		ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { OrderNotFound.class })
	public ResponseEntity<ExceptionResponse> handleOrderNotFound(OrderNotFound e) {
		ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { OrderAllreadyPresentException.class })
	public ResponseEntity<ExceptionResponse> handleOrderAllreadyPresentException(OrderAllreadyPresentException e) {
		ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE,
				ZonedDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException e) {
		ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				ZonedDateTime.now());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

package com.nagarro.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.orderservice.constant.Constant;
import com.nagarro.orderservice.dto.Customer;
import com.nagarro.orderservice.dto.Order;
import com.nagarro.orderservice.exception.RequstNotValidException;
import com.nagarro.orderservice.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(Constant.PATH_TO_CUSTOMERS)
public class CustomerController {

	@Autowired
	@Qualifier("CustomerServiceImpl")
	private CustomerService customerService;

	@GetMapping("/{id}")
	@ApiOperation(value = Constant.FIND_CUSTOMER, notes = Constant.NOTE_FOR_GET_CUSTOMER_HANDLER, response = Customer.class)
	public ResponseEntity<Customer> getCustomer(
			@ApiParam(value = Constant.CUSTOMER_ID_TYPE, required = true) @PathVariable("id") String id) {

		if (id.isEmpty())
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);

		final Customer customer = customerService.getCustomer(id);

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping("/{id}/orderhistory")
	@ApiOperation(value = Constant.FIND_ORDER_HISTORY, notes = Constant.NOTE_FOR_GET_ORDER_HISTORY_HANDLER, response = List.class)
	public ResponseEntity<List<Order>> getOrderHistoryOfCustomer(
			@ApiParam(value = Constant.CUSTOMER_ID_TYPE, required = true)
			@PathVariable("id") String id) {

		if (id.isEmpty())
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);

		final List<Order> orderHistory = customerService.getCustomerOrderHistory(id);

		return new ResponseEntity<List<Order>>(orderHistory, HttpStatus.OK);
	}

}

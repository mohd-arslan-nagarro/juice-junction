package com.nagarro.orderservice.service;

import java.util.List;

import com.nagarro.orderservice.dto.Customer;
import com.nagarro.orderservice.dto.Order;

public interface CustomerService {

	Customer getCustomer(String customerId);

	List<Order> getCustomerOrderHistory(String customerId);

}

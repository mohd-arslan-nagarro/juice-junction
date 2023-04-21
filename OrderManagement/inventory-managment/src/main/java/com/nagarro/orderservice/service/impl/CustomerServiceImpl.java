package com.nagarro.orderservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.orderservice.converter.Converter;
import com.nagarro.orderservice.dao.CustomerRepository;
import com.nagarro.orderservice.dao.OrderRepository;
import com.nagarro.orderservice.dto.Customer;
import com.nagarro.orderservice.dto.Order;
import com.nagarro.orderservice.exception.CustomerNotFound;
import com.nagarro.orderservice.exception.OrderNotFound;
import com.nagarro.orderservice.service.CustomerService;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private Converter converter;

	@Override
	public Customer getCustomer(String customerId) {

		final Optional<com.nagarro.orderservice.entity.Customer> customerOpt=customerRepository.findById(customerId);

		if(customerOpt.isEmpty()) {
			throw new CustomerNotFound("Customer Not Found For Customer ID -"+customerId);
		}

		final com.nagarro.orderservice.entity.Customer customerEntity=customerOpt.get();

		final Customer customer=converter.convertCustomerEntityToDto(customerEntity);

		return customer;
	}

	@Override
	public List<Order> getCustomerOrderHistory(String customerId) {

		final List<com.nagarro.orderservice.entity.Order> ordersEntity=orderRepository.findOrdersByCustomerId(customerId);

		if(ordersEntity.size()==0) {
			throw new OrderNotFound("NO ORDERS PLACED BY CUSTOMER WITH ID - "+customerId);
		}

		final List<Order> orders=converter.convertToListOrderDto(ordersEntity);

		return orders;
	}

}

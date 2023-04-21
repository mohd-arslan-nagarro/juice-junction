package com.nagarro.orderservice.service;

import java.util.Date;
import java.util.List;

import com.nagarro.orderservice.dto.Order;

public interface OrderService {

	Order placeOrder(Order order);

	Order getOrderByOrderId(long orderId);
	List<Order> getOrderByEmailId(String emailId);

	List<Order> getPendingOrders(String status);
	List<Order> getOrders();

	Boolean updateOrderStatus(long orderId,Order order);

	List<Order> getOrdersPurchasedOrSoldInTimeLine(Date from, Date to, String filterBy);

}

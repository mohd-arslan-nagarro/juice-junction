package com.nagarro.orderservice.controller;

import java.util.Date;
import java.util.List;

import com.nagarro.orderservice.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nagarro.orderservice.constant.Constant;
import com.nagarro.orderservice.dto.Order;
import com.nagarro.orderservice.exception.RequstNotValidException;
import com.nagarro.orderservice.service.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(Constant.PATH_TO_ORDERS)
public class OrderController {

	@Autowired
	@Qualifier("OrderServiceImpl")
	private OrderService orderService;

	@GetMapping
	@ApiOperation(value = Constant.FIND_ORDERS, notes = Constant.NOTE_FOR_GET_ORDERS_HANDLER)
	public ResponseEntity<List<Order>> handleGetOrders() {
		final List<Order> orders = orderService.getOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = Constant.FIND_ORDER, notes = Constant.NOTE_FOR_GET_ORDER_HANDLER)
	public ResponseEntity<Order> getOrder(
			@ApiParam(value = Constant.ORDER_ID_TYPE, required = true) @PathVariable("id") long orderId) {

		if (orderId == 0) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}

		final Order order = orderService.getOrderByOrderId(orderId);

		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	@GetMapping("/byEmail/{email}")
	@ApiOperation(value = Constant.FIND_ORDER, notes = Constant.NOTE_FOR_GET_ORDER_HANDLER)
	public ResponseEntity<List<Order>> getOrderByEmail(
			@ApiParam(value = Constant.ORDER_ID_TYPE, required = true) @PathVariable("email") String emailId) {

		if (emailId == null) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}

		final List<Order> orders = orderService.getOrderByEmailId(emailId);

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@GetMapping("/byStatus/{status}")
	@ApiOperation(value = Constant.FIND_ORDER, notes = Constant.NOTE_FOR_GET_ORDER_HANDLER)
	public ResponseEntity<List<Order>> getPendingOrders(@PathVariable("status") String status)
			{
				if (status == null) {
					throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
				}
				final List<Order> orders = orderService.getPendingOrders(status);
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}


	@GetMapping("/timeline")
	@ApiOperation(value = Constant.FIND_ORDERS_BY_FILTER_IN_TIMELINE, notes = Constant.NOTE_FOR_GET_ORDERS_BY_FILTER_IN_TIMELINE)
	public ResponseEntity<List<Order>> getFilteredOrdersInTimeLine(
			@ApiParam(value = Constant.DATE_PATTERN, required = true) @RequestParam @DateTimeFormat(pattern = Constant.DATE_PATTERN) Date fromDate,
			@ApiParam(value = Constant.DATE_PATTERN, required = true) @RequestParam @DateTimeFormat(pattern = Constant.DATE_PATTERN) Date toDate,
			@ApiParam(value = Constant.ORDER_FILTER_TYPE, required = true) @RequestParam String filterBy) {

		if (fromDate == null || toDate == null) {
			throw new RequstNotValidException(Constant.PROVIDE_VALID_TIMELINE);
		}

		final List<Order> filteredOrders = orderService.getOrdersPurchasedOrSoldInTimeLine(fromDate, toDate, filterBy);

		return new ResponseEntity<List<Order>>(filteredOrders, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = Constant.PLACE_ORDER, notes = Constant.NOTE_FOR_PLACE_ORDER)
	public ResponseEntity<Order> handlePlaceOrder(@RequestBody Order order) {
		if (order == null) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}
		System.out.println(order);
		final Order placedOrder = orderService.placeOrder(order);

		return new ResponseEntity<Order>(placedOrder, HttpStatus.CREATED);
	}
	@PatchMapping("/updateStatus")
//	@ApiOperation(value = Constant.PLACE_ORDER, notes = Constant.NOTE_FOR_PLACE_ORDER)
	public ResponseEntity<Boolean> updateOrderStatus(@RequestParam long orderId, @RequestBody Order order) {
		if (order == null || orderId == 0) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}

		boolean updateStatus= orderService.updateOrderStatus(orderId,order);

		return new ResponseEntity<Boolean>( updateStatus,HttpStatus.OK);

	}

}

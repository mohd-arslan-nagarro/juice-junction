package com.nagarro.orderservice.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {


	private long orderId;

	private String orderStatus;
	private Date purchasedDate;

	private Date soldDate;

	private LocalDateTime deliveryDate;

	private long orderedQuantity;

	private long orderedPrice;

	private Item item;
	private String address;
	private String email;
	private String paymentType;
	private Customer customer;

}

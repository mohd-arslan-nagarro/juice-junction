package com.nagarro.orderservice.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name ="order_details")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	private String orderStatus;
	@Temporal(TemporalType.DATE)
	private Date purchasedDate;

	@Temporal(TemporalType.DATE)
	private Date soldDate;
	private LocalDateTime deliveryDate;
	private String email;
	private String paymentType;
	private long orderedQuantity;
	private long orderedPrice;
	private String address;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customer;

}

package com.nagarro.orderservice.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class Item {
	
	private long productId;
	
	private String productName;
	
	private String productType;
	
	private Description productDescription;


	private long productPrice;
	
	private long quantity;
	
	private Boolean isActive;
	
	private List<Order> transcationDetail;

	public Item(long quantity) {
		super();
		this.quantity = quantity;
	}



}

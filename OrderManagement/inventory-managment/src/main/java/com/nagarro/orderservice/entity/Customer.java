package com.nagarro.orderservice.entity;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String customerId;

	private String name;

	@OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Order> customerOrders;

	public Customer(String customerId) {
		this.customerId=customerId;
	}

}

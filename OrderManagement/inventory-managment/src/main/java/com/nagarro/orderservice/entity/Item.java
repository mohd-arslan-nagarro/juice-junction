package com.nagarro.orderservice.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "inventory")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	private String productName;

	private String productType;

	@OneToOne(fetch = FetchType.EAGER ,cascade = javax.persistence.CascadeType.ALL)
	private Description productDescription;
//	private String Description;
	private long productPrice;

	private long quantity;
	
	private Boolean isActive;

	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Order> transcationDetail;

	public Item(long productId) {
		this.productId = productId;
	}


}

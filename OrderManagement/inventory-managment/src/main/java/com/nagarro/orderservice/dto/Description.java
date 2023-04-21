package com.nagarro.orderservice.dto;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Description {

	@Id
	private long id;

	private String color;

	private String specification;


}
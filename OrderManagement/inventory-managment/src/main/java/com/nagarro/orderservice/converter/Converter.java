package com.nagarro.orderservice.converter;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.orderservice.dto.Customer;
import com.nagarro.orderservice.dto.Description;
import com.nagarro.orderservice.dto.Item;
import com.nagarro.orderservice.entity.Order;

@Component
public class Converter {

	@Autowired
	private ModelMapper modelMapper;

	public Customer convertCustomerEntityToDto(com.nagarro.orderservice.entity.Customer customerEntity) {

		List<com.nagarro.orderservice.dto.Order> t = new ArrayList<com.nagarro.orderservice.dto.Order>();
		Customer customer = this.modelMapper.map(customerEntity, Customer.class);
		customer.setCustomerOrders(t);

		return customer;
	}

	public com.nagarro.orderservice.entity.Customer convertCustomerDtoToEntty(Customer customer) {

		List<Order> orders = new ArrayList<Order>();

		com.nagarro.orderservice.entity.Customer customerEntity = this.modelMapper.map(customer,
				com.nagarro.orderservice.entity.Customer.class);

		customerEntity.setCustomerOrders(orders);

		return customerEntity;

	}

	public Item convertItemEntityToDto(com.nagarro.orderservice.entity.Item itemEntity) {
		List<com.nagarro.orderservice.dto.Order> t = new ArrayList<com.nagarro.orderservice.dto.Order>();
		Item item = this.modelMapper.map(itemEntity, Item.class);
		item.setTranscationDetail(t);

		return item;
	}

	public com.nagarro.orderservice.entity.Item convertItemDtoToEntity(Item itemDto) {
		com.nagarro.orderservice.entity.Item itemEntity = this.modelMapper.map(itemDto,
				com.nagarro.orderservice.entity.Item.class);
		return itemEntity;
	}

	public List<Item> convertListToItemDtoList(List<com.nagarro.orderservice.entity.Item> itemsEntity) {

		List<Item> items = new ArrayList<Item>();

		itemsEntity.forEach(i -> items.add(convertItemEntityToDto(i)));

		return items;
	}

	public com.nagarro.orderservice.entity.Description convertDescriptionDtoToEntity(Description description) {
		return this.modelMapper.map(description, com.nagarro.orderservice.entity.Description.class);
	}

	public List<Order> convertToListOrderEntity(List<com.nagarro.orderservice.dto.Order> orders) {

		List<Order> ordersEntity = new ArrayList<Order>();

		orders.forEach(r -> {
			ordersEntity.add(modelMapper.map(r, Order.class));
		});

		return ordersEntity;
	}

	public Order convertOrderDtoToEntity(com.nagarro.orderservice.dto.Order order) {
		Order orderEntity = this.modelMapper.map(order, Order.class);

		return orderEntity;
	}

	public com.nagarro.orderservice.dto.Order convertOrderEntityTODto(Order orderEntity) {
		Item item = convertItemEntityToDto(orderEntity.getItem());
		Customer customer = convertCustomerEntityToDto(orderEntity.getCustomer());

		com.nagarro.orderservice.dto.Order order = new com.nagarro.orderservice.dto.Order();
		order.setCustomer(customer);
		order.setItem(item);
		order.setOrderedPrice(orderEntity.getOrderedPrice());
		order.setOrderedQuantity(orderEntity.getOrderedQuantity());
		order.setOrderId(orderEntity.getOrderId());
		order.setPurchasedDate(orderEntity.getPurchasedDate());
		order.setSoldDate(orderEntity.getSoldDate());
		order.setDeliveryDate(orderEntity.getDeliveryDate());
		order.setEmail(orderEntity.getEmail());
		order.setAddress(orderEntity.getAddress());
		order.setOrderStatus(orderEntity.getOrderStatus());
		order.setPaymentType(orderEntity.getPaymentType());

		return order;
	}

	public List<com.nagarro.orderservice.dto.Order> convertToListOrderDto(List<Order> ordersEntity) {

		List<com.nagarro.orderservice.dto.Order> orders = new ArrayList<com.nagarro.orderservice.dto.Order>();
		ordersEntity.forEach(r -> {
			orders.add(convertOrderEntityTODto(r));
		});

		return orders;
	}

}

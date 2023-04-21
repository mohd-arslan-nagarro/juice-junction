package com.nagarro.orderservice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import com.nagarro.orderservice.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.orderservice.converter.Converter;
import com.nagarro.orderservice.dao.ItemRepository;
import com.nagarro.orderservice.dao.OrderRepository;

import com.nagarro.orderservice.dto.Order;
import com.nagarro.orderservice.service.OrderService;


@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private Converter converter;

	@Override
	public Order getOrderByOrderId(long orderId) {
		final Optional<com.nagarro.orderservice.entity.Order> orderOpt = orderRepository.findById(orderId);
		if (orderOpt.isEmpty()) {
			throw new OrderNotFound("ORDER NOT FOUND BY ORDER ID - " + orderId);
		}

		final Order order = converter.convertOrderEntityTODto(orderOpt.get());
		return order;
	}

	@Override
	public List<Order> getOrderByEmailId(String emailId) {
		List<com.nagarro.orderservice.entity.Order>  ordersEntity=  orderRepository.findOrdersByEmailId(emailId);

		if (ordersEntity.size() == 0) {
			throw new OrderNotFound("NO ORDERS PRESENT");
		}

		final List<Order> orders = converter.convertToListOrderDto(ordersEntity);

		return orders;
	}




	@Override
	public List<Order> getOrders() {
		final List<com.nagarro.orderservice.entity.Order> ordersEntity = orderRepository.findAll();

		if (ordersEntity.size() == 0) {
			throw new OrderNotFound("NO ORDERS PRESENT");
		}

		final List<Order> orders = converter.convertToListOrderDto(ordersEntity);

		return orders;
	}


	@Override
	public List<Order> getOrdersPurchasedOrSoldInTimeLine(Date from, Date to, String filterBy) {

		List<com.nagarro.orderservice.entity.Order> ordersEntity;

		switch (filterBy.toLowerCase()) {

		case "purchased":
			ordersEntity = orderRepository.findOrdersPurchasedInTimeLine(from, to);
			break;

		case "sold":
			ordersEntity = orderRepository.findOrdersSoldInTimeLine(from, to);
			break;

		default:
			throw new RequstNotValidException("REQUST NOT VALID !!! filterBy SHOULD BE Either PURCHASED / SOLD ");

		}

		final List<Order> orders = converter.convertToListOrderDto(ordersEntity);

		return orders;
	}

	@Override
	@Transactional
	public Order placeOrder(Order order) {
		com.nagarro.orderservice.entity.Order placedOrderEntity = null;

		final Optional<com.nagarro.orderservice.entity.Order> orderOpt = orderRepository
				.findById(order.getOrderId());

		if (orderOpt.isPresent()) {
			throw new OrderAllreadyPresentException(
					"ORDER WITH SAME ORDER ID IS ALLREADY PRESENT !!! CANT PLACE ORDER");
		}

		final long productId = order.getItem().getProductId();
		final long orderedQuantiy = order.getOrderedQuantity();
		final boolean isAvailabeToSell = checkItemAvailabeToSell(productId, orderedQuantiy);

		if (isAvailabeToSell) {
			final long totalStock = itemRepository.getCurrentStockOfItem(productId);
			final long currentPriceOfItem = itemRepository.getCurrentPriceOfItem(productId);
			itemRepository.updateStock(totalStock - orderedQuantiy, productId);
			LocalDateTime deliveryDate=null;
			LocalDateTime tempDate=LocalDateTime.now();
			if (tempDate.getHour()<19) deliveryDate = tempDate.plusDays(1);
			else deliveryDate = tempDate.plusDays(2);
			System.out.println(deliveryDate+" accha to hm chlte h console me");
			order.setOrderedPrice(orderedQuantiy * currentPriceOfItem);
			order.setPurchasedDate(new Date());
			order.setSoldDate(new Date());
			order.setDeliveryDate(deliveryDate);
			order.setOrderStatus("Processing");

			final com.nagarro.orderservice.entity.Order orderEntity = converter.convertOrderDtoToEntity(order);
			System.out.println(orderEntity);
			placedOrderEntity = orderRepository.save(orderEntity);
		} else {
			throw new ItemQuantityNotAvailable(
					"OOPS !!ITEM WITH PRODUCT ID -" + productId + " HAVE NOT MUCH QUANTIY AVAILABLE");
		}

		final Order placedOrder = converter.convertOrderEntityTODto(placedOrderEntity);

		return placedOrder;
	}

	private boolean checkItemAvailabeToSell(long productId, long orderedQuantity) {

		final long availabeStock = itemRepository.getCurrentStockOfItem(productId);
		if (orderedQuantity <= availabeStock && availabeStock > 0) {
			return true;
		}

		return false;
	}


	@Override
	public List<Order> getPendingOrders(String status) {

			List<com.nagarro.orderservice.entity.Order>  ordersEntity=  orderRepository.findPendingOrders(status);

			if (ordersEntity.size() == 0) {
				throw new OrderNotFound("NO ORDERS PRESENT");
			}

			final List<Order> orders = converter.convertToListOrderDto(ordersEntity);

			return orders;
	}
	@Override
	@Transactional
	public Boolean updateOrderStatus(long orderId,Order order) {
		String message = "Not Updated Item Status With Item Id " + orderId;


		final Optional<com.nagarro.orderservice.entity.Order> orderOpt=orderRepository.findById(order.getOrderId());
		if (orderOpt == null){
			throw new ItemNotFoundException("NO ITEMS PRESENT WITH THIS ID - SO CAN'T UPDATE ITEM");
		} else {
			orderRepository.updateOrderStatus(order.getOrderStatus(), orderId);

		}
		return true;

	}


}

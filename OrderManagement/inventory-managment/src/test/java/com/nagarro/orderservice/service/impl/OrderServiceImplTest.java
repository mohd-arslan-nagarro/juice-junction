//package com.nagarro.orderservice.service.impl;
//
//import static com.nagarro.orderservice.provider.FakeDataProvider.*;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.nagarro.orderservice.converter.Converter;
//import com.nagarro.orderservice.dao.ItemRepository;
//import com.nagarro.orderservice.dao.OrderRepository;
//import com.nagarro.orderservice.entity.Item;
//import com.nagarro.orderservice.entity.Order;
//
//@SpringBootTest
//@DisplayName("ORDER SERVICE IMPL TESTING")
//@TestInstance(Lifecycle.PER_CLASS)
//class OrderServiceImplTest {
//
//	@MockBean
//	private OrderRepository orderRepository;
//
//	@MockBean
//	private ItemRepository itemRepository;
//
//	@Autowired
//	private OrderServiceImpl orderServiceImpl;
//
//	@Autowired
//	private Converter converter;
//
//	private List<Order> orders;
//
//	private List<Item> items;
//
//	@BeforeAll
//	void setUp() throws Exception {
//		orders = getPreDefinedDataForOrder();
//		items = getPerDefinedItemsForTest();
//	}
//
//	@Test
//	@DisplayName("GET ORDER BY ID")
//	void testGetOrderByOrderId() {
//		Order order = orders.get(0);
//		long orderId = order.getOrderId();
//
//		when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
//
//		com.nagarro.orderservice.dto.Order actual = orderServiceImpl.getOrderByOrderId(orderId);
//		verify(orderRepository, times(1)).findById(anyLong());
//
//		assertEquals(actual.getOrderId(), converter.convertOrderDtoToEntity(actual).getOrderId());
//
//	}
//
//	@Test
//	@DisplayName("GET ORDERS")
//	void testGetOrders() {
//
//		when(orderRepository.findAll()).thenReturn(orders);
//
//		List<com.nagarro.orderservice.dto.Order> actual = orderServiceImpl.getOrders();
//
//		verify(orderRepository, times(1)).findAll();
//		assertEquals(orders.size(), actual.size());
//
//	}
//
//	@Test
//	@DisplayName("GET ORDER PLACED IN TIME LINE BY FILTER")
//	void testGetOrdersPurchasedOrSoldInTimeLine() throws Exception {
//		Date from = new SimpleDateFormat("yyyy-MM-dd").parse("1960-01-01");
//		Date to = new SimpleDateFormat("yyyy-MM-dd").parse("1993-01-02");
//
//		String filterBy = "sold";
//
//		List<Order> filterByPurchased = orders.stream()
//				.filter(t -> (t.getPurchasedDate().after(from) && t.getPurchasedDate().before(to))
//						|| (t.getPurchasedDate().equals(from)) || (t.getPurchasedDate().equals(to)))
//				.collect(Collectors.toList());
//
//		List<Order> filterBySold = orders.stream()
//				.filter(t -> (t.getSoldDate().after(from) && t.getSoldDate().before(to))
//						|| (t.getSoldDate().equals(from)) || (t.getSoldDate().equals(to)))
//				.collect(Collectors.toList());
//
//		when(orderRepository.findOrdersPurchasedInTimeLine(from, to)).thenReturn(filterByPurchased);
//
//		when(orderRepository.findOrdersSoldInTimeLine(from, to)).thenReturn(filterBySold);
//
//		List<com.nagarro.orderservice.dto.Order> actual = orderServiceImpl
//				.getOrdersPurchasedOrSoldInTimeLine(from, to, filterBy);
//
//		if (filterBy.equals("purchased")) {
//			assertEquals(actual.size(), filterByPurchased.size());
//		}
//		if (filterBy.equals("sold")) {
//			assertEquals(actual.size(), filterBySold.size());
//		}
//
//	}
//
//	@Test
//	@DisplayName("PLACE ORDER")
//	void testPlaceOrder() {
//
//		when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
//		when(itemRepository.getCurrentStockOfItem(anyString())).thenReturn(items.get(0).getQuantity());
//		when(itemRepository.getCurrentPriceOfItem(anyString())).thenReturn(items.get(0).getProductPrice());
//
//		Order expected = orders.get(0);
//
//		when(orderRepository.save(any(Order.class))).thenReturn(expected);
//
//		com.nagarro.orderservice.dto.Order actual = orderServiceImpl
//				.placeOrder(converter.convertOrderEntityTODto(expected));
//
//		verify(itemRepository, times(1)).updateStock(anyLong(), anyString());
//
//		assertAll("MATCH ORDER DETAILS",
//				()->assertEquals(expected.getOrderId(),actual.getOrderId()),
//				()->assertEquals(expected.getOrderedPrice(),actual.getOrderedPrice()),
//				()->assertEquals(expected.getOrderedQuantity(),actual.getOrderedQuantity()),
//				()->assertEquals(expected.getItem().getProductId(),actual.getItem().getProductId()));
//
//	}
//
//}

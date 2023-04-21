//package com.nagarro.orderservice.dao;
//
//import static com.nagarro.orderservice.provider.FakeDataProvider.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.nagarro.orderservice.entity.Order;;
//
//@SpringBootTest
//@TestInstance(Lifecycle.PER_CLASS)
//@DisplayName("ORDER REPOSITORY TESTING")
//class OrderRepositoryTest {
//
//	@Autowired
//	private ItemRepository itemRepository;
//
//	@Autowired
//	private OrderRepository orderRepository;
//
//	@BeforeAll
//	void setUp() throws ParseException {
//		itemRepository.save(getPerDefinedItemsForTest().get(0));
//		orderRepository.saveAll(getPreDefinedDataForOrder());
//	}
//
//	@Test
//	@DisplayName("PURCHASED IN TIMELINE")
//	void testFindPurchsedItemsInTimeLine() throws ParseException {
//
//		Date from = new SimpleDateFormat("yyyy-MM-dd").parse("1960-01-01");
//		Date to = new SimpleDateFormat("yyyy-MM-dd").parse("1993-01-02");
//
//		List<Order> expected = getPreDefinedDataForOrder().stream()
//				.filter(t -> (t.getPurchasedDate().after(from) && t.getPurchasedDate().before(to))
//						|| (t.getPurchasedDate().equals(from)) || (t.getPurchasedDate().equals(to)))
//				.collect(Collectors.toList());
//
//		List<Order> actual = orderRepository.findOrdersPurchasedInTimeLine(from, to);
//
//		assertEquals(expected.size(), actual.size());
//
//	}
//
//	@Test
//	@DisplayName("SOLD IN TIMELINE")
//	void testFindSoldItemsInTimeLine() throws ParseException {
//
//		Date from = new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01");
//		Date to = new SimpleDateFormat("yyyy-MM-dd").parse("1993-01-02");
//
//		List<Order> expected = getPreDefinedDataForOrder().stream()
//				.filter(t -> (t.getSoldDate().after(from) && t.getSoldDate().before(to))
//						|| (t.getSoldDate().equals(from)) || (t.getSoldDate().equals(to)))
//				.collect(Collectors.toList());
//
//		List<Order> actual = orderRepository.findOrdersSoldInTimeLine(from, to);
//
//		assertEquals(expected.size(), actual.size());
//	}
//
//	@Test
//	@DisplayName("ORDERS BY CUSTOMER ID")
//	void testFindOrdersByCustomerId() throws ParseException {
//		List<Order> expected = getPreDefinedDataForOrder();
//		List<Order> actual = orderRepository.findOrdersByCustomerId(expected.get(0).getCustomer().getCustomerId());
//
//		assertEquals(2, actual.size());
//	}
//
//	@AfterAll
//	void tearDown() throws ParseException {
//		itemRepository.deleteById(getPerDefinedItemsForTest().get(0).getProductId());
//
//	}
//
//}

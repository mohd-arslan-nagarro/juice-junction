//package com.nagarro.orderservice.controller;
//
//import static com.nagarro.orderservice.provider.FakeDataProvider.getPreDefinedDataForOrder;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
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
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nagarro.orderservice.converter.Converter;
//import com.nagarro.orderservice.dto.Order;
//import com.nagarro.orderservice.service.OrderService;
//
//@SpringBootTest
//@DisplayName("ORDER CONTROLLER TESTING")
//@TestInstance(Lifecycle.PER_CLASS)
//class OrderControllerTest {
//
//	private MockMvc mockMvc;
//
//	@Autowired
//	private WebApplicationContext applicationContext;
//
//	@MockBean
//	private OrderService orderService;
//
//	@Autowired
//	private Converter converter;
//
//	@Autowired
//	private ObjectMapper om;
//
//	private List<Order> orders;
//
//	@BeforeAll
//	private void setUp() throws ParseException {
//		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
//
//		orders = converter.convertToListOrderDto(getPreDefinedDataForOrder());
//
//	}
//
//	@Test
//	void testHandleGetOrders() throws Exception {
//		String uri = "/orders";
//
//		when(orderService.getOrders()).thenReturn(orders);
//
//		MvcResult result = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();
//		String resultContent = result.getResponse().getContentAsString();
//		Order[] response = om.readValue(resultContent, Order[].class);
//		List<Order> actual = Arrays.asList(response);
//
//		assertEquals(orders.size(), actual.size());
//	}
//
//	@Test
//	void testGetOrder() throws Exception {
//
//		long orderId = orders.get(0).getOrderId();
//		String uri = "/orders/" + orderId;
//
//		when(orderService.getOrderByOrderId(orderId)).thenReturn(orders.get(0));
//
//		MvcResult result = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		Order response = om.readValue(resultContent, Order.class);
//
//		assertEquals(response.getItem().getProductId(), orders.get(0).getItem().getProductId());
//
//	}
//
//	@Test
//	void testGetFilteredOrdersInTimeLine() throws Exception {
//		String from = "01/01/1960";
//		String to = "02/01/1993";
//		String filterBy = "purchased";
//
//		String uri = "/orders/timeline?fromDate=" + from + "&toDate=" + to + "&filterBy=" + filterBy;
//
//		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(from);
//		Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(to);
//
//		List<com.nagarro.orderservice.entity.Order> trascations = getPreDefinedDataForOrder().stream()
//				.filter(t -> (t.getPurchasedDate().after(fromDate) && t.getPurchasedDate().before(toDate))
//						|| (t.getPurchasedDate().equals(fromDate)) || (t.getPurchasedDate().equals(toDate)))
//				.collect(Collectors.toList());
//
//		when(orderService.getOrdersPurchasedOrSoldInTimeLine(fromDate, toDate, filterBy))
//		.thenReturn(converter.convertToListOrderDto(trascations));
//
//		MvcResult result = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//		Order[] response = om.readValue(resultContent, Order[].class);
//		List<Order> actual=Arrays.asList(response);
//		assertEquals(actual.size(), trascations.size());
//		verify(orderService, times(1)).getOrdersPurchasedOrSoldInTimeLine(fromDate, toDate, filterBy);
//	}
//
//	@Test
//	void testHandlePlaceOrder() throws Exception {
//
//		String uri="/orders";
//
//		String jsonRequst = om.writeValueAsString(orders.get(0));
//
//		when(orderService.placeOrder(any(Order.class)))
//		.thenReturn(orders.get(0));
//
//		MvcResult result = mockMvc
//				.perform(post(uri).content(jsonRequst).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isCreated()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		Order response = om.readValue(resultContent,Order.class);
//
//		assertEquals(response.getOrderId(),orders.get(0).getOrderId());
//
//
//
//	}
//
//}

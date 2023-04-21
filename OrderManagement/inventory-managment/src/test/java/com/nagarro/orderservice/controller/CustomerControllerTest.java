//package com.nagarro.orderservice.controller;
//
//import static com.nagarro.orderservice.provider.FakeDataProvider.getPreDefinedDataForOrder;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nagarro.orderservice.converter.Converter;
//import com.nagarro.orderservice.dto.Order;
//import com.nagarro.orderservice.provider.FakeDataProvider;
//import com.nagarro.orderservice.service.CustomerService;
//
//@SpringBootTest
//@DisplayName("CUSTOMER CONTROLLER TESTING")
//@TestInstance(Lifecycle.PER_CLASS)
//class CustomerControllerTest {
//
//	private MockMvc mockMvc;
//
//	@Autowired
//	private WebApplicationContext applicationContext;
//
//	@MockBean
//	private CustomerService customerService;
//
//	@Autowired
//	private Converter converter;
//
//	@Autowired
//	private ObjectMapper om;
//
//	@Autowired
//	private FakeDataProvider dataProvider;
//
//	private com.nagarro.orderservice.dto.Customer customer;
//
//	private List<com.nagarro.orderservice.dto.Order> orders;
//
//	@BeforeAll
//	private void setUp() throws ParseException {
//		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
//
//		orders = converter.convertToListOrderDto(getPreDefinedDataForOrder());
//		customer = dataProvider.getPreDefinedDataForCustomer();
//
//	}
//
//	@Test
//	@DisplayName("GET CUSTOMER BY ID")
//	void testGetCustomer() throws Exception {
//		String customerId = customer.getCustomerId();
//		String uri = "/customers/" + customerId;
//
//		List<com.nagarro.orderservice.dto.Order> orders = new ArrayList<com.nagarro.orderservice.dto.Order>();
//		customer.setCustomerOrders(orders);
//
//		when(customerService.getCustomer(anyLong())).thenReturn(customer);
//
//		MvcResult result = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		com.nagarro.orderservice.dto.Customer response = om.readValue(resultContent,
//				com.nagarro.orderservice.dto.Customer.class);
//
//		assertEquals(response.getCustomerId(), customer.getCustomerId());
//
//	}
//
//	@Test
//	@DisplayName("GET ORDER HISTORY OF CUSTOMER")
//	void testGetOrderHistoryOfCustomer() throws Exception {
//		String customerId = customer.getCustomerId();
//		String uri = "/customers/" + customerId + "/orderhistory";
//
//		when(customerService.getCustomerOrderHistory(anyLong())).thenReturn(orders);
//
//		MvcResult result = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		Order[] response=om.readValue(resultContent,Order[].class);
//
//		List<Order> actual=List.of(response);
//
//		assertEquals(actual.size(),orders.size());
//
//	}
//
//}

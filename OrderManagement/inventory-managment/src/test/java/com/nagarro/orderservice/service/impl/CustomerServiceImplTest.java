//package com.nagarro.orderservice.service.impl;
//
//import static com.nagarro.orderservice.provider.FakeDataProvider.getPreDefinedDataForOrder;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
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
//import com.nagarro.orderservice.dao.CustomerRepository;
//import com.nagarro.orderservice.dao.OrderRepository;
//import com.nagarro.orderservice.dto.Customer;
//import com.nagarro.orderservice.dto.Order;
//import com.nagarro.orderservice.provider.FakeDataProvider;
//
//@SpringBootTest
//@DisplayName("CUSTOMER SERVICE IMPL TESTING")
//@TestInstance(Lifecycle.PER_CLASS)
//class CustomerServiceImplTest {
//
//	@MockBean
//	private CustomerRepository customerRepository;
//
//	@MockBean
//	private OrderRepository orderRepository;
//
//	@Autowired
//	private Converter converter;
//
//	@Autowired
//	private CustomerServiceImpl customerServiceImpl;
//
//	@Autowired
//	FakeDataProvider dataProvider;
//
//	private Customer customer;
//
//	@BeforeAll
//	void setUp() throws Exception {
//		customer = dataProvider.getPreDefinedDataForCustomer();
//		List<Order> orders = new ArrayList<Order>();
//		customer.setCustomerOrders(orders);
//	}
//
//	@Test
//	@DisplayName("GET CUSTOMER")
//	void testGetCustomer() {
//
//		when(customerRepository.findById(anyLong()))
//				.thenReturn(Optional.of(converter.convertCustomerDtoToEntty(customer)));
//
//		Customer actual = customerServiceImpl.getCustomer(customer.getCustomerId());
//
//		assertEquals(actual, customer);
//
//	}
//
//	@Test
//	@DisplayName("ORDER HISTORY OF CUSTOMER")
//	void testGetCustomerOrderHistory() throws Exception {
//
//		when(orderRepository.findOrdersByCustomerId(anyLong())).thenReturn(getPreDefinedDataForOrder());
//
//		List<Order> actual = customerServiceImpl.getCustomerOrderHistory(customer.getCustomerId());
//
//		verify(orderRepository, times(1)).findOrdersByCustomerId(anyLong());
//
//		assertEquals(actual, converter.convertToListOrderDto(getPreDefinedDataForOrder()));
//	}
//
//}

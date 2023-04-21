//package com.nagarro.orderservice.dao;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.text.ParseException;
//import java.util.Optional;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.nagarro.orderservice.entity.Customer;
//import com.nagarro.orderservice.provider.FakeDataProvider;
//
//@SpringBootTest
//@TestInstance(Lifecycle.PER_CLASS)
//@DisplayName("CUSTOMER REPOSITORY TESTING")
//class CustomerRepositoryTest {
//
//	@Autowired
//	private FakeDataProvider dataProvider;
//
//	@Autowired
//	private CustomerRepository customerRepository;
//
//	@Test
//	@DisplayName("GET CUSTOMER BY ID")
//	void test() throws ParseException {
//		Optional<Customer> customer=customerRepository.findById(123L);
//
//		assertEquals(customer.get().getName(),dataProvider.getPreDefinedDataForCustomer().getName());
//
//
//	}
//
//
//
//}

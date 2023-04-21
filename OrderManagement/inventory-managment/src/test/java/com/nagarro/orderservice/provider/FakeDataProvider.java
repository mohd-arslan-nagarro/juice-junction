//package com.nagarro.orderservice.provider;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.nagarro.orderservice.converter.Converter;
//import com.nagarro.orderservice.entity.Customer;
//import com.nagarro.orderservice.entity.Description;
//import com.nagarro.orderservice.entity.Item;
//import com.nagarro.orderservice.entity.Order;
//
//@Component
//public class FakeDataProvider {
//
//	@Autowired
//	private Converter converter;
//
//	public static List<Item> getPerDefinedItemsForTest() throws ParseException {
//		List<Item> items = new ArrayList<Item>();
//
//		Item expected = new Item();
//		expected.setProductId("CX102B");
//		expected.setProductName("CASIO WATCH G-SHOCK EDITION");
//		expected.setProductDescription(new Description(786, "black", "hello testing"));
//		expected.setProductType("WATCH");
//		expected.setQuantity(23);
//		expected.setProductPrice(500);
//		expected.setIsActive(true);
//
//		Item expected2 = new Item();
//		expected2.setProductId("MNOP");
//		expected2.setProductName("MONOPOLY EDITION");
//		expected2.setProductDescription(new Description(902, "black", "hello testing 2"));
//		expected2.setProductType("WATCH");
//		expected2.setQuantity(12);
//		expected2.setProductPrice(100);
//		expected.setIsActive(true);
//
//		items.add(expected);
//		items.add(expected2);
//
//		return items;
//	}
//
//	public static List<Order> getPreDefinedDataForOrder() throws ParseException {
//
//		List<Order> transcations = new ArrayList<Order>();
//
//		Order t = new Order();
//		t.setOrderId(4215);
//		t.setItem(new Item("CX102B"));
//		t.setCustomer(new Customer(123));
//		t.setPurchasedDate(new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-22"));
//		t.setSoldDate(new SimpleDateFormat("yyyy-MM-dd").parse("1981-02-23"));
//		t.setOrderedQuantity(3);
//		t.setOrderedPrice(1500);
//
//		Order t2 = new Order();
//		t2.setOrderId(4216);
//		t2.setItem(new Item("CX102B"));
//		t2.setCustomer(new Customer(123));
//		t2.setPurchasedDate(new SimpleDateFormat("yyyy-MM-dd").parse("1980-01-22"));
//		t2.setSoldDate(new SimpleDateFormat("yyyy-MM-dd").parse("1981-02-23"));
//		t2.setOrderedQuantity(3);
//		t2.setOrderedPrice(1500);
//
//		transcations.add(t);
//		transcations.add(t2);
//
//		return transcations;
//	}
//
//	public  com.nagarro.orderservice.dto.Customer getPreDefinedDataForCustomer() throws ParseException {
//
//		return new com.nagarro.orderservice.dto.Customer(123, "danish",
//				converter.convertToListOrderDto(getPreDefinedDataForOrder()));
//	}
//
//}

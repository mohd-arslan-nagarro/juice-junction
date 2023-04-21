//package com.nagarro.orderservice.service.impl;
//
//import static com.nagarro.orderservice.provider.FakeDataProvider.getPerDefinedItemsForTest;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.text.ParseException;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.nagarro.orderservice.converter.Converter;
//import com.nagarro.orderservice.dao.ItemRepository;
//import com.nagarro.orderservice.entity.Item;;
//
//@SpringBootTest
//@DisplayName("ITEM SERVICE IMPL TESTING")
//class ItemServiceImplTest {
//
//	@Autowired
//	private ItemServiceImpl itemServiceImpl;
//
//	@Autowired
//	private Converter converter;
//
//	@MockBean
//	private ItemRepository itemRepository;
//
//	@Test
//	@DisplayName("ITEM BY ID")
//	void testGetItemById() throws ParseException {
//
//		String id = "CX102B";
//
//		Item item = getPerDefinedItemsForTest().get(0);
//
//		when(itemRepository.findById(id)).thenReturn(Optional.of(item));
//
//		com.nagarro.orderservice.dto.Item actual = itemServiceImpl.getItemById(id);
//
//		verify(itemRepository, times(1)).findById(anyString());
//
//		assertEquals(converter.convertItemEntityToDto(item), actual);
//
//	}
//
//	@Test
//	@DisplayName("GET ITEMS")
//	void testGetItems() throws ParseException {
//
//		List<Item> expected = getPerDefinedItemsForTest();
//
//		String filterBy = "all";
//
//		when(itemRepository.findAll()).thenReturn(expected);
//
//		when(itemRepository.findItemsAvailableInStocks())
//				.thenReturn(expected.stream().filter(i -> i.getQuantity() > 0).collect(Collectors.toList()));
//
//		when(itemRepository.findSoldOutItems())
//				.thenReturn(expected.stream().filter(i -> i.getQuantity() == 0).collect(Collectors.toList()));
//
//		int actual = itemServiceImpl.getItems(filterBy).size();
//
//		verify(itemRepository, times(1)).findAll();
//
//		assertEquals(expected.size(), actual);
//
//	}
//
//	@Test
//	@DisplayName("SAVE ITEM")
//	void testSaveItem() throws ParseException {
//
//		Item itemEntity = getPerDefinedItemsForTest().get(0);
//
//		com.nagarro.orderservice.dto.Item expected = converter.convertItemEntityToDto(itemEntity);
//
//		when(itemRepository.findById(expected.getProductId())).thenReturn(Optional.empty());
//		when(itemRepository.save(any(com.nagarro.orderservice.entity.Item.class))).thenReturn(itemEntity);
//
//		com.nagarro.orderservice.dto.Item actual = itemServiceImpl.saveItem(expected);
//
//		verify(itemRepository, times(1)).findById(anyString());
//		verify(itemRepository, times(1)).save(any(Item.class));
//
//		assertEquals(expected, actual);
//
//	}
//
//	@Test
//	@DisplayName("UPDATE ITEM")
//	void testUpdateItem() throws ParseException {
//
//		Item itemEntity = getPerDefinedItemsForTest().get(0);
//
//		com.nagarro.orderservice.dto.Item expected = converter.convertItemEntityToDto(itemEntity);
//
//		when(itemRepository.findById(expected.getProductId())).thenReturn(Optional.of(itemEntity));
//		when(itemRepository.save(any(Item.class))).thenReturn(itemEntity);
//		com.nagarro.orderservice.dto.Item actual = itemServiceImpl.updateItem(expected, expected.getProductId());
//
//		verify(itemRepository, times(1)).findById(anyString());
//		verify(itemRepository, times(1)).save(any(Item.class));
//
//		assertEquals(actual, expected);
//
//	}
//
//	@Test
//	@DisplayName("UPDATE STOCK OF ITEM")
//	void testUpdateStock() throws ParseException {
//
//		Item product = getPerDefinedItemsForTest().get(0);
//		long currentQuantity = product.getQuantity();
//		String productId = product.getProductId();
//
//		long updateStockBy = 10;
//
//		com.nagarro.orderservice.dto.Item expected = converter.convertItemEntityToDto(product);
//		expected.setQuantity(updateStockBy + expected.getQuantity());
//
//		when(itemRepository.findById(productId)).thenReturn(Optional.of(product))
//				.thenReturn(Optional.of(converter.convertItemDtoToEntity(expected)));
//		when(itemRepository.getCurrentStockOfItem(productId)).thenReturn(currentQuantity);
//
//		com.nagarro.orderservice.dto.Item actual = itemServiceImpl.updateStock(productId,
//				new com.nagarro.orderservice.dto.Item(updateStockBy));
//
//		verify(itemRepository, times(1)).updateStock(anyLong(), anyString());
//
//		assertEquals(actual.getQuantity(), expected.getQuantity());
//
//	}
//
//	@Test
//	@DisplayName("DELETE ITEM")
//	void testDeleteItem() throws ParseException {
//		String itemId = getPerDefinedItemsForTest().get(0).getProductId();
//
//		String expected = "Deleted Product with id " + itemId;
//
//		when(itemRepository.findById(itemId)).thenReturn(Optional.of(getPerDefinedItemsForTest().get(0)));
//
//		String actual = this.itemServiceImpl.deleteItem(itemId);
//
//		verify(itemRepository, times(1)).findById(anyString());
//		verify(itemRepository, times(1)).deleteById(anyString());
//
//		assertEquals(expected, actual);
//
//	}
//
//}

//package com.nagarro.orderservice.dao;
//
//import static com.nagarro.orderservice.provider.FakeDataProvider.getPerDefinedItemsForTest;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.text.ParseException;
//import java.util.List;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.nagarro.orderservice.entity.Item;
//
//@SpringBootTest
//@TestInstance(Lifecycle.PER_CLASS)
//@DisplayName("ITEM REPOSITORY TESTING")
//class ItemRepositoryTest {
//
//	@Autowired
//	private ItemRepository itemRepository;
//
//	@Test
//	@DisplayName("ITEMS IN STOCK")
//	void testFindItemsAvailableInStocks() {
//
//		int expectedSize = 3;
//
//		List<Item> actual = itemRepository.findItemsAvailableInStocks();
//
//		assertEquals(expectedSize, actual.size());
//
//	}
//
//	@Test
//	@DisplayName("SOLD OUT ITEMS")
//	void testFindSoldOutItems() {
//
//		int expectedSize = 2;
//
//		List<Item> actual = itemRepository.findSoldOutItems();
//
//		assertEquals(expectedSize, actual.size());
//	}
//
//	@SuppressWarnings("deprecation")
//	@Test
//	@DisplayName("UPDATE STOCK")
//	@Transactional
//	void testUpdateStockOnParticularItem() throws ParseException {
//
//		Item item = getPerDefinedItemsForTest().get(0);
//
//		itemRepository.save(item);
//
//		itemRepository.updateStock(10, item.getProductId());
//
//		Item actual = itemRepository.getOne(item.getProductId());
//
//		assertEquals(actual.getQuantity(),10);
//
//		itemRepository.deleteById(actual.getProductId());
//
//	}
//
//	@Test
//	@DisplayName("ITEM BY ID")
//	@Transactional
//	void testFindItemByID() throws ParseException {
//
//		Item expected = getPerDefinedItemsForTest().get(0);
//
//		itemRepository.save(expected);
//
//		Item actual = itemRepository.findById("CX102B").get();
//
//		assertAll("PROPERTIES OF ITEMS", () -> assertEquals(expected.getProductId(), actual.getProductId()),
//				() -> assertEquals(expected.getProductName(), actual.getProductName()),
//				() -> assertEquals(expected.getProductType(), actual.getProductType()));
//
//		itemRepository.deleteById(actual.getProductId());
//	}
//
//	@Test
//	@DisplayName("SAVE OR UPDATE ITEM")
//	void testSaveORUpdateItem() throws ParseException {
//
//		Item expected = getPerDefinedItemsForTest().get(1);
//
//		Item actual = itemRepository.save(expected);
//
//		assertEquals(expected.getProductId(), actual.getProductId());
//
//		this.itemRepository.deleteById(expected.getProductId());
//
//	}
//
//	@Test
//	@DisplayName("CURRENT STOCK OF ITEM")
//	@Transactional
//	void testCurrentStockOfItem() throws ParseException {
//
//		Item expected = getPerDefinedItemsForTest().get(0);
//
//		itemRepository.save(expected);
//
//		long actual = itemRepository.getCurrentStockOfItem("CX102B");
//
//		assertEquals(actual,expected.getQuantity());
//
//		itemRepository.deleteById(expected.getProductId());
//	}
//
//	@Test
//	@DisplayName("CURRENT PRICE OF ITEM")
//	@Transactional
//	void testCurrentPriceOfItem() throws ParseException {
//
//		Item expected = getPerDefinedItemsForTest().get(0);
//
//		itemRepository.save(expected);
//
//		long actual = itemRepository.getCurrentPriceOfItem("CX102B");
//
//		assertEquals(actual,expected.getProductPrice());
//
//		itemRepository.deleteById(expected.getProductId());
//	}
//
//
//
//}

//package com.nagarro.orderservice.controller;
//
//import static com.nagarro.orderservice.provider.FakeDataProvider.getPerDefinedItemsForTest;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.text.ParseException;
//import java.util.Arrays;
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
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nagarro.orderservice.converter.Converter;
//import com.nagarro.orderservice.dto.Item;
//import com.nagarro.orderservice.service.ItemService;
//
//@SpringBootTest
//@DisplayName("ITEM CONTROLLER TESTING")
//@TestInstance(Lifecycle.PER_CLASS)
//class ItemControllerTest {
//
//	private MockMvc mockMvc;
//
//	@Autowired
//	private WebApplicationContext applicationContext;
//
//	@MockBean
//	private ItemService itemService;
//
//	@Autowired
//	private Converter converter;
//
//	@Autowired
//	private ObjectMapper om;
//
//	private List<Item> items;
//
//	@BeforeAll
//	private void setUp() throws ParseException {
//		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
//
//		items = converter.convertListToItemDtoList(getPerDefinedItemsForTest());
//
//	}
//
//	@Test
//	@DisplayName("GET ITEM BY ID")
//	void testGetParticularItem() throws Exception {
//
//		String productId = "CX102B";
//
//		String uri = "/items/" + productId;
//
//		when(itemService.getItemById(anyString())).thenReturn(items.get(0));
//
//		MvcResult result = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		Item response = om.readValue(resultContent, Item.class);
//
//		assertEquals(response.getProductId(), items.get(0).getProductId());
//	}
//
//	@Test
//	@DisplayName("GET ALL ITEMS")
//	void testGetItems() throws Exception {
//		String uri = "/items";
//
//		String filterBy = "all";
//
//		when(itemService.getItems(filterBy)).thenReturn(items);
//
//		MvcResult result = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		Item[] response = om.readValue(resultContent, Item[].class);
//
//		List<Item> actual = Arrays.asList(response);
//		assertEquals(items.size(), actual.size());
//
//	}
//
//	@Test
//	@DisplayName("SAVE ITEM")
//	void testSaveItemInInventory() throws Exception {
//
//		String jsonRequst = om.writeValueAsString(items.get(0));
//
//		when(itemService.saveItem(items.get(0))).thenReturn(items.get(0));
//
//		MvcResult result = mockMvc
//				.perform(post("/items").content(jsonRequst).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isCreated()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		Item response = om.readValue(resultContent, Item.class);
//
//		assertEquals(response.getProductId(), items.get(0).getProductId());
//	}
//
//	@Test
//	@DisplayName("UPDATE ITEM")
//	void testUpdateParticularItemInInventory() throws Exception {
//
//		String productId = "CX102B";
//		String uri = "/items?productId=" + productId;
//		String jsonRequst = om.writeValueAsString(items.get(0));
//
//		when(itemService.updateItem(any(Item.class), anyString())).thenReturn(items.get(0));
//
//		MvcResult result = mockMvc.perform(put(uri).content(jsonRequst).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		Item response = om.readValue(resultContent, Item.class);
//
//		assertEquals(response, items.get(0));
//	}
//
//	@Test
//	@DisplayName("UPDATE STOCK ")
//	void testUpdateStockOfItem() throws Exception {
//		String productId = "CX102B";
//
//		String uri = "/items?productId=" + productId;
//
//		Item expected = items.get(0);
//		expected.setQuantity(10);
//
//		when(itemService.updateStock(anyString(), any(Item.class))).thenReturn(expected);
//
//		String flagUpdate = "{\"inStock\":true}";
//
//		MvcResult result = mockMvc.perform(patch(uri).content(flagUpdate).contentType(MediaType.APPLICATION_JSON_VALUE))
//				.andExpect(status().isOk()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		Item response = om.readValue(resultContent, Item.class);
//
//		assertEquals(response.getProductId(), expected.getProductId());
//
//	}
//
//	@Test
//	@DisplayName("DELETE ITEM")
//	void testDeleteParticularItemFromInventory() throws Exception {
//
//		String productId = "CX102B";
//		String uri = "/items/" + productId;
//
//		String expected = "DELETED ITEM WITH ID - " + productId;
//
//		when(itemService.deleteItem(anyString())).thenReturn(expected);
//
//		MvcResult result = mockMvc.perform(delete(uri)).andExpect(status().isOk()).andReturn();
//
//		String resultContent = result.getResponse().getContentAsString();
//
//		verify(itemService, times(1)).deleteItem(anyString());
//
//		assertEquals(resultContent, expected);
//
//	}
//
//}

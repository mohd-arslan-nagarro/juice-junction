package com.nagarro.orderservice.service;

import java.util.List;

import com.nagarro.orderservice.dto.Item;

public interface ItemService {
	
	Item getItemById(long id);
	
	List<Item> getItems(String filterBy);
	
	Item saveItem(Item product);
	
	Item updateItem(Item product ,long productId);
	
	Item updateStock(long productId,Item product);
	
	String updateItemStatus(long productId,Item product);
	
	String deleteItem(long productId);
	
	

}

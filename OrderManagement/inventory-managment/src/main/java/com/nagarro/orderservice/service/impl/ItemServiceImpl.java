package com.nagarro.orderservice.service.impl;

import java.util.List;
import java.util.Optional;

import com.nagarro.orderservice.dao.DescriptionRepository;
import com.nagarro.orderservice.dto.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.orderservice.converter.Converter;
import com.nagarro.orderservice.dao.ItemRepository;
import com.nagarro.orderservice.dto.Item;
import com.nagarro.orderservice.exception.ItemAllreadyPresentException;
import com.nagarro.orderservice.exception.ItemNotFoundException;
import com.nagarro.orderservice.exception.RequstNotValidException;
import com.nagarro.orderservice.service.ItemService;

@Service("ItemServiceImpl")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private DescriptionRepository descriptionRepository;

	@Autowired
	private Converter converter;

	@Override
	public Item getItemById(long id) {

		final Optional<com.nagarro.orderservice.entity.Item> itemEntity = itemRepository.findById(id);

		if (itemEntity.isEmpty()) {
			throw new ItemNotFoundException("NO ITEM WITH ID - " + id + " FOUND !! NOT FOUND");
		}

		final Item item = converter.convertItemEntityToDto(itemEntity.get());

		return item;
	}

	@Override
	public List<Item> getItems(String filterBy) {

		List<com.nagarro.orderservice.entity.Item> itemsEntity;

		switch (filterBy.toLowerCase()) {

		case "instock":
			itemsEntity = itemRepository.findItemsAvailableInStocks();
			break;

		case "soldout":
			itemsEntity = itemRepository.findSoldOutItems();
			break;
			
		case "notforsale":
			itemsEntity=itemRepository.findByIsActiveFalse();
			break;

		case "all":
			itemsEntity = itemRepository.findAll();
			break;

		default:
			throw new RequstNotValidException(
					"REQUST NOT VALID !!! filterBy SHOULD BE Either INSTOCK / SOLDOUT / ALL ");

		}

		if (itemsEntity.size() == 0) {
			throw new ItemNotFoundException("NO ITEMS PRESENT");
		}

		final List<Item> items = converter.convertListToItemDtoList(itemsEntity);

		return items;
	}

	@Override
	@Transactional
	public Item saveItem(Item product) {

		final Optional<com.nagarro.orderservice.entity.Item> itemOpt = itemRepository
				.findById(product.getProductId());

		if (itemOpt.isPresent()) {
			throw new ItemAllreadyPresentException("SAME ITEM ALLREADY PRESENT CANT SAVE THIS ONE");
		}
//		Description des=product.getProductDescription();

//		com.nagarro.orderservice.entity.Description descEntity=converter.convertDescriptionDtoToEntity(des);
//		com.nagarro.orderservice.entity.Description descNewEntity =descriptionRepository.save(descEntity);
		final com.nagarro.orderservice.entity.Item itemEntity = converter.convertItemDtoToEntity(product);

//		itemEntity.setProductDescription(descNewEntity);

		final com.nagarro.orderservice.entity.Item savedItem = itemRepository.save(itemEntity);

		final Item item = converter.convertItemEntityToDto(savedItem);

		return item;
	}

	@Override
	public Item updateItem(Item product, long productId) {

		final Optional<com.nagarro.orderservice.entity.Item> itemOpt = itemRepository.findById(productId);

		if (itemOpt.isEmpty()) {
			throw new ItemNotFoundException("NO ITEMS PRESENT WITH THIS ID -" + productId + "SO CAN'T UPDATE ITEM");
		}

		com.nagarro.orderservice.entity.Item itemEntity = itemOpt.get();
		itemEntity.setProductId(product.getProductId());
		itemEntity.setProductDescription(converter.convertDescriptionDtoToEntity(product.getProductDescription()));
//		itemEntity.setProductDescription();
		itemEntity.setProductName(product.getProductName());
		itemEntity.setProductType(product.getProductType());
		itemEntity.setQuantity(product.getQuantity());
		itemEntity.setProductPrice(product.getProductPrice());
		itemEntity.setIsActive(product.getIsActive());

		com.nagarro.orderservice.entity.Item updatedItemEntity = itemRepository.save(itemEntity);

		Item updatedItem = converter.convertItemEntityToDto(updatedItemEntity);

		return updatedItem;
	}

	@Override
	@Transactional
	public Item updateStock(long productId, Item product) {

		final Optional<com.nagarro.orderservice.entity.Item> itemOpt = itemRepository.findById(productId);

		if (itemOpt.isEmpty()) {
			throw new ItemNotFoundException(
					"NO ITEMS PRESENT WITH THIS ID -" + productId + "SO CAN'T UPDATE STOCK OF THIS ITEM");
		}

		final long currentStock = itemRepository.getCurrentStockOfItem(productId);

		final long updatedStock = currentStock + product.getQuantity();

		itemRepository.updateStock(updatedStock, productId);

		final com.nagarro.orderservice.entity.Item itemEntity = itemRepository.findById(productId).get();

		final Item item = converter.convertItemEntityToDto(itemEntity);

		return item;
	}

	@Override
	@Transactional
	public String deleteItem(long productId) {

		String status = "Not Deleted Product with id " + productId;

		final Optional<com.nagarro.orderservice.entity.Item> itemOpt = itemRepository.findById(productId);

		if (itemOpt.isEmpty()) {

			throw new ItemNotFoundException(
					"NO ITEMS PRESENT WITH THIS ID -" + productId + "SO CAN'T PERFORM DELETE OPREATION");
		} else {
			itemRepository.deleteById(productId);
			status = "Deleted Product with id " + productId;
		}
		return status;
	}

	@Override
	@Transactional
	public String updateItemStatus(long productId, Item product) {
		String message = "Not Updated Item Status With Item Id " + productId;

		boolean isItemPresent = itemRepository.existsById(productId);

		if (!isItemPresent) {
			throw new ItemNotFoundException(
					"NO ITEMS PRESENT WITH THIS ID -" + productId + "SO CAN'T PERFORM UPDATE STATUS OPREATION");
		} else {
			itemRepository.updateItemStatus(product.getIsActive(), productId);
			
			message = "UPDATED ITEM STATUS OF ITEM WITH PRODUCT ID " + productId + " NOW ITEM IS ACTIVE ="
					+ product.getIsActive();
		}

		return message;
	}

}

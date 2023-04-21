package com.nagarro.orderservice.controller;

import java.util.List;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nagarro.orderservice.constant.Constant;
import com.nagarro.orderservice.dto.Item;
import com.nagarro.orderservice.exception.RequstNotValidException;
import com.nagarro.orderservice.service.ItemService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(Constant.PATH_TO_ITEMS)
public class ItemController {

	@Autowired
	@Qualifier("ItemServiceImpl")
	private ItemService itemService;

	@GetMapping("/{id}")
	@ApiOperation(value = Constant.FIND_ITEM, notes = Constant.NOTE_FOR_GET_ITEM_HANDLER)
	public ResponseEntity<Item> getParticularItem(
			@ApiParam(required =true)
			@PathVariable("id") long id) {
		if (id == 0) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}
		final Item item = itemService.getItemById(id);

		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = Constant.FIND_ITEMS, notes = Constant.NOTE_FOR_GET_ITEMS_HANDLER)
	public ResponseEntity<List<Item>> getItems(
			@ApiParam(value=Constant.ITEM_FILTER_TYPE)
			@RequestParam(defaultValue = "all") String filterBy) {

		if (Constant.BLANK.equals(filterBy) || filterBy == null) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}

		final List<Item> items = itemService.getItems(filterBy);

		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	@PostMapping
	@ApiOperation(value = Constant.ADD_ITEM, notes = Constant.NOTE_FOR_ADD_ITEM)
	public ResponseEntity<Item> saveItemInInventory(@RequestBody Item item, @RequestHeader(name="X-ROLE") String role) {
		System.out.println(role);
		if (item == null) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}

		final Item savedItem = itemService.saveItem(item);

		return new ResponseEntity<Item>(savedItem, HttpStatus.CREATED);
	}

	@PutMapping
	@ApiOperation(value = Constant.UPDATE_ITEM, notes = Constant.NOTE_FOR_UPDATE_ITEM)
	public ResponseEntity<Item> updateParticularItemInInventory(@RequestBody Item item,
			@ApiParam(required = true)
			@RequestParam long productId) {

		if (item == null || productId == 0) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}
		final Item updatedItem = itemService.updateItem(item, productId);

		return new ResponseEntity<Item>(updatedItem, HttpStatus.OK);
	}

	@PatchMapping
	@ApiOperation(value = Constant.UPDATE_ITEM_STOCK, notes = Constant.NOTE_UPDATE_ITEM_STOCK)
	public ResponseEntity<Item> updateItemStock(@RequestParam long productId, @RequestBody Item item) {

		if (item == null || productId == 0) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}

		final Item updatedItem = itemService.updateStock(productId, item);

		return new ResponseEntity<Item>(updatedItem, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = Constant.REMOVE_ITEM, notes = Constant.NOTE_FOR_REMOVE_ITEM)
	public ResponseEntity<String> deleteParticularItemFromInventory(@PathVariable("id") long id) {
		if (id == 0 ) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}
		final String status = itemService.deleteItem(id);

		return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@PatchMapping("/status")
	public ResponseEntity<String> updateItemStatus(@RequestParam long productId,@RequestBody Item item){
		if (item == null || productId == 0) {
			throw new RequstNotValidException(Constant.REQUST_NOT_VALID);
		}
		
		final String message=itemService.updateItemStatus(productId, item);
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}

}

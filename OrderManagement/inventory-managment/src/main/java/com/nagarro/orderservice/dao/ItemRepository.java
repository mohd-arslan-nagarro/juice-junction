package com.nagarro.orderservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.orderservice.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
	
	final static String FIND_ITEM_PRESENT_IN_STOCK="from inventory i where i.quantity > 0";
	
	final static String UPDATE_STOCK_OF_ITEM="update inventory i set i.quantity=?1 where i.productId=?2";
	
	final static String UPDATE_STATUS_OF_ITEM="update inventory i set i.isActive=?1 where i.productId=?2";
	
	final static String FIND_ITEM_NOT_IN_STOCK="from inventory i where i.quantity=0";
	
	final static String GET_CURRENT_STOCK_OF_ITEM="select i.quantity from inventory i where i.productId=?1";
	
	final static String GET_CURRENT_PRICE_OF_ITEM="select i.productPrice from inventory i where i.productId=?1";
	@Query(FIND_ITEM_PRESENT_IN_STOCK )
	List<Item> findItemsAvailableInStocks();


	@Query(FIND_ITEM_NOT_IN_STOCK )
	List<Item> findSoldOutItems();
	
	List<Item> findByIsActiveFalse();
	
	@Modifying(clearAutomatically = true)
	@Query(UPDATE_STOCK_OF_ITEM)
	void updateStock(long updatedQuantity,long productId);
	
	@Modifying(clearAutomatically = true)
	@Query(UPDATE_STATUS_OF_ITEM)
	void updateItemStatus(boolean isActive,long productId);
	
	@Query(GET_CURRENT_STOCK_OF_ITEM)
	long getCurrentStockOfItem(long productId);
	
	@Query(GET_CURRENT_PRICE_OF_ITEM)
	long getCurrentPriceOfItem(long productId);
	
	

}

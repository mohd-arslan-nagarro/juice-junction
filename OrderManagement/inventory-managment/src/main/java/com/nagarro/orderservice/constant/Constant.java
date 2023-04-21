package com.nagarro.orderservice.constant;

public class Constant {
	
	public static final String REQUST_NOT_VALID="Oops Input Data provided by you is not valid";
	
	public static final String DATE_PATTERN="dd/MM/yyyy";
	
	public static final String PATH_TO_REPORTS="/reports";
	
	public static final String PATH_TO_ITEMS="/items";
	
	public static final String PATH_TO_ORDERS="/orders";
	
	public static final String PATH_TO_CUSTOMERS="/customers";
	
	public static final String BLANK="";
	
	public static final String PROVIDE_VALID_TIMELINE="Please Provide Valid Time Line";
	
	public static final String FIND_CUSTOMER="FIND CUSTOMER DETAILS BY CUSTOMER ID";
	
	public static final String FIND_ORDER_HISTORY="FIND CUSTOMER ORDER HISTORY";
	
	public static final String NOTE_FOR_GET_CUSTOMER_HANDLER="it will return customer with empty field of orders";

	public static final String NOTE_FOR_GET_ORDER_HISTORY_HANDLER="it will return all orders placed by customer but it needs customer id for that";

	public static final String CUSTOMER_ID_TYPE="CUSTOMER ID SHOULB BE IN NUMBER";
	
	public static final String FIND_ORDERS="FIND ALL ORDERS";
	
	public static final String NOTE_FOR_GET_ORDERS_HANDLER="it will return list of order";
	
	public static final String FIND_ORDER="FIND ORDER BY ORDER ID";
	
	public static final String FIND_ITEM="FIND ITEM BY PRODUCT ID";
	
	public static final String FIND_ITEMS="FIND ITEMS BY FILTER";
	
	public static final String NOTE_FOR_GET_ORDER_HANDLER="it will return order of pirticular order id but you must have to provide order id";

	public static final String NOTE_FOR_GET_ITEM_HANDLER="it will return item of pirticular item id but the transcation of that item shoul be empty because it is not required here .";

	public static final String NOTE_FOR_GET_ITEMS_HANDLER="it will return list of items according to provided filter.  by default it will give all items if you not provide filter .";
	
	public static final String ORDER_ID_TYPE="ORDER ID SHOULB BE IN NUMBER";

	public static final String FIND_ORDERS_BY_FILTER_IN_TIMELINE="FIND ORDERS PLACED IN TIME LINE";
	
	public static final String NOTE_FOR_GET_ORDERS_BY_FILTER_IN_TIMELINE="it will return the list of orders with in provided timeline and accrding to filter";

	public static final String ORDER_FILTER_TYPE="it shoud be either sold / purchased";
	
	public static final String ITEM_FILTER_TYPE="it shoud be either instock / soldout / all";
	
	public static final String PLACE_ORDER="PLACE ORDER";
	
	public static final String NOTE_FOR_PLACE_ORDER="it will return placed order but for placing order you need to provide details along with customer id and item id";

	public static final String ADD_ITEM="ADD ITEM IN INVENTORY";
	
	public static final String REMOVE_ITEM="DELETE ITEM FROM INVENTORY";
	
	public static final String NOTE_FOR_REMOVE_ITEM="TO DELETE ITEM FROM INVENTORY YOU MUST PROVIDE PRODUCT ID . AND BE AWARE DELETING ITEM LEADS TO DELETE ALL TRANSCATION RECORDS ASSOCIATED WITH THAT ITEM";
	
	public static final String UPDATE_ITEM="UPDATE ITEM IN INVENTORY";
	
	public static final String NOTE_FOR_ADD_ITEM="it will return added item but for adding item in inventory you need to provide all deatils required for item including description . no need to provide transcations for item bucz it is managed with differt api request ";

	public static final String NOTE_FOR_UPDATE_ITEM="it will return updated item but for updating item in inventory you need to provide all deatils required for item including description . no need to provide transcations for item bucz it is managed with differt api request ";

	public static final String UPDATE_ITEM_STOCK="UPDATE ITEM STOCK";
	
	public static final String NOTE_UPDATE_ITEM_STOCK="for updating particular item stock you must have to provide product id and item olny with quantiy you want to update ";
	public static final String INVENTORY_ITEMS="/inventory/items/**";

}

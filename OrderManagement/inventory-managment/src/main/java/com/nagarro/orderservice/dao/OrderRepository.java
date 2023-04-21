package com.nagarro.orderservice.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.orderservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("FROM order_details t WHERE t.purchasedDate BETWEEN ?1 AND ?2")
	List<Order> findOrdersPurchasedInTimeLine(Date fromDate,Date toDate);
	
	@Query("FROM order_details t WHERE t.soldDate BETWEEN ?1 AND ?2")
	List<Order> findOrdersSoldInTimeLine(Date fromDate,Date toDate);
	
	@Query("FROM order_details t WHERE t.customer.customerId =?1")
	List<Order> findOrdersByCustomerId(String customerId);

	@Query("FROM order_details t WHERE t.email=?1")
	List<Order> findOrdersByEmailId(String emailId);

	@Query("FROM order_details t WHERE t.orderStatus=?1")
	List<Order> findPendingOrders(String orderStatus);

	@Modifying(clearAutomatically = true)
	@Query("update order_details i set i.orderStatus=?1 where i.orderId=?2")
	void updateOrderStatus(String orderStatus ,long orderId);



	
}

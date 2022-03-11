package com.api.order.repo;

import java.util.List;
import java.util.Optional;

import com.api.order.model.Order;

public interface CustomMongoRepo {

	public Optional<Order> getOrderBId(String orderid);
	public Optional<List<Order>> getAllOrders();
	public Order updateOrder(Order order);
	public long deleteOrder(String orderid);
	public Order saveOrder(Order order);
	
	public Optional<List<Order>> getOnlyAddress();
	public Optional<List<Order>> getOnlyItems();
	public Optional<List<Order>> findAllOrderSortByAddress();
}

package com.api.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.order.model.Order;
import com.api.order.repo.CustomMongoRepo;

@Service
public class OrderService {
	@Autowired
	private CustomMongoRepo customMongoRepo;

	public Order placeOrder(@RequestBody Order order) {
		return customMongoRepo.saveOrder(order);
	}
	public Optional<Order> getOrderById(String orderId) {
		return customMongoRepo.getOrderBId(orderId);
	}
	public List<Order> getAllOrders(){
		return customMongoRepo.getAllOrders().get();
	}
	public long deleteOrderById(String orderId) {
		return customMongoRepo.deleteOrder(orderId);
	}
	public Order updateOrderById(Order order) {
		return customMongoRepo.updateOrder(order);
	}
	public Optional<List<Order>> getOnlyAddress(){
		return customMongoRepo.getOnlyAddress();
	}
	public Optional<List<Order>> getOnlyItems(){
		return customMongoRepo.getOnlyItems();
	}
	private Integer privateMethod(Integer value) {
		return value+100;
	}
}

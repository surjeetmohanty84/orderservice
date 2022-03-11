package com.api.order.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.api.order.model.Order;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.operation.AggregateOperation;
@Repository
public class CustomMongoRepoImpl implements CustomMongoRepo{

	@Autowired
	private MongoTemplate mongoTemplate;
	@Override
	public Optional<Order> getOrderBId(String orderid) {
		// TODO Auto-generated method stub
		return Optional.ofNullable( mongoTemplate.findById(orderid, Order.class));
		
	}

	@Override
	public Optional<List<Order>> getAllOrders() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(mongoTemplate.findAll(Order.class));
	}

	@Override
	public Order updateOrder(Order order) {
		Query query=new Query();
		query.addCriteria(Criteria.where("orderId").is(order.getOrderId()));
		Update update=new Update();
		update.set("items", order.getItems());
		update.set("shippingAddress", order.getShippingAddress());
		UpdateResult result= mongoTemplate.updateFirst(query, update, Order.class);
		
		return mongoTemplate.findAndModify(query, update, Order.class);
		//return result.getModifiedCount();
		
	}

	@Override
	public long deleteOrder(String orderid) {
		Query query=new Query();
		query.addCriteria(Criteria.where("orderId").is(orderid));
		DeleteResult result= mongoTemplate.remove(query, Order.class);
		return result.getDeletedCount();
	}

	@Override
	public Order saveOrder(Order order) {
		return mongoTemplate.save(order);
	}

	@Override
	public Optional<List<Order>> getOnlyAddress() {
		Query query=new Query();
		query.fields().include("shippingAddress");
		
		return Optional.ofNullable(mongoTemplate.find(query,Order.class));
	}

	@Override
	public Optional<List<Order>> getOnlyItems() {
		Query query=new Query();
		query.fields().include("items");
		
		return Optional.ofNullable( mongoTemplate.find(query, Order.class));
	}
	public Optional<List<Order>> findAllOrderSortByAddress(){
		
		//SortOperation sortOperation=Aggregation.sort(Sort.by(SortD))
		//Aggregation aggregation=Aggregation.newUpdate(operations);
		SortOperation sortOperation=Aggregation.sort(Sort.by("shipingAddress"));
		Aggregation aggregation= Aggregation.newAggregation(sortOperation);
		return null;
	}

}

package com.api.order.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.api.order.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{

}

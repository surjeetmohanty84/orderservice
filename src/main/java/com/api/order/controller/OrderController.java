package com.api.order.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.order.exception.OrderNotFoundException;
import com.api.order.model.Order;
import com.api.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		System.out.println("Received Order For " + order.getItems().size() + " Items");
		order.getItems().forEach((lineItem) -> System.out
				.println("Item: " + lineItem.getItemCode() + " Quantity: " + lineItem.getQuantity()));

		String orderId = UUID.randomUUID().toString();
		order.setOrderId(orderId);
		orderService.placeOrder(order);
		// orders.put(orderId, order);
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}
	@GetMapping("get/{orderid}")
	public ResponseEntity<Order> getOrderById(@PathVariable("orderid") String orderid) {
		System.out.println("Received OrderId========="+ orderid);
		Optional<Order> op=orderService.getOrderById(orderid);
		Order order= op.orElseThrow(()->new OrderNotFoundException("Order Not Found"));
		return new ResponseEntity<Order>(order, HttpStatus.FOUND);
	}
	@GetMapping("/get/all")
	public ResponseEntity<List<Order>> getAllOrders(){
		List<Order> orders= orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.FOUND);
	}
	@DeleteMapping("/delete/{orderid}")
	public void deleteOrderId(@PathVariable("orderid") String orderid) {
		System.out.println("Deleting Order by Id:"+ orderid);
		orderService.deleteOrderById(orderid);		
	}
	@PutMapping("/update")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
		Order order2=orderService.updateOrderById(order);
		//Order order2=orderService.getOrderById(order.getOrderId()).get();
		
		return new ResponseEntity(order2,HttpStatus.OK);
	}
	@GetMapping("/get/address")
	public ResponseEntity<List<Order>> getOnlyAddress(){
		return new ResponseEntity(orderService.getOnlyAddress().get(),HttpStatus.OK);
	}
	@GetMapping("/get/items")
	public ResponseEntity<List<Order>> getOnlyItems(){
		return new ResponseEntity(orderService.getOnlyItems().get(),HttpStatus.OK);
	}
	@GetMapping("/deleteitem")
	public void testVoid(){
		System.out.println("Test Void==================");
	}
	
}

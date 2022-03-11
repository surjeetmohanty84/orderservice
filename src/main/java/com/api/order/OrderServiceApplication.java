package com.api.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.api.order.repo.OrderRepository;

@SpringBootApplication(scanBasePackages = {"com.api.order.controller","com.api.order.service","com.api.order.repo","com.api.order.exception"})
public class OrderServiceApplication implements CommandLineRunner{
	@Autowired
	private OrderRepository orderRepository;
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		//If we want to run multiple instances of single app on same server, then we need to assign a
		//different port at runtime
		SpringBootUtil.setRandomPort(5000, 5500);
		  ApplicationContext ctx = SpringApplication.run(OrderServiceApplication.class, args);
		  System.out.println("Application " + ctx.getApplicationName() + " started");
	}
	@Override
	public void run(String... args) throws Exception {
		
	}
}

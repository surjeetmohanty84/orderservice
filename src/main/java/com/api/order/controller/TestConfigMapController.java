package com.api.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConfigMapController {
	
	@Value("${app.username:DefaultUsername}")
	private String username;
	@Value("${app.firstname:DefaultFirstName}")
	private String firstname;
	@Value("${app.lastname:DefaultLastName}")
	private String lastname;
	@Value("${globalkey: default}")
	private String globalkey;
	@GetMapping("/getmessage")
	public String getMessage() {
		System.out.println("Message recieved");
		return username+":"+firstname+":"+lastname;
	}
}

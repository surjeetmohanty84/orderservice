package com.api.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomException {
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<OrderExceptionResponse> handleOrderNotFoundException(OrderNotFoundException excep){
		System.out.println("Exception handled=============================");
		OrderExceptionResponse response=new OrderExceptionResponse(excep.getMessage());
		return new ResponseEntity<OrderExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
}

package com.api.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.api.order.controller.OrderController;
import com.api.order.model.LineItem;
import com.api.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {OrderControllerTest.class})
public class OrderControllerTest {
	@Mock
	private OrderService orderService;
	@Autowired
	private MockMvc mockMvc;
	@InjectMocks
	private OrderController orderController;
	private List<com.api.order.model.Order> list;
	private List<LineItem> lineItems;
	private String orderid="102222";
	com.api.order.model.Order order;
	@BeforeEach
	public void setUp() {
		list=new ArrayList<>();
		lineItems=new ArrayList<>();
		order=new com.api.order.model.Order();
		LineItem lineItem=new LineItem();
		lineItem.setItemCode("101");
		lineItem.setQuantity(5);
		lineItems.add(lineItem);
		order.setOrderId("200123");
		order.setShippingAddress("Berhampur");
		order.setItems(lineItems);
		list.add(order);
		mockMvc=MockMvcBuilders.standaloneSetup(orderController).build();
	}
	@Test
	@Order(1)
	public void getCustomerAll() {
	
		
		Mockito.when(orderService.getAllOrders()).thenReturn(list);
		try {
			mockMvc.perform(get("/orders/get/all")).andExpect(status().isFound()).andDo(print());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	@Test
	@Order(2)
	public void getCustomerById() {
		Mockito.when(orderService.getOrderById(orderid)).thenReturn(Optional.of(order));
		try {
			mockMvc.perform(get("/orders/get/{orderid}", orderid))
					.andExpect(status().isFound())
					.andExpect(MockMvcResultMatchers.jsonPath(".orderId").value("200123"))
					.andDo(print());
		}catch(Exception e) {
			
		}
		
	}
	//test post method
	@Test
	@Order(3)
	public void postOrder() {
		Mockito.when(orderService.placeOrder(order)).thenReturn(order);
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonBody=mapper.writeValueAsString(order);
			mockMvc.perform(post("/orders").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated())
					.andDo(print());
					
			
		}catch(Exception e) {
			
		}
	}
	//test Private Method
	@Test
	@Order(4)
	public void testPrivateMethod() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		OrderService orderService=new OrderService();
		Method method= OrderService.class.getDeclaredMethod("privateMethod", Integer.class);
		method.setAccessible(true);
		Integer o=(Integer)method.invoke(orderService, 200);
		System.out.println("Value:::::"+o);
		assertEquals(300, o);
		
		//org.springframework.util.ReflectionUtils.invokeMethod(orderService, "privateMethod",new Integer(200));
	}
	//test void method
	@Test
	@Order(5)
	public void deleteOrderById() {
	//Whenever we write unit test cases for any method we expect a return value from the method and 
	//generally use assert for checking if the functions return the value that we expect it to return, 
    //but in the case of void methods, they do not return any value. So how do we check if our method is functioning properly?
	//Whenever we mock a void method we do not expect a return value that is why we can only verify whether that method is being called or not. 
		Mockito.doNothing().when(orderService).deleteOrderById(orderid);
		
		Mockito.verify(orderService, Mockito.times(1)).deleteOrderById(orderid);
	}
	@Test
	@Order(6)
	public void updateOrderById() {
		//Mockito.when(orderService.deleteOrderById(orderid));
		
	}
	
	
}

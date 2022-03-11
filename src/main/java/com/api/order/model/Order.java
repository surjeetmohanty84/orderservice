package com.api.order.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class Order {

		@JsonProperty("orderId")
		@Id
	    private String orderId;

	    @JsonProperty("items")
	    private List<LineItem> items;

	    @JsonProperty("shippingAddress")
	    private String shippingAddress;

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public List<LineItem> getItems() {
			return items;
		}

		public void setItems(List<LineItem> items) {
			this.items = items;
		}

		public String getShippingAddress() {
			return shippingAddress;
		}

		public void setShippingAddress(String shippingAddress) {
			this.shippingAddress = shippingAddress;
		}


}

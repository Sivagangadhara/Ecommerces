package com.example.demo.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class OrderResponseDTO {
	@JsonProperty("id")
	private Long orderId;
	
    private UserDTO user;
    private ProductDTO product;
    
    
    
	public OrderResponseDTO() {
		super();
	}
	public OrderResponseDTO(Long orderId, UserDTO user, ProductDTO product) {
		super();
		this.orderId = orderId;
		this.user = user;
		this.product = product;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
    
    

}

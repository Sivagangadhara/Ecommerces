package com.example.demo.Payload;

public class OrderDetailsDTO {
	private Long id;
	private int quantity;
	private UserDTO user;
	private ProductDTO product;

	public OrderDetailsDTO() {
	}

	public OrderDetailsDTO(Long id, int quantity, UserDTO user, ProductDTO product) {
		this.id = id;
		this.quantity = quantity;
		this.user = user;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

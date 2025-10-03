package com.example.demo.Payload;

public class OrderResponseDTO {
	private Long id;
	private Long userId;
	private Long productId;
	private int quantity;

	public OrderResponseDTO() {
	}

	public OrderResponseDTO(Long id, Long userId, Long productId, int quantity) {
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

package com.example.demo.payload;

public class PaymentResponseDTO {
	private Long paymentId;
    private String status;
    private OrderResponseDTO orderDetails;
    
    
    
	public PaymentResponseDTO() {
		super();
	}


	public PaymentResponseDTO(Long paymentId, String status, OrderResponseDTO orderDetails) {
		super();
		this.paymentId = paymentId;
		this.status = status;
		this.orderDetails = orderDetails;
	}


	public Long getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public OrderResponseDTO getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(OrderResponseDTO orderDetails) {
		this.orderDetails = orderDetails;
	}
    
    
    
}

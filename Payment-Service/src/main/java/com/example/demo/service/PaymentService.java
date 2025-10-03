package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Payment;
import com.example.demo.payload.PaymentResponseDTO;

public interface PaymentService {
	Payment savePayment(Payment payment);
    Payment getPaymentById(Long id);
    List<PaymentResponseDTO> getAllPayments();
    void deletePayment(Long id);

    // Extra: Payment with Order + User + Product
    PaymentResponseDTO getPaymentDetails(Long paymentId);
}

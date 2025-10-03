package com.example.demo.Serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.example.demo.Entity.Payment;
import com.example.demo.payload.OrderResponseDTO;
import com.example.demo.payload.PaymentResponseDTO;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
public class PaymentServiceImpl implements PaymentService{
	private final PaymentRepository paymentRepository;
    private final WebClient.Builder webClientBuilder;
    

    public PaymentServiceImpl(PaymentRepository paymentRepository, Builder webClientBuilder) {
		super();
		this.paymentRepository = paymentRepository;
		this.webClientBuilder = webClientBuilder;
	}

	// ✅ Save
    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // ✅ Find by Id (only payment)
    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    
    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream().map(payment -> {
            // Call Order Service for each payment
            OrderResponseDTO order = webClientBuilder.build()
                    .get()
                    .uri("http://ORDER-SERVICE/orders/" + payment.getOrderId())
                    .retrieve()
                    .bodyToMono(OrderResponseDTO.class)
                    .block();

            return new PaymentResponseDTO(payment.getId(), payment.getStatus(), order);
        }).collect(Collectors.toList());
    }

    // ✅ Delete
    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    // ✅ Find by Id with Order + User + Product
    @Override
    public PaymentResponseDTO getPaymentDetails(Long paymentId) {
        Payment payment = getPaymentById(paymentId);

        // Call Order Service
        OrderResponseDTO order = webClientBuilder.build()
                .get()
                .uri("http://Order-Service/orders/" + payment.getOrderId())
                .retrieve()
                .bodyToMono(OrderResponseDTO.class)
                .block();

        return new PaymentResponseDTO(payment.getId(), payment.getStatus(), order);
    }
}
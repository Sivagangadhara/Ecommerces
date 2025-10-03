package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.Entity.Order;
import com.example.demo.Payload.OrderDetailsDTO;
import com.example.demo.Payload.OrderRequestDTO;
import com.example.demo.Payload.OrderResponseDTO;
import com.example.demo.Payload.ProductDTO;
import com.example.demo.Payload.UserDTO;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.ServiceInf.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository repo;
	private final WebClient webClient;

	public OrderServiceImpl(OrderRepository repo, WebClient.Builder webClientBuilder) {
		this.repo = repo;
		this.webClient = webClientBuilder.build();
	}

	@Override
	public OrderResponseDTO createOrder(OrderRequestDTO dto) {
		Order order = new Order();
		order.setUserId(dto.getUserId());
		order.setProductId(dto.getProductId());
		order.setQuantity(dto.getQuantity());

		Order saved = repo.save(order);

		return new OrderResponseDTO(saved.getId(), saved.getUserId(), saved.getProductId(), saved.getQuantity());
	}

	@Override
	public OrderDetailsDTO getOrderById(Long id) {
		Order order = repo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

		UserDTO user = webClient.get().uri("http://User-Service/users/" + order.getUserId()).retrieve()
				.bodyToMono(UserDTO.class).block();

		ProductDTO product = webClient.get().uri("http://Product-Service/products/" + order.getProductId()).retrieve()
				.bodyToMono(ProductDTO.class).block();

		return new OrderDetailsDTO(order.getId(), order.getQuantity(), user, product);
	}

	@Override
	public List<OrderDetailsDTO> getAllOrders() {
		return repo.findAll().stream().map(order -> {
			UserDTO user = webClient.get().uri("http://User-Service/users/" + order.getUserId()).retrieve()
					.bodyToMono(UserDTO.class).block();

			ProductDTO product = webClient.get().uri("http://Product-Service/products/" + order.getProductId())
					.retrieve().bodyToMono(ProductDTO.class).block();

			return new OrderDetailsDTO(order.getId(), order.getQuantity(), user, product);
		}).collect(Collectors.toList());
	}

	@Override
	public void deleteOrder(Long id) {
		repo.deleteById(id);
	}
}

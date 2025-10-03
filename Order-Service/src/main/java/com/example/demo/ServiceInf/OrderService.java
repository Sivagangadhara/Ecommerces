package com.example.demo.ServiceInf;

import java.util.List;
import com.example.demo.Payload.OrderDetailsDTO;
import com.example.demo.Payload.OrderRequestDTO;
import com.example.demo.Payload.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO dto);
    OrderDetailsDTO getOrderById(Long id);
    List<OrderDetailsDTO> getAllOrders();
    void deleteOrder(Long id);
}

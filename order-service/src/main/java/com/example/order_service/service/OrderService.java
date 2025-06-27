package com.example.order_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.model.Order;
import com.example.order_service.repostitory.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;

  public void placeOrder(OrderRequest orderRequest) {
    Order order = Order.builder()
      .orderNumber(UUID.randomUUID().toString())
      .skuCode(orderRequest.skuCode())
      .price(orderRequest.price())
      .quantity(orderRequest.quantity())
      .build();
      
    orderRepository.save(order);
  }
}

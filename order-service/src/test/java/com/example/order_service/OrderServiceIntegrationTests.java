package com.example.order_service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.model.Order;
import com.example.order_service.repostitory.OrderRepository;
import com.example.order_service.service.OrderService;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@Transactional
class OrderServiceIntegrationTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void shouldSaveOrder() {
        // arrange
        OrderRequest orderRequest = new OrderRequest("123456", 1, BigDecimal.valueOf(100));
        
        // act
        orderService.placeOrder(orderRequest);
        
        // assert
        Order savedOrder = orderRepository.findAll().get(0);
        assertThat(savedOrder.getSkuCode()).isEqualTo(orderRequest.skuCode());
        assertThat(savedOrder.getQuantity()).isEqualTo(orderRequest.quantity());
        assertThat(savedOrder.getPrice()).isEqualTo(orderRequest.price());
        assertThat(savedOrder.getOrderNumber()).isNotNull();
    }
} 
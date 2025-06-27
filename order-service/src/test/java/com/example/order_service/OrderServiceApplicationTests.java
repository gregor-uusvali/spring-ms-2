package com.example.order_service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.model.Order;
import com.example.order_service.repostitory.OrderRepository;
import com.example.order_service.service.OrderService;

@ExtendWith(MockitoExtension.class)
class OrderServiceApplicationTests {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderService orderService;

	@Test
	void shouldSaveOrder() {
		// arrange
		OrderRequest orderRequest = new OrderRequest("123456", 1, BigDecimal.valueOf(100));
		
		// act
		orderService.placeOrder(orderRequest);
		
		// assert
		verify(orderRepository).save(any(Order.class));
	}

}

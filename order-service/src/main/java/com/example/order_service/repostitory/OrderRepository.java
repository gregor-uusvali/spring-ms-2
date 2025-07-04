package com.example.order_service.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.order_service.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

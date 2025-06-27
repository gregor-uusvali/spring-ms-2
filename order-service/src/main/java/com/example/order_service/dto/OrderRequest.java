package com.example.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest(String skuCode, Integer quantity, BigDecimal price) {

}

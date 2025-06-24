package com.example.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.model.Product;
import com.example.product_service.dto.ProductRequest;
import com.example.product_service.dto.ProductResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
  private final ProductRepository productRepository;

  public ProductResponse createProduct(ProductRequest productRequest) {
    Product product = Product.builder()
        .name(productRequest.name())
        .description(productRequest.description())
        .price(productRequest.price())
        .build();
    productRepository.save(product);
    log.info("Product {} is saved", product.getId());
    return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice());
  }

  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream()
        .map(p -> new ProductResponse(
            p.getId(),
            p.getName(),
            p.getDescription(),
            p.getPrice()))
        .toList();
  }
}

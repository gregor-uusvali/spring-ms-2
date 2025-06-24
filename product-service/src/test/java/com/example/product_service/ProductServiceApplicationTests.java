package com.example.product_service;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.product_service.dto.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		mongoDBContainer.start();
	}

	@Test
	void testCreateProduct() throws Exception {
		// Create a product request
		ProductRequest productRequest = new ProductRequest(
			"Test Laptop", 
			"High-performance laptop", 
			new BigDecimal("999.99")
		);
		
		// Method 1: Using base URI with MockMvcRequestBuilders
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/products")  // Base URI is included in the path
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(productRequest)))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Laptop"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(999.99));
	}
	
	@Test
	void testGetAllProducts() throws Exception {
		// Method 2: Using base URI with standalone setup (if needed)
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/products")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}

package com.example.inventory_service.service;

import org.springframework.stereotype.Service;

import com.example.inventory_service.repository.InventoryRepository;
import com.example.inventory_service.model.Inventory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  public boolean isInStock(String skuCode, int quantity) {
    Inventory inventory = inventoryRepository.findBySkuCode(skuCode);
    return inventory != null && inventory.getQuantity() >= quantity;
  }

}

package com._6core.platform.warehousespec.rest.v1.dto.inventory;

public record InventoryAllocateRequest(String inventoryId, String warehouseId, Integer quantity) {}

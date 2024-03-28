package com._6core.platform.warehousespec.rest.v1.dto.inventory;

public record InventorySupplyRequest(
    String warehouseId, String inventoryId, String productId, Integer quantity) {}

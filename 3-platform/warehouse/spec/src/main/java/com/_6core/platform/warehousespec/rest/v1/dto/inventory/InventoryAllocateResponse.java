package com._6core.platform.warehousespec.rest.v1.dto.inventory;

public record InventoryAllocateResponse(
    String locateId, String warehouseId, String inventoryId, Integer quantity) {}

package com._6core.platform.warehousespec.rest.v1.dto.product;

import java.math.BigInteger;

public record ProductDetailsResponse(
    String productId,
    String name,
    String description,
    String image,
    BigInteger price,
    String category,
    Boolean isAvailable) {}

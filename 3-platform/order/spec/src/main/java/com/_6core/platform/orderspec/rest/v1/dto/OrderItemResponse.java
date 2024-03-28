package com._6core.platform.orderspec.rest.v1.dto;

import java.math.BigInteger;

public record OrderItemResponse(
    String itemId, String productId, Integer quantity, BigInteger price) {}

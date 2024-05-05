package com._6core.platform.orderdomain.dto;

import java.math.BigInteger;

public record OrderItemResponse(
    String itemId, String productId, Integer quantity, BigInteger price, String orderId) {}

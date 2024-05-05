package com._6core.platform.orderdomain.dto;

import java.math.BigInteger;

public record OrderItemRequest(
    String itemId,
    String productId,
    Integer quantity,
    BigInteger price,
    String orderId) {}

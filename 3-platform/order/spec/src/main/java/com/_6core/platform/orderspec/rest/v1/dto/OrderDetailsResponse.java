package com._6core.platform.orderspec.rest.v1.dto;

import java.math.BigInteger;
import java.util.Set;

public record OrderDetailsResponse(
    String orderId,
    String status,
    BigInteger total,
    Set<OrderItemResponse> orderItems,
    String userId) {}

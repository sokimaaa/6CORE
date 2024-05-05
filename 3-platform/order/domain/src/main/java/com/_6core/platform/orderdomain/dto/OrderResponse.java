package com._6core.platform.orderdomain.dto;

import java.math.BigInteger;
import java.util.Set;

public record OrderResponse(
    String orderId, String status, BigInteger total, Set<OrderItemResponse> orderItems) {}

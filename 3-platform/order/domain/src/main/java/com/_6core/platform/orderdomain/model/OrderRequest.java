package com._6core.platform.orderdomain.model;

import java.math.BigInteger;
import java.util.Set;

public record OrderRequest(
    String orderId,
    String status,
    BigInteger total,
    Set<OrderItemRequest> orderItems,
    String userId) {}

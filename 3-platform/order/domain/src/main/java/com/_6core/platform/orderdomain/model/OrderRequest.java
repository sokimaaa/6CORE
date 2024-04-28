package com._6core.platform.orderdomain.model;

import com._6core.platform.orderspec.rest.v1.dto.OrderItemResponse;
import java.math.BigInteger;
import java.util.Set;

public record OrderRequest(String orderId,
                           String status,
                           BigInteger total,
                           Set<OrderItemResponse> orderItems,
                           String userId) {
}


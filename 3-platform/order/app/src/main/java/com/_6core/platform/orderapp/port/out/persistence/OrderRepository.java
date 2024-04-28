package com._6core.platform.orderapp.port.out.persistence;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderspec.rest.v1.dto.OrderDetailsResponse;
import reactor.core.publisher.Mono;

public interface OrderRepository {

  Mono<OrderDetailsResponse> createOrder(OrderRequest order);

  Mono<ImmutableOrderV01Impl> getOrderById(String orderId);

  Mono<ImmutableOrderV01Impl> getOrderByUserId(String userId);
}

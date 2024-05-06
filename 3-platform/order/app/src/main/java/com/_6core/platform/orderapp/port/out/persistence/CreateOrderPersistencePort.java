package com._6core.platform.orderapp.port.out.persistence;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderdomain.dto.OrderRequest;
import reactor.core.publisher.Mono;

public interface CreateOrderPersistencePort {

  Mono<ImmutableOrderV01Impl> createOrder(OrderRequest order);

  Mono<ImmutableOrderV01Impl> getOrderById(String orderId);

  Mono<ImmutableOrderV01Impl> getOrderByIdAndStatus(String orderId, String status);
}

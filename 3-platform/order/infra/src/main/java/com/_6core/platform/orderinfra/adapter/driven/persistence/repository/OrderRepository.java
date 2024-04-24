package com._6core.platform.orderinfra.adapter.driven.persistence.repository;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderinfra.adapter.driven.persistence.entity.OrderEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository {

        Mono<OrderEntity> save(Mono<ImmutableOrderV01Impl> order);

        Mono<OrderEntity> getOrderById(String orderId);

        Mono<OrderEntity> update(Mono<ImmutableOrderV01Impl> order);

        Mono<OrderEntity> delete(String orderId);

        Flux<OrderEntity> getAllOrders();

}
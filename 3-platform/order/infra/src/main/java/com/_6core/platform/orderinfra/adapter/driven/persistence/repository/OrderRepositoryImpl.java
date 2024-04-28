package com._6core.platform.orderinfra.adapter.driven.persistence.repository;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderapp.port.out.persistence.OrderRepository;
import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderspec.rest.v1.dto.OrderDetailsResponse;
import reactor.core.publisher.Mono;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void save(ImmutableOrderV01Impl order) {

    }

    @Override
    public Mono<OrderDetailsResponse> getOrderById(String orderId) {
        return null;
    }

    @Override
    public Mono<OrderDetailsResponse> getOrderByUserId(String userId) {
        return null;
    }

    @Override
    public Mono<OrderDetailsResponse> update(OrderRequest order) {
        return null;
    }

    @Override
    public void delete(String orderId) {

    }
}

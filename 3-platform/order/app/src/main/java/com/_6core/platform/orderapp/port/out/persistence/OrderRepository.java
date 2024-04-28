package com._6core.platform.orderapp.port.out.persistence;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderspec.rest.v1.dto.OrderDetailsResponse;
import reactor.core.publisher.Mono;

public interface OrderRepository {

        void save(ImmutableOrderV01Impl order);
        Mono<OrderDetailsResponse> getOrderById(String orderId);
        Mono<OrderDetailsResponse> getOrderByUserId(String userId);
}
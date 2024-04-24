package com._6core.platform.orderinfra.adapter.driven.persistence;

import com._6core.platform.orderdomain.model.OrderStatus;
import reactor.core.publisher.Mono;

public interface OrderDeliveryService {
    Mono<OrderStatus> sendNotification();
}

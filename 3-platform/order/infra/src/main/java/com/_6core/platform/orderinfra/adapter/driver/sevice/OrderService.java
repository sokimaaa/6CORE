package com._6core.platform.orderinfra.adapter.driver.sevice;

import com._6core.platform.orderinfra.dto.CreateOrderRequest;
import com._6core.platform.orderinfra.dto.CreateOrderResponse;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<CreateOrderResponse> createOrder(CreateOrderRequest request);

    Mono<Boolean> checkOrderDuplicate(CreateOrderRequest request);
}

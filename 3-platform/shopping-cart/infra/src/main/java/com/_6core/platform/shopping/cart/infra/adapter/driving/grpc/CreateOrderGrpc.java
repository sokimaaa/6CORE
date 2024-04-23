package com._6core.platform.shopping.cart.infra.adapter.driving.grpc;

import com._6core.platform.shopping.cart.infra.dto.CreateOrderRequest;
import com._6core.platform.shopping.cart.infra.dto.CreateOrderResponse;
import reactor.core.publisher.Mono;

public interface CreateOrderGrpc {
  Mono<CreateOrderResponse> createOrder(Mono<CreateOrderRequest> order);
}

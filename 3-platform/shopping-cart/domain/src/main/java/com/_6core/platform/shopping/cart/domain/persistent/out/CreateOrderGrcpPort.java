package com._6core.platform.shopping.cart.domain.persistent.out;

import com._6core.platform.shopping.cart.domain.CreateOrderRequest;
import com._6core.platform.shopping.cart.domain.CreateOrderResponse;
import reactor.core.publisher.Mono;

public interface CreateOrderGrcpPort {
  Mono<CreateOrderResponse> createOrder(CreateOrderRequest order);
}

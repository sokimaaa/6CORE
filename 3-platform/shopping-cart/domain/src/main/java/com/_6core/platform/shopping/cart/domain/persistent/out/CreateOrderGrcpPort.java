package com._6core.platform.shopping.cart.domain.persistent.out;

import com._6core.platform.shopping.cart.domain.dto.internal.CreateOrderRequest;
import com._6core.platform.shopping.cart.domain.dto.internal.CreateOrderResponse;
import reactor.core.publisher.Mono;

public interface CreateOrderGrcpPort {
  Mono<CreateOrderResponse> createOrder(CreateOrderRequest order);
}

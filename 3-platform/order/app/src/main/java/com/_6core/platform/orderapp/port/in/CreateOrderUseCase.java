package com._6core.platform.orderapp.port.in;

import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.dto.OrderResponse;
import reactor.core.publisher.Mono;

public interface CreateOrderUseCase {
  public Mono<OrderResponse> createOrder(OrderRequest request);
}

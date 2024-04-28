package com._6core.platform.orderapp.port.in;

import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderspec.rest.v1.dto.OrderDetailsResponse;
import reactor.core.publisher.Mono;

public interface OrderCreateUseCase {
  public Mono<OrderDetailsResponse> createOrder(OrderRequest request);
}

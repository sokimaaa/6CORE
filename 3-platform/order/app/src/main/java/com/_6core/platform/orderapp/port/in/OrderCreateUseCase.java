package com._6core.platform.orderapp.port.in;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.dto.OrderResponse;
import reactor.core.publisher.Mono;

public interface OrderCreateUseCase {
  public Mono<OrderResponse> createOrder(OrderRequest request);
}

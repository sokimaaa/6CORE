package com._6core.platform.orderapp.port.in;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderdomain.dto.OrderRequest;
import reactor.core.publisher.Mono;

public interface OrderCreateUseCase {
  public Mono<OrderV01> createOrder(OrderRequest request);
}

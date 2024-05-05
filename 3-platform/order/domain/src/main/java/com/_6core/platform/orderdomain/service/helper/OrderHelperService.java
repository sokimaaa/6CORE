package com._6core.platform.orderdomain.service.helper;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderdomain.model.OrderStatus;
import reactor.core.publisher.Mono;

public interface OrderHelperService {
  Mono<OrderV01> getOrderById(String orderId);

  Mono<OrderV01> getOrderByIdAndStatus(String orderId, String status);
}

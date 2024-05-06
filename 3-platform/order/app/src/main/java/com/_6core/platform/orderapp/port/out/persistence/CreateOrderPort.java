package com._6core.platform.orderapp.port.out.persistence;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderdomain.dto.OrderRequest;
import reactor.core.publisher.Mono;

public interface CreateOrderPort {

  Mono<OrderV01> createOrder(OrderRequest order);

  Mono<OrderV01> getOrderById(String orderId);

  Mono<OrderV01> getOrderByIdAndStatus(String orderId, String status);
}

package com._6core.platform.orderapp.port.out.persistence;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.lib.proto.domain.model.Order;
import reactor.core.publisher.Mono;

public interface OrderRepository {

  Mono<OrderV01> createOrder(Order order);

  Mono<OrderV01> getOrderById(String orderId);

  Mono<OrderV01> getOrderByUserId(String userId);
}

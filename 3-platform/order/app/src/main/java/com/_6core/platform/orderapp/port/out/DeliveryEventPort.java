package com._6core.platform.orderapp.port.out;

import com._6core.platform.orderdomain.model.OrderStatus;
import reactor.core.publisher.Mono;

public interface DeliveryEventPort {
  Mono<OrderStatus> sendNotification();
}

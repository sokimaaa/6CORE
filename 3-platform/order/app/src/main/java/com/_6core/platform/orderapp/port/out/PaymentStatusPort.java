package com._6core.platform.orderapp.port.out;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderdomain.model.PaymentStatus;
import reactor.core.publisher.Mono;

public interface PaymentStatusPort {
  Mono<PaymentStatus> payForOrder(OrderV01 order);
}

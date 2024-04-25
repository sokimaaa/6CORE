package com._6core.platform.orderinfra.adapter.driver.sevice;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderdomain.model.PaymentStatus;
import reactor.core.publisher.Mono;

public interface OrderPaymentService {
    Mono<PaymentStatus> payForOrder(ImmutableOrderV01Impl order);
}

package com._6core.platform.orderapp.port.out;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderdomain.model.PaymentStatus;
import reactor.core.publisher.Mono;

public interface PaymentEventPort {
    Mono<PaymentStatus> payForOrder(ImmutableOrderV01Impl order);
}

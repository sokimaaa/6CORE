package com._6core.platform.orderdomain.service.helper;

import com._6core.lib.hexagonal.annotations.DomainService;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import reactor.core.publisher.Mono;

@DomainService
public interface CreateOrderHelperService {
  Mono<ImmutableOrderV01Impl> getOrderById(String orderId);

  Mono<ImmutableOrderV01Impl> getOrderByIdAndStatus(String orderId, String status);
}

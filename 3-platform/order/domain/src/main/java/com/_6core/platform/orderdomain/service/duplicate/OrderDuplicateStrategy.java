package com._6core.platform.orderdomain.service.duplicate;

import reactor.core.publisher.Mono;

public interface OrderDuplicateStrategy<T> {
  Mono<Boolean> isDuplicate(T request);
}

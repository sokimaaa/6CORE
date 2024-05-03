package com._6core.platform.shopping.cart.domain.persistent.in;

import com._6core.platform.shopping.cart.domain.dto.internal.CheckoutRequest;
import com._6core.platform.shopping.cart.domain.dto.internal.CheckoutResponse;
import reactor.core.publisher.Mono;

public interface CheckoutUseCase {
  Mono<CheckoutResponse> checkout(CheckoutRequest checkoutRequest);
}

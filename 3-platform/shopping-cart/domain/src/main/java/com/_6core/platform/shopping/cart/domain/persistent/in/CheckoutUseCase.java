package com._6core.platform.shopping.cart.domain.persistent.in;

import com._6core.platform.shopping.cart.spec.rest.v1.request.CheckoutShoppingCartRequest;
import com._6core.platform.shopping.cart.spec.rest.v1.response.CheckoutShoppingCartResponse;
import reactor.core.publisher.Mono;

public interface CheckoutUseCase {
  Mono<CheckoutShoppingCartResponse> checkout(
      String cartId, CheckoutShoppingCartRequest checkoutRequest);
}

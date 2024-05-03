package com._6core.platform.shopping.cart.domain.persistent.out;

import com._6core.lib.java.domain.model.cart.immutable.ShoppingCartV01Impl;
import com._6core.platform.shopping.cart.domain.dto.internal.CartId;
import com._6core.platform.shopping.cart.domain.dto.internal.EmptyCartResponse;
import reactor.core.publisher.Mono;

public interface CheckoutShoppingCartPort {
  Mono<ShoppingCartV01Impl> get(CartId cartId);

  Mono<EmptyCartResponse> clean(CartId cartId);
}

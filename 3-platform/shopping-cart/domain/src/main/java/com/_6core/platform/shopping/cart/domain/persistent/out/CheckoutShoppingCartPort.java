package com._6core.platform.shopping.cart.domain.persistent.out;

import com._6core.lib.java.domain.model.cart.immutable.ShoppingCartV01Impl;
import com._6core.platform.shopping.cart.domain.CartId;
import com._6core.platform.shopping.cart.domain.EmptyCartResponse;
import reactor.core.publisher.Mono;

public interface CheckoutShoppingCartPort {
  Mono<ShoppingCartV01Impl> getCart(CartId cartId);

  Mono<EmptyCartResponse> cleanCart(CartId cartId);
}

package com._6core.platform.shopping.cart.infra.adapter.driven.persistence;

import com._6core.platform.shopping.cart.infra.adapter.driven.persistence.entity.ShoppingCartEntity;
import reactor.core.publisher.Mono;

public interface ShoppingCartRepository {
  Mono<ShoppingCartEntity> getCart(String cartId);

  Mono<Void> cleanCart(String cartId);
}

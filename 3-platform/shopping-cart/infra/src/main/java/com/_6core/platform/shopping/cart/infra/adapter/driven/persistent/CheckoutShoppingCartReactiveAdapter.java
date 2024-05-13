package com._6core.platform.shopping.cart.infra.adapter.driven.persistent;

import com._6core.lib.java.domain.model.cart.immutable.ShoppingCartV01Impl;
import com._6core.platform.shopping.cart.domain.dto.internal.CartId;
import com._6core.platform.shopping.cart.domain.dto.internal.EmptyCartResponse;
import com._6core.platform.shopping.cart.domain.persistent.out.CheckoutShoppingCartPort;
import com._6core.platform.shopping.cart.infra.adapter.driven.persistent.repository.CheckoutShoppingCartReactiveRepository;
import com._6core.platform.shopping.cart.infra.mapper.ImmutableShoppingCart2ShoppingCartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CheckoutShoppingCartReactiveAdapter implements CheckoutShoppingCartPort {
  private final CheckoutShoppingCartReactiveRepository repository;
  private final ImmutableShoppingCart2ShoppingCartEntity mapper =
      ImmutableShoppingCart2ShoppingCartEntity.INSTANCE;

  @Override
  public Mono<ShoppingCartV01Impl> get(CartId cartId) {
    return repository.findByCartId(cartId).map(mapper::map2Domain);
  }

  @Override
  public Mono<EmptyCartResponse> clean(CartId cartId) {
    return repository.clean(cartId).then(Mono.fromCallable(EmptyCartResponse::new));
  }
}

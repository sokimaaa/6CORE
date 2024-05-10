package com._6core.platform.shopping.cart.infra.adapter.driven.persistent.repository;

import com._6core.platform.shopping.cart.domain.dto.internal.CartId;
import com._6core.platform.shopping.cart.infra.adapter.driven.persistent.entity.ShoppingCart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CheckoutShoppingCartReactiveRepository extends R2dbcRepository<ShoppingCart, String> {
    @Query(
            "SELECT s FROM ShoppingCart s WHERE s.id = ?1")
    Mono<ShoppingCart> findByCartId(CartId id);

    @Query(
            "UPDATE ShoppingCart s SET s.productIds = null WHERE s.id = ?1")
    Mono<Void> clean(CartId cartId);
}

package com._6core.platform.shopping.cart.domain.mapper;

import com._6core.platform.shopping.cart.domain.CartId;
import com._6core.platform.shopping.cart.domain.CreateOrderResponse;
import com._6core.platform.shopping.cart.spec.rest.v1.response.CheckoutShoppingCartResponse;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface CreateOrderResponse2CheckoutShoppingCartResponse {
  CreateOrderResponse2CheckoutShoppingCartResponse INCTANCE =
      Mappers.getMapper(CreateOrderResponse2CheckoutShoppingCartResponse.class);

  @Mapping(target = "ok", source = "isOk")
  @Mapping(target = "transactionalId", ignore = true)
  CheckoutShoppingCartResponse map(CreateOrderResponse orderResponse, CartId cartId);
}

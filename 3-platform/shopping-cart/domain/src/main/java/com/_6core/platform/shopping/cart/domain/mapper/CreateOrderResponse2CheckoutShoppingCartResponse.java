package com._6core.platform.shopping.cart.domain.mapper;

import com._6core.platform.shopping.cart.domain.dto.internal.CartId;
import com._6core.platform.shopping.cart.domain.dto.internal.CheckoutResponse;
import com._6core.platform.shopping.cart.domain.dto.internal.CreateOrderResponse;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface CreateOrderResponse2CheckoutShoppingCartResponse {
  CreateOrderResponse2CheckoutShoppingCartResponse INCTANCE =
      Mappers.getMapper(CreateOrderResponse2CheckoutShoppingCartResponse.class);

  @Mapping(target = "ok", source = "isOk")
  @Mapping(target = "transactionalId", ignore = true)
  CheckoutResponse map(CreateOrderResponse orderResponse, CartId cartId);
}

package com._6core.platform.shopping.cart.domain.mapper;

import com._6core.platform.shopping.cart.domain.dto.internal.CartId;
import com._6core.platform.shopping.cart.domain.dto.internal.CheckoutResponse;
import com._6core.platform.shopping.cart.domain.dto.internal.CreateOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreateOrderResponse2CheckoutShoppingCartResponse {
  CreateOrderResponse2CheckoutShoppingCartResponse INSTANCE =
      Mappers.getMapper(CreateOrderResponse2CheckoutShoppingCartResponse.class);

  @Mapping(target = "transactionId", ignore = true)
  @Mapping(target = "ok", source = "orderResponse.isOk")
  @Mapping(target = "orderId", source = "orderResponse.orderId")
  CheckoutResponse map(CreateOrderResponse orderResponse, CartId cartId);
}

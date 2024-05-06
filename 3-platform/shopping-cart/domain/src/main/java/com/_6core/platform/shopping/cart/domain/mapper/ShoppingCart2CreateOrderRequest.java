package com._6core.platform.shopping.cart.domain.mapper;

import com._6core.platform.shopping.cart.domain.dto.internal.CreateOrderRequest;

@Mapper
public interface ShoppingCart2CreateOrderRequest {
  ShoppingCart2CreateOrderRequest INSTANCE =
      Mappers.getMapper(ShoppingCart2CreateOrderRequest.class);

  @Mapping(target = "productIds", source="productIds")
  CreateOrderRequest map(ShoppingCartV01 cartDomain);
}

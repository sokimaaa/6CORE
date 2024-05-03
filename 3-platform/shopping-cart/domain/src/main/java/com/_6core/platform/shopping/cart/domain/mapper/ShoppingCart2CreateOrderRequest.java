package com._6core.platform.shopping.cart.domain.mapper;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.domain.dto.internal.CreateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShoppingCart2CreateOrderRequest {
  ShoppingCart2CreateOrderRequest INCTANCE =
      Mappers.getMapper(ShoppingCart2CreateOrderRequest.class);

  @Mapping(target = "productIds", expression = "java(cartDomain.productIds())")
  CreateOrderRequest map(ShoppingCartV01 cartDomain);
}

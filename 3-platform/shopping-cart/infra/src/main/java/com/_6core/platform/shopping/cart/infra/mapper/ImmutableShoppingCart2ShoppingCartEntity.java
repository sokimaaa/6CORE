package com._6core.platform.shopping.cart.infra.mapper;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.lib.java.domain.model.cart.immutable.ImmutableShoppingCartV01Impl;
import com._6core.platform.shopping.cart.infra.adapter.driven.persistent.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImmutableShoppingCart2ShoppingCartEntity {
    ImmutableShoppingCart2ShoppingCartEntity INSTANCE =
            Mappers.getMapper(ImmutableShoppingCart2ShoppingCartEntity.class);

    @Mapping(target = "cartId", source = "id")
    ImmutableShoppingCartV01Impl map2Domain(ShoppingCart entity);

    @Mapping(target = "id", source = "cartId")
    ShoppingCart map2Entity(ShoppingCartV01 shoppingCartV01);
}

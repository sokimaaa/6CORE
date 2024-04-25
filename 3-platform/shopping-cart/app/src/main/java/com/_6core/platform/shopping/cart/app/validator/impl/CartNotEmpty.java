package com._6core.platform.shopping.cart.app.validator.impl;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.app.validator.ValidationChainBuilder;
import com._6core.platform.shopping.cart.domain.ValidationResponse;
import com._6core.platform.shopping.cart.domain.mapper.Boolean2ValidationResponseMapper;

public class CartNotEmpty extends ValidationChainBuilder<ShoppingCartV01> {
  @Override
  public ValidationResponse validate(ShoppingCartV01 cartEntity) {
    return Boolean2ValidationResponseMapper.INCTANCE.map(
        !cartEntity.productIds().isEmpty() || validateNext(cartEntity).getIsValid());
  }
}

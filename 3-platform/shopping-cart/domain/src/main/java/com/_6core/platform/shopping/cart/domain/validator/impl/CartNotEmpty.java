package com._6core.platform.shopping.cart.domain.validator.impl;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.domain.dto.internal.ValidationResponse;
import com._6core.platform.shopping.cart.domain.mapper.Boolean2ValidationResponseMapper;
import com._6core.platform.shopping.cart.domain.validator.BaseValidationChain;

public class CartNotEmpty extends BaseValidationChain<ShoppingCartV01> {
  @Override
  public ValidationResponse validate(ShoppingCartV01 cartEntity) {
    return Boolean2ValidationResponseMapper.INSTANCE.map(
        !cartEntity.getProductIds().isEmpty() && validateNext(cartEntity).getIsValid());
  }
}

package com._6core.platform.shopping.cart.app.validator.impl;

import com._6core.platform.shopping.cart.app.validator.ValidationHandler;
import com._6core.platform.shopping.cart.domain.ShoppingCartDomain;

/** Example of possible validation rule implementation. Can be changed or superseded. */
public class CartNotEmpty extends ValidationHandler<ShoppingCartDomain> {
  @Override
  public Boolean validate(ShoppingCartDomain cartEntity) {
    if (cartEntity.productIds().isEmpty()) {
      return false;
    }
    return validateNext(cartEntity);
  }
}

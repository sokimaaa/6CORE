package com._6core.platform.shopping.cart.app.validator.impl;

import com._6core.platform.shopping.cart.app.validator.ValidationHandler;
import com._6core.platform.shopping.cart.domain.ShoppingCartDomain;
import java.util.List;

/** Example of possible validation rule implementation. Can be changed or superseded. */
public class HasRestrictedItems extends ValidationHandler<ShoppingCartDomain> {
  private static List<String> restrictedItems;

  @Override
  public Boolean validate(ShoppingCartDomain cartEntity) {
    System.out.println(this.getClass().getSimpleName() + " works");
    boolean isContinRestricted =
        cartEntity.productIds().stream().anyMatch(t -> restrictedItems.contains(t));
    if (isContinRestricted) return false;
    return validateNext(cartEntity);
  }
}

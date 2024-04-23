package com._6core.platform.shopping.cart.app.usecase;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import java.util.List;

public interface CheckoutUseCase {
  // CheckoutShoppingCartResponse checkout(String cartId);
  Boolean validate(ShoppingCartV01 cart);

  List<String> composeCreateOrderRequest(ShoppingCartV01 cart);
}

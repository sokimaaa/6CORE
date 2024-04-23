package com._6core.platform.shopping.cart.app.usecase;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.app.validator.ValidationHandler;
import com._6core.platform.shopping.cart.app.validator.impl.CartNotEmpty;
import com._6core.platform.shopping.cart.app.validator.impl.HasRestrictedItems;
import com._6core.platform.shopping.cart.domain.ShoppingCartDomain;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CheckoutCartUseCase implements CheckoutUseCase {
  private final ValidationHandler<ShoppingCartDomain> cartHandler =
      new ValidationHandler<ShoppingCartDomain>() {};

  @Override
  public Boolean validate(ShoppingCartV01 cart) {
    return cartHandler
        .build(new CartNotEmpty(), new HasRestrictedItems())
        .validate(new ShoppingCartDomain());
  }

  @Override
  public List<String> composeCreateOrderRequest(ShoppingCartV01 cart) {
    return cart.productIds();
  }
}

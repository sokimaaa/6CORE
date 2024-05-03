package com._6core.platform.shopping.cart.domain.validator;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import java.util.List;

public class ValidationChainBuilder {
  public Validator<ShoppingCartV01> build(final List<Validator<ShoppingCartV01>> validators) {
    int size = validators.size();
    for (int i = 0; i < size - 1; i++) {
      validators.get(i).setNext(validators.get(i + 1));
    }
    return validators.get(0);
  }
}

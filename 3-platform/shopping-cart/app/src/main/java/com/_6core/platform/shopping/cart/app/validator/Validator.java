package com._6core.platform.shopping.cart.app.validator;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.domain.ValidationResponse;

public interface Validator<T extends ShoppingCartV01> {
  ValidationResponse validate(final T domainModel);
}

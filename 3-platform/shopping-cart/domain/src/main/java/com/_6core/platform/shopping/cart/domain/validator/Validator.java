package com._6core.platform.shopping.cart.domain.validator;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.domain.dto.internal.ValidationResponse;

public interface Validator<T extends ShoppingCartV01> {
  ValidationResponse validate(final T domainModel);

  public void setNext(final Validator<T> validator);
}

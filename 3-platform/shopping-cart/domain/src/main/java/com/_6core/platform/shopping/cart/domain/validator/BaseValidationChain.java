package com._6core.platform.shopping.cart.domain.validator;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.domain.dto.internal.ValidationResponse;
import com._6core.platform.shopping.cart.domain.mapper.Boolean2ValidationResponseMapper;

public abstract class BaseValidationChain<T extends ShoppingCartV01>
    implements Validator<ShoppingCartV01> {
  private Validator<ShoppingCartV01> next;

  @Override
  public void setNext(final Validator<ShoppingCartV01> validator) {
    this.next = validator;
  }

  protected ValidationResponse validateNext(final T domainModel) {
    return Boolean2ValidationResponseMapper.INCTANCE.map(
        next == null || next.validate(domainModel).getIsValid());
  }

  @Override
  public abstract ValidationResponse validate(final ShoppingCartV01 domainModel);
}

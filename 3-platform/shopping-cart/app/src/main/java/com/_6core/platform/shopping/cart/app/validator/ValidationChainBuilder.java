package com._6core.platform.shopping.cart.app.validator;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.domain.ValidationResponse;
import com._6core.platform.shopping.cart.domain.mapper.Boolean2ValidationResponseMapper;

public abstract class ValidationChainBuilder<T extends ShoppingCartV01>
    implements Validator<ShoppingCartV01> {
  private ValidationChainBuilder<T> nextValidator;

  public ValidationChainBuilder<T> build(
      final ValidationChainBuilder<T> first, final ValidationChainBuilder<T>... chain) {
    ValidationChainBuilder<T> head = first;
    for (ValidationChainBuilder<T> nextInChain : chain) {
      head.nextValidator = nextInChain;
      head = nextInChain;
    }
    return first;
  }

  public void setNextValidator(final ValidationChainBuilder<T> validator) {
    if (nextValidator == null) {
      this.nextValidator = validator;
    } else {
      ValidationChainBuilder<T> buffer = nextValidator;
      nextValidator = validator;
      nextValidator.nextValidator = buffer;
    }
  }

  protected ValidationResponse validateNext(final T entity) {
    return Boolean2ValidationResponseMapper.INCTANCE.map(
        nextValidator == null || nextValidator.validate(entity).getIsValid());
  }
}

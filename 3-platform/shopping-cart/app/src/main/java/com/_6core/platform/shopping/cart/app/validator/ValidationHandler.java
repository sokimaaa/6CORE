package com._6core.platform.shopping.cart.app.validator;

public abstract class ValidationHandler<T> implements Validator<T> {
  private ValidationHandler<T> nextValidator;

  public ValidationHandler<T> build(
      final ValidationHandler<T> first, final ValidationHandler<T>... chain) {
    ValidationHandler<T> head = first;
    for (ValidationHandler<T> nextInChain : chain) {
      head.nextValidator = nextInChain;
      head = nextInChain;
    }
    return first;
  }

  public void setNextValidator(final ValidationHandler<T> validator) {
    if (nextValidator == null) {
      this.nextValidator = validator;
    } else {
      ValidationHandler<T> buffer = nextValidator;
      nextValidator = validator;
      nextValidator.nextValidator = buffer;
    }
  }

  @Override
  public Boolean validate(T entity) {
    return false;
  }

  protected Boolean validateNext(final T entity) {
    if (nextValidator == null) {
      return true;
    }
    return nextValidator.validate(entity);
  }
}

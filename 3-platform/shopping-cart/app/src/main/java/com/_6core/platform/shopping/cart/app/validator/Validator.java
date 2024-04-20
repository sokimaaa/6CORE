package com._6core.platform.shopping.cart.app.validator;

public interface Validator<T> {
  Boolean validate(final T entity);
}

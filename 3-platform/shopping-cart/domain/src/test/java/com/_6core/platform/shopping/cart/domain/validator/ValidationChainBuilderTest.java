package com._6core.platform.shopping.cart.domain.validator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import com._6core.platform.shopping.cart.domain.validator.impl.CartNotEmpty;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidationChainBuilderTest {
  private ValidationChainBuilder chainBuilder = new ValidationChainBuilder();

  @BeforeEach
  void setUp() {
    this.chainBuilder = new ValidationChainBuilder();
  }

  @Test
  void build_returnFirstValidator() {
    Validator<ShoppingCartV01> firstValidator =
        chainBuilder.build(List.of(new CartNotEmpty(), new CartNotEmpty()));
    assertNotNull(firstValidator);
    assertNotNull(firstValidator.getNext());
    assertNull(firstValidator.getNext().getNext());
  }
}

package com._6core.platform.shopping.cart.domain.validator.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com._6core.lib.java.domain.model.cart.immutable.ImmutableShoppingCartV01Impl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartNotEmptyTest {
  private CartNotEmpty cartNotEmpty;

  @BeforeEach
  void setUp() {
    this.cartNotEmpty = new CartNotEmpty();
  }

  @Test
  void validate_OneValidatorFilledCart_True() {
    ImmutableShoppingCartV01Impl fieldCart =
        ImmutableShoppingCartV01Impl.builder()
            .addAllProductIds(List.of("item1", "item2"))
            .cartId("testCartId")
            .build();
    Boolean isValidResponse = cartNotEmpty.validate(fieldCart).getIsValid();
    assertNotNull(isValidResponse);
    assertTrue(isValidResponse);
  }

  @Test
  void validate_OneValidatorEmptyCart_False() {
    ImmutableShoppingCartV01Impl emptyCart =
        ImmutableShoppingCartV01Impl.builder()
            .addAllProductIds(List.of())
            .cartId("testCartId")
            .build();
    Boolean isValidResponse = cartNotEmpty.validate(emptyCart).getIsValid();
    assertNotNull(isValidResponse);
    assertFalse(isValidResponse);
  }

  @Test
  void validate_TwoValidatorsFilledCart_True() {
    cartNotEmpty.setNext(new CartNotEmpty());
    ImmutableShoppingCartV01Impl fieldCart =
        ImmutableShoppingCartV01Impl.builder()
            .addAllProductIds(List.of("item1", "item2"))
            .cartId("testCartId")
            .build();
    Boolean isValidResponse = cartNotEmpty.validate(fieldCart).getIsValid();
    assertNotNull(isValidResponse);
    assertTrue(isValidResponse);
  }

  @Test
  void validate_TwoValidatorsEmtyCart_False() {
    cartNotEmpty.setNext(new CartNotEmpty());
    ImmutableShoppingCartV01Impl fieldCart =
        ImmutableShoppingCartV01Impl.builder()
            .addAllProductIds(List.of())
            .cartId("testCartId")
            .build();
    Boolean isValidResponse = cartNotEmpty.validate(fieldCart).getIsValid();
    assertNotNull(isValidResponse);
    assertFalse(isValidResponse);
  }
}

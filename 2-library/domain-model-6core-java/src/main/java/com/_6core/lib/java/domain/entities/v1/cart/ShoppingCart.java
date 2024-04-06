package com._6core.lib.java.domain.entities.v1.cart;

import org.immutables.value.Value;
import java.io.Serializable;
import java.util.List;

@Value.Immutable
public interface ShoppingCart extends Serializable {
    String cartId();
    List<String> productIds();
}

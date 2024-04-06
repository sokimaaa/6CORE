package com._6core.lib.java.domain.entities.v1.order;

import org.immutables.value.Value;
import java.io.Serializable;
import java.math.BigInteger;

@Value.Immutable
public interface OrderItem extends Serializable {

    String itemId();

    String orderId();

    String productId();

    Integer quantity();

    BigInteger price();
}

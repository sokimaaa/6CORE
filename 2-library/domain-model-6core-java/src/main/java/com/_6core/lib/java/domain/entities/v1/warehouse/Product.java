package com._6core.lib.java.domain.entities.v1.warehouse;

import org.immutables.value.Value;
import java.io.Serializable;
import java.math.BigInteger;

@Value.Immutable
public interface Product extends Serializable {
    String productId();
    String name();
    String description();
    String image();
    BigInteger price();
    String category();
    Inventory inventory();
}


package com._6core.lib.java.domain.immutable.order;

import com._6core.lib.java.domain.model.order.OrderItemV01;
import org.immutables.value.Value;

import java.math.BigInteger;

@Value.Immutable
public abstract class OrderItemV01Impl implements OrderItemV01 {

    @Override
    public abstract String itemId();

    @Override
    public abstract String orderId();

    @Override
    public abstract String productId();

    @Override
    public abstract Integer quantity();

    @Override
    public abstract BigInteger price();

}

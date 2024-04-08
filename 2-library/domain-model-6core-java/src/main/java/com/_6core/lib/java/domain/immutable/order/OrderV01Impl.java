package com._6core.lib.java.domain.immutable.order;

import com._6core.lib.java.domain.model.order.OrderItemV01;
import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.lib.java.domain.model.order.OrderV02;
import org.immutables.value.Value;

import java.math.BigInteger;
import java.util.Set;

@Value.Immutable
public abstract class OrderV01Impl implements OrderV01, OrderV02 {
    @Override
    public abstract String orderId();

    @Override
    public abstract String status();

    @Override
    public abstract BigInteger total();

    @Override
    public abstract Set<OrderItemV01> orderItems();
}

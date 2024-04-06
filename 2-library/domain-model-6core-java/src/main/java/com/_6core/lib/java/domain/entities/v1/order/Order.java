package com._6core.lib.java.domain.entities.v1.order;

import com._6core.lib.java.domain.model.order.OrderItemV01;
import org.immutables.value.Value;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

@Value.Immutable
public interface Order extends Serializable {
    String orderId();
    String status();
    BigInteger total();
    Set<OrderItemV01> orderItems();
}

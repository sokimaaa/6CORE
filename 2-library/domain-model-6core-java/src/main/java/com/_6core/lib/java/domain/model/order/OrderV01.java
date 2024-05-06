package com._6core.lib.java.domain.model.order;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

public interface OrderV01 extends Serializable {
    /**
     * The unique identifier of order.
     *
     * @return the order id
     */
    String getOrderId();

    /**
     * The status of an order.
     *
     * @return the status
     */
    String getStatus();

    /**
     * The total price of an order in integer format.
     * <p>
     * The value returned represents the total price of the order where the last two digits
     * correspond to the fractional unit of the currency, typically representing cents or a
     * similar subunit. For example, a return value of 12345 would indicate a price
     * of 123 units (e.g., dollars, euros, pounds) and 45 subunits (e.g., cents, pence).
     * This format allows for precise financial calculations without the need for floating-point numbers.
     *
     * @return the total price
     */
    BigInteger getTotal();

    /**
     * Bunch of order items that were ordered.
     *
     * @return the set of order items
     */
    Set<OrderItemV01> getOrderItems();
}

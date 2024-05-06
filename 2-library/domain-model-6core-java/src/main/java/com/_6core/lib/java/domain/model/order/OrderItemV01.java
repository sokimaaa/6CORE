package com._6core.lib.java.domain.model.order;

import java.io.Serializable;
import java.math.BigInteger;

public interface OrderItemV01 extends Serializable {
    /**
     * The unique identifier of item that relates to specific order.
     *
     * @return the item id
     */
    String getItemId();

    /**
     * The order that owns an item.
     *
     * @return the order id
     */
    String getOrderId();

    /**
     * The product that was added to order.
     *
     * @return the product id
     */
    String getProductId();

    /**
     * The number of products that was ordered.
     *
     * @return the quantity
     */
    Integer getQuantity();

    /**
     * Calculates and returns the price of the current order item.
     * <p>
     * This method computes the price by multiplying the quantity of the product by its unit price.
     * The result represents the total cost of this order item, taking into account the number of units
     * purchased.
     * <p>
     * The value returned represents the total price of the order where the last two digits
     * correspond to the fractional unit of the currency, typically representing cents or a
     * similar subunit.
     *
     * @return The total price
     */
    BigInteger getPrice();
}

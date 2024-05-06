package com._6core.lib.java.domain.model.warehouse;

import java.io.Serializable;

public interface InventoryV01 extends Serializable {
    /**
     * The inventory unique identifier.
     *
     * @return the inventory id
     */
    String getInventoryId();

    /**
     * The warehouse that accumulates this inventory.
     *
     * @return the warehouse
     */
    WarehouseV01 getWarehouse();

    /**
     * The product that stores.
     *
     * @return the product
     */
    ProductV01 getProduct();

    /**
     * The actual quantity of a product.
     * <p>
     * Returns the real, or physical, count of a product's stock available in inventory,
     * irrespective of any reservations, holds, or pending transactions.
     *
     * @return the actual quantity
     */
    Integer getActualQuantity();

    /**
     * The available quantity of a product for new reservations or purchases.
     * <p>
     * The available quantity reflects the number of units that are currently available
     * for new customers to reserve or purchase, providing a dynamic measure of stock availability
     * that accounts for ongoing transactions and reservations.
     *
     * @return the available quantity
     */
    Integer getAvailableQuantity();
}

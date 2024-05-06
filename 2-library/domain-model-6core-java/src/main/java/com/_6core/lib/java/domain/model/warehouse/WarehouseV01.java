package com._6core.lib.java.domain.model.warehouse;

import java.io.Serializable;

public interface WarehouseV01 extends Serializable {
    /**
     * The warehouse unique identifier.
     *
     * @return the warehouse id
     */
    String getWarehouseId();

    /**
     * The address location of warehouse.
     *
     * @return the address
     */
    String getAddress();
}

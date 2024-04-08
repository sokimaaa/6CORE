package com._6core.lib.java.domain.immutable.warehouse;

import com._6core.lib.java.domain.model.warehouse.InventoryV01;
import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.lib.java.domain.model.warehouse.WarehouseV01;
import org.immutables.value.Value;

@Value.Immutable
public abstract class InventoryV01Impl implements InventoryV01 {

    public abstract String inventoryId();

    public abstract WarehouseV01 warehouse();

    public abstract ProductV01 product();

    public abstract Integer actualQuantity();

    public abstract Integer availableQuantity();
}



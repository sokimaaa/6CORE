package com._6core.lib.java.domain.immutable.warehouse;

import com._6core.lib.java.domain.model.warehouse.WarehouseV01;
import org.immutables.value.Value;

@Value.Immutable
public abstract class WarehouseV01Impl implements WarehouseV01 {

    @Override
    public abstract String warehouseId();

    @Override
    public abstract String address();
}

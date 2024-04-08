package com._6core.lib.java.domain.immutable.warehouse;

import com._6core.lib.java.domain.model.warehouse.InventoryV01;
import com._6core.lib.java.domain.model.warehouse.ProductV01;
import org.immutables.value.Value;

import java.math.BigInteger;

@Value.Immutable
public abstract class ProductV01Impl implements ProductV01 {
    @Override
    public abstract String productId();

    @Override
    public abstract String name();

    @Override
    public abstract String description();

    @Override
    public abstract String image();

    @Override
    public abstract BigInteger price();

    @Override
    public abstract String category();

    @Override
    public abstract InventoryV01 inventory();
}

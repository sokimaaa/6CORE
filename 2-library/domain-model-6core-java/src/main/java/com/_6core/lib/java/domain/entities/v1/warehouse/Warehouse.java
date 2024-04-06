package com._6core.lib.java.domain.entities.v1.warehouse;

import org.immutables.value.Value;

import java.io.Serializable;

@Value.Immutable
public interface Warehouse extends Serializable {

    String warehouseId();

    String address();
}

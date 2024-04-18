package com._6core.platform.warehousedomain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "warehouses")
public class Warehouse {
    @Id
    private Long id;
    private String address;
}

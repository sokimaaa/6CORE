package com._6core.platform.warehousedomain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "inventories")
public class Inventory {
    @Id
    private Long id;
    private int actual_quantity;
    private int available_quantity;
}

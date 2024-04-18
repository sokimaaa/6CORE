package com._6core.platform.warehousedomain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@Table(name = "products")
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private String image;
    private BigDecimal price;
}

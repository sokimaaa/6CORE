package com._6core.platform.warehouseinfra.adapter.driven.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "inventories")
public class InventoryEntity {
  @Id private Long id;

  @Column("actual_quantity")
  private int actualQuantity;

  @Column("available_quantity")
  private int availableQuantity;

  @Column("warehouse_id")
  private String warehouseId;

  @Column("product_id")
  private String productId;
}

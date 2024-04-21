package com._6core.platform.warehouseinfra.adapter.driven.persistence.entity;

import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "inventories")
public class InventoryEntity {
  @Id private String id;

  @Column("actual_quantity")
  private Integer actualQuantity;

  @Column("available_quantity")
  private Integer availableQuantity;

  @Transient
  private WarehouseEntity warehouse;

  @Transient
  private ProductEntity product;
}

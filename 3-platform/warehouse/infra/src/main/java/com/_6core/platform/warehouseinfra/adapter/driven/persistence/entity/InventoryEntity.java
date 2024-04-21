package com._6core.platform.warehouseinfra.adapter.driven.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Optional;

@Data
@NoArgsConstructor
@Table(name = "inventories")
public class InventoryEntity {
  @Id private Long id;

  @Column("actual_quantity")
  private Integer actualQuantity;

  @Column("available_quantity")
  private Integer availableQuantity;

  private WarehouseEntity warehouse;

  private ProductEntity product;

  public Optional<WarehouseEntity> getWarehouse() {
    return Optional.ofNullable(this.warehouse);
  }

  public Optional<ProductEntity> getProduct() {
    return Optional.ofNullable(this.product);
  }

}

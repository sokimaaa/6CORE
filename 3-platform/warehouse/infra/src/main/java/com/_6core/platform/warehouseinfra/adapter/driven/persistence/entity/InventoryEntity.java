package com._6core.platform.warehouseinfra.adapter.driven.persistence.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
  private Integer actualQuantity;

  @Column("available_quantity")
  private Integer availableQuantity;

  @OneToOne
  @JoinColumn(name = "warehouse_id", nullable = false)
  private WarehouseEntity warehouse;

  @OneToOne
  @JoinColumn(name = "product_id", nullable = false)
  private ProductEntity product;
}

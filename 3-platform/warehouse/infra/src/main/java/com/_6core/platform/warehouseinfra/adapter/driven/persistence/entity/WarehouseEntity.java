package com._6core.platform.warehouseinfra.adapter.driven.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table(name = "warehouses")
public class WarehouseEntity {
  @Id private String id;
  private String address;
}

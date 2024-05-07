package com._6core.platform.warehouseinfra.adapter.driven.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "warehouses")
public class WarehouseEntity {
  @Id private String id;
  private String address;
}

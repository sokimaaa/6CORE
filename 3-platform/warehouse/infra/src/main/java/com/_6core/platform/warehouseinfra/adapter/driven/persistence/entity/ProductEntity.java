package com._6core.platform.warehouseinfra.adapter.driven.persistence.entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity {
  @Id private Long id;
  private String name;
  private String description;
  private String image;
  private BigDecimal price;
}

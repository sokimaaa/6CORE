package com._6core.platform.orderinfra.adapter.driven.persistence.entity;

import java.math.BigInteger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItemEntity {
  @Id private String id;

  @Column("order_id")
  private String orderId;

  @Column("product_id")
  private String productId;

  private Integer quantity;
  private BigInteger price;
}

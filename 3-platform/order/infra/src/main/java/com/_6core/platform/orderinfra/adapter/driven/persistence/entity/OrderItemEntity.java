package com._6core.platform.orderinfra.adapter.driven.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigInteger;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "order_items")
public class OrderItemEntity {
  @Id private String id;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  @Column(name = "order_id")
  private OrderEntity order;

  @Column(name = "product_id")
  private String productId;

  private Integer quantity;
  private BigInteger price;
}

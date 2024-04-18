package com._6core.platform.orderdomain.model;

import java.math.BigInteger;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "order_items")
public class OrderItem {
  @Id private String id;

  @Column("order_id")
  private String orderId;

  @Column("product_id")
  private String productId;

  private Integer quantity;
  private BigInteger price;
}

package com._6core.platform.orderdomain.model;

import java.math.BigInteger;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "orders")
public class Order {
  @Id private String id;
  private BigInteger total;
  private String status;
  @Transient private Set<OrderItem> orderItems;
}

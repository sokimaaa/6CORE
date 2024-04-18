package com._6core.platform.orderinfra.adapter.driven.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigInteger;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity {
  @Id private String id;
  private BigInteger total;

  @Enumerated(value = EnumType.STRING)
  private OrderStatus status;

  @OneToMany(mappedBy = "order")
  private Set<OrderItemEntity> orderItems;

  public enum OrderStatus {
    UNDEFINED
  }
}

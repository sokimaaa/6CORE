package com._6core.platform.orderinfra.adapter.driven.persistence.entity;

import com._6core.platform.orderdomain.model.OrderStatus;
import java.math.BigInteger;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {
  @Id private String id;
  private BigInteger total;
  private OrderStatus status;
  @Transient private Set<OrderItemEntity> orderItems;
}

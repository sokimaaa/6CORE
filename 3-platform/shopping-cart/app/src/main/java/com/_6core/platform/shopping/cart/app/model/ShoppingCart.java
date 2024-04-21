package com._6core.platform.shopping.cart.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "shopping_carts")
public class ShoppingCart {
  @Id private String id;
  private String[] productIds;
}

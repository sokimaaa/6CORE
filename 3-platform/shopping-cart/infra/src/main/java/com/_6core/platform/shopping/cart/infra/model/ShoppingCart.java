package com._6core.platform.shopping.cart.infra.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "shopping_carts")
public class ShoppingCart {
  @Id private Long id;
  private List<String> productIds;
}

package com._6core.platform.shopping.cart.domain;

import com._6core.lib.java.domain.model.cart.ShoppingCartV01;
import java.util.List;

public class ShoppingCartDomain implements ShoppingCartV01 {
  private String id;
  private List<String> productIds;

  public void setId(String id) {
    this.id = id;
  }

  public void setProductIds(List<String> productIds) {
    this.productIds = productIds;
  }

  @Override
  public String cartId() {
    return id;
  }

  @Override
  public List<String> productIds() {
    return productIds;
  }
}

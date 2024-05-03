package com._6core.platform.orderdomain.service.duplicate;

import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;

public class UserIDDuplicateStrategy implements OrderDuplicateStrategy<OrderRequest> {
  private final OrderHelperService orderHelper;

  public UserIDDuplicateStrategy(OrderHelperService orderHelper) {
    this.orderHelper = orderHelper;
  }

  @Override
  public boolean isDuplicate(OrderRequest request) {
    return orderHelper
        .getOrderByUserId(request.userId())
        .hasElement()
        .blockOptional()
        .orElse(false);
  }
}

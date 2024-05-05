package com._6core.platform.orderdomain.service.duplicate;

import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;

public class StatusDuplicateStrategy implements OrderDuplicateStrategy<OrderRequest> {
  private final OrderHelperService orderHelper;

  public StatusDuplicateStrategy(OrderHelperService orderHelper) {
    this.orderHelper = orderHelper;
  }

  @Override
  public boolean isDuplicate(OrderRequest request) {
    return orderHelper
        .getOrderByIdAndStatus(request.orderId(), request.status())
        .hasElement()
        .blockOptional()
        .orElse(false);
  }
}

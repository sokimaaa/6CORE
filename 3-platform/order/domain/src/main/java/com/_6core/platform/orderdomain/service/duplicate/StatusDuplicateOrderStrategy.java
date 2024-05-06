package com._6core.platform.orderdomain.service.duplicate;

import com._6core.lib.hexagonal.annotations.DomainService;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.service.helper.CreateOrderHelperService;

@DomainService
public class StatusDuplicateOrderStrategy implements DuplicateOrderStrategy<OrderRequest> {
  private final CreateOrderHelperService orderHelper;

  public StatusDuplicateOrderStrategy(CreateOrderHelperService orderHelper) {
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

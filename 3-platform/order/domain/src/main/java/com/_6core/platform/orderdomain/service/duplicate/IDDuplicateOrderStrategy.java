package com._6core.platform.orderdomain.service.duplicate;

import com._6core.lib.hexagonal.annotations.DomainService;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.service.helper.CreateOrderHelperService;

@DomainService
public class IDDuplicateOrderStrategy implements DuplicateOrderStrategy<OrderRequest> {
  private final CreateOrderHelperService orderHelper;

  public IDDuplicateOrderStrategy(CreateOrderHelperService orderHelper) {
    this.orderHelper = orderHelper;
  }

  @Override
  public boolean isDuplicate(OrderRequest request) {
    return orderHelper.getOrderById(request.orderId()).hasElement().blockOptional().orElse(false);
  }
}

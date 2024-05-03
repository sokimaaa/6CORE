package com._6core.platform.orderdomain.service.duplicate;

import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;

public class OrderIDDuplicateStrategy implements OrderDuplicateStrategy<OrderRequest> {
  private final OrderHelperService orderHelper;

  public OrderIDDuplicateStrategy(OrderHelperService orderHelper) {
    this.orderHelper = orderHelper;
  }

  @Override
  public boolean isDuplicate(OrderRequest request) {
    System.out.println("Check by order id");
    return orderHelper.getOrderById(request.orderId())
            .hasElement()
            .blockOptional()
            .orElse(false);
  }
}

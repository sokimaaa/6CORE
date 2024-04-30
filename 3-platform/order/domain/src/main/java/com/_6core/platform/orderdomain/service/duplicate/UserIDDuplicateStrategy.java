package com._6core.platform.orderdomain.service.duplicate;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;
import com._6core.platform.orderdomain.model.OrderRequest;
import reactor.core.publisher.Mono;

public class UserIDDuplicateStrategy implements OrderDuplicateStrategy<OrderRequest> {
  private final OrderHelperService orderHelper;

  public UserIDDuplicateStrategy(OrderHelperService orderHelper) {
    this.orderHelper = orderHelper;
  }

  @Override
  public boolean isDuplicate(OrderRequest request) {
    Mono<OrderV01> orderByUserId = orderHelper.getOrderByUserId(request.userId());
    return Boolean.TRUE.equals(orderByUserId.hasElement().block());
  }
}

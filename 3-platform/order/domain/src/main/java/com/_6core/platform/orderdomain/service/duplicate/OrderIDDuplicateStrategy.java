package com._6core.platform.orderdomain.service.duplicate;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;
import reactor.core.publisher.Mono;

public class OrderIDDuplicateStrategy implements OrderDuplicateStrategy<OrderRequest> {
  private final OrderHelperService orderHelper;

  public OrderIDDuplicateStrategy(OrderHelperService orderRepository) {
    this.orderHelper = orderRepository;
  }

  @Override
  public boolean isDuplicate(OrderRequest request) {
    Mono<OrderV01> orderById = orderHelper.getOrderById(request.orderId());
      return Boolean.TRUE.equals(orderById.hasElement().block());
  }
}

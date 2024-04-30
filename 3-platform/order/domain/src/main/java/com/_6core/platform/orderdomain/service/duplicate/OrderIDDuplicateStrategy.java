package com._6core.platform.orderdomain.service.duplicate;

import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;
import reactor.core.publisher.Mono;

public class OrderIDDuplicateStrategy implements OrderDuplicateStrategy<OrderRequest> {
  private final OrderHelperService orderHelper;

  public OrderIDDuplicateStrategy(OrderHelperService orderRepository) {
    this.orderHelper = orderRepository;
  }

  @Override
  public Mono<Boolean> isDuplicate(OrderRequest request) {
    return orderHelper.getOrderById(request.orderId()).hasElement();
  }
}

package com._6core.platform.orderdomain.service.correctness;

import com._6core.platform.orderdomain.model.OrderRequest;

public class OrderItemsCorrect implements OrderCorrectnessStrategy<OrderRequest> {
  @Override
  public boolean isCorrect(OrderRequest request) {
    return request.orderItems().isEmpty();
  }
}

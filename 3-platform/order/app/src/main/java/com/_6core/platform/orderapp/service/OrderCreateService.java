package com._6core.platform.orderapp.service;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderapp.port.in.OrderCreateUseCase;
import com._6core.platform.orderapp.port.out.persistence.CreateOrderPort;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.service.correctness.OrderCorrectnessContext;
import com._6core.platform.orderdomain.service.correctness.OrderCorrectnessStrategy;
import com._6core.platform.orderdomain.service.correctness.OrderItemsCorrect;
import com._6core.platform.orderdomain.service.correctness.OrderTotalCorrect;
import com._6core.platform.orderdomain.service.duplicate.OrderDuplicateContext;
import com._6core.platform.orderdomain.service.duplicate.OrderDuplicateStrategy;
import com._6core.platform.orderdomain.service.duplicate.OrderIDDuplicateStrategy;
import com._6core.platform.orderdomain.service.duplicate.StatusDuplicateStrategy;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OrderCreateService implements OrderCreateUseCase {
  private final OrderIDDuplicateStrategy orderIdDuplicateStrategy;
  private final StatusDuplicateStrategy statusDuplicateStrategy;
  private final OrderTotalCorrect orderTotalCorrect;
  private final OrderItemsCorrect orderItemsCorrect;
  private final CreateOrderPort createOrderPort;

  @Override
  public Mono<OrderV01> createOrder(OrderRequest request) {
    if (orderDuplicateChecker(request)) {
      return Mono.error(new RuntimeException("Order is a duplicate"));
    }
    if (!orderCorrectness(request)) {
      return Mono.error(new RuntimeException("Invalid order data"));
    }

    return createOrderPort.createOrder(request);
  }

  public boolean orderDuplicateChecker(OrderRequest request) {
    OrderDuplicateContext<OrderRequest> duplicateContext = new OrderDuplicateContext<>();
    List<OrderDuplicateStrategy<OrderRequest>> strategies = new ArrayList<>();
    strategies.add(orderIdDuplicateStrategy);
    strategies.add(statusDuplicateStrategy);

    for (OrderDuplicateStrategy<OrderRequest> strategy : strategies) {
      duplicateContext.setStrategy(strategy);
      if (duplicateContext.executeStrategy(request)) {
        return true;
      }
    }
    return false;
  }

  public boolean orderCorrectness(OrderRequest request) {
    OrderCorrectnessContext<OrderRequest> correctnessContext = new OrderCorrectnessContext<>();
    List<OrderCorrectnessStrategy<OrderRequest>> strategies = new ArrayList<>();
    strategies.add(orderItemsCorrect);
    strategies.add(orderTotalCorrect);

    for (OrderCorrectnessStrategy<OrderRequest> strategy : strategies) {
      correctnessContext.setStrategy(strategy);
      if (correctnessContext.executeStrategy(request)) {
        return true;
      }
    }
    return false;
  }
}

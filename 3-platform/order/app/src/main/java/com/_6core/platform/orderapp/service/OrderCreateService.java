package com._6core.platform.orderapp.service;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderapp.port.in.OrderCreateUseCase;
import com._6core.platform.orderapp.port.out.persistence.OrderRepository;
import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.correctness.strategy.OrderCorrectnessContext;
import com._6core.platform.orderdomain.service.correctness.strategy.OrderCorrectnessStrategy;
import com._6core.platform.orderdomain.service.duplicate.strategy.OrderDuplicateContext;
import com._6core.platform.orderdomain.service.duplicate.strategy.OrderDuplicateStrategy;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OrderCreateService implements OrderCreateUseCase {
  private final OrderDuplicateStrategy<OrderRequest> orderIdDuplicateStrategy;
  private final OrderDuplicateStrategy<OrderRequest> userIdDuplicateStrategy;
  private final OrderDuplicateContext<OrderRequest> duplicateContext;
  private final OrderCorrectnessStrategy<OrderRequest> orderTotalCorrect;
  private final OrderCorrectnessStrategy<OrderRequest> orderItemsCorrect;
  private final OrderCorrectnessContext<OrderRequest> correctnessContext;
  private final OrderRepository orderRepository;

  @Override
  public Mono<OrderV01> createOrder(OrderRequest request) {

    if (orderDuplicateChecker(request)) {
      return Mono.error(new RuntimeException("Order is a duplicate"));
    }
    if (!orderCorrectness(request)) {
      return Mono.error(new RuntimeException("Invalid order data"));
    }

    return orderRepository.createOrder(request);
  }

  public boolean orderDuplicateChecker(OrderRequest request) {
    List<OrderDuplicateStrategy<OrderRequest>> strategies = new ArrayList<>();
    strategies.add(orderIdDuplicateStrategy);
    strategies.add(userIdDuplicateStrategy);

    for (OrderDuplicateStrategy<OrderRequest> strategy : strategies) {
      duplicateContext.setStrategy(strategy);
      if (duplicateContext.executeStrategy(request)) {
        return true;
      }
    }
    return false;
  }

  public boolean orderCorrectness(OrderRequest request) {
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

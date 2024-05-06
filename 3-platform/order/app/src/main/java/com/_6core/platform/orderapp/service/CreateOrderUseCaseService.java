package com._6core.platform.orderapp.service;

import com._6core.lib.hexagonal.annotations.UseCase;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderapp.port.in.CreateOrderUseCase;
import com._6core.platform.orderapp.port.out.persistence.CreateOrderPersistencePort;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.dto.OrderResponse;
import com._6core.platform.orderdomain.mapper.OrderMapper;
import com._6core.platform.orderdomain.service.correctness.CorrectnessOrderContext;
import com._6core.platform.orderdomain.service.correctness.CorrectnessOrderStrategy;
import com._6core.platform.orderdomain.service.correctness.ItemsCorrectOrderStrategy;
import com._6core.platform.orderdomain.service.correctness.TotalCorrectOrderStrategy;
import com._6core.platform.orderdomain.service.duplicate.DuplicateOrderContext;
import com._6core.platform.orderdomain.service.duplicate.DuplicateOrderStrategy;
import com._6core.platform.orderdomain.service.duplicate.IDDuplicateOrderStrategy;
import com._6core.platform.orderdomain.service.duplicate.StatusDuplicateOrderStrategy;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class CreateOrderUseCaseService implements CreateOrderUseCase {
  private final IDDuplicateOrderStrategy orderIdDuplicateStrategy;
  private final StatusDuplicateOrderStrategy statusDuplicateStrategy;
  private final TotalCorrectOrderStrategy orderTotalCorrect;
  private final ItemsCorrectOrderStrategy orderItemsCorrect;
  private final CreateOrderPersistencePort createOrderPersistencePort;
  private final OrderMapper mapper;

  @Override
  public Mono<OrderResponse> createOrder(OrderRequest request) {
    if (orderDuplicateChecker(request)) {
      return Mono.error(new RuntimeException("Order is a duplicate"));
    }
    if (!orderCorrectness(request)) {
      return Mono.error(new RuntimeException("Invalid order data"));
    }

    Mono<ImmutableOrderV01Impl> order = createOrderPersistencePort.createOrder(request);
    return mapper.mapToResponseDto(order);
  }

  public boolean orderDuplicateChecker(OrderRequest request) {
    DuplicateOrderContext<OrderRequest> duplicateContext = new DuplicateOrderContext<>();
    List<DuplicateOrderStrategy<OrderRequest>> strategies = new ArrayList<>();
    strategies.add(orderIdDuplicateStrategy);
    strategies.add(statusDuplicateStrategy);

    for (DuplicateOrderStrategy<OrderRequest> strategy : strategies) {
      duplicateContext.setStrategy(strategy);
      if (duplicateContext.executeStrategy(request)) {
        return true;
      }
    }
    return false;
  }

  public boolean orderCorrectness(OrderRequest request) {
    CorrectnessOrderContext<OrderRequest> correctnessContext = new CorrectnessOrderContext<>();
    List<CorrectnessOrderStrategy<OrderRequest>> strategies = new ArrayList<>();
    strategies.add(orderItemsCorrect);
    strategies.add(orderTotalCorrect);

    for (CorrectnessOrderStrategy<OrderRequest> strategy : strategies) {
      correctnessContext.setStrategy(strategy);
      if (correctnessContext.executeStrategy(request)) {
        return true;
      }
    }
    return false;
  }
}

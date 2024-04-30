package com._6core.platform.orderapp.service;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.lib.proto.domain.model.Order;
import com._6core.platform.orderapp.port.in.OrderCreateUseCase;
import com._6core.platform.orderapp.port.out.persistence.OrderRepository;
import com._6core.platform.orderdomain.mapper.OrderMapper;
import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.correctness.OrderCorrectnessContext;
import com._6core.platform.orderdomain.service.correctness.OrderCorrectnessStrategy;
import com._6core.platform.orderdomain.service.duplicate.OrderDuplicateContext;
import com._6core.platform.orderdomain.service.duplicate.OrderDuplicateStrategy;
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
  private final OrderMapper mapper;
  ;

  @Override
  public Mono<OrderV01> createOrder(Order protoRequest) {

    OrderRequest request = mapper.mapToOrderRequest(protoRequest);

    if (orderDuplicateChecker(request)) {
      return Mono.error(new RuntimeException("Order is a duplicate"));
    }
    if (!orderCorrectness(request)) {
      return Mono.error(new RuntimeException("Invalid order data"));
    }

    return orderRepository.createOrder(protoRequest);
  }

  public boolean orderDuplicateChecker(OrderRequest request) {
    List<OrderDuplicateStrategy<OrderRequest>> strategies = new ArrayList<>();
    strategies.add(orderIdDuplicateStrategy);
    strategies.add(userIdDuplicateStrategy);

    for (OrderDuplicateStrategy<OrderRequest> strategy : strategies) {
      duplicateContext.setStrategy(strategy);
      Mono<Boolean> booleanMono = duplicateContext.executeStrategy(request);
      if (Boolean.TRUE.equals(booleanMono.block())) {
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

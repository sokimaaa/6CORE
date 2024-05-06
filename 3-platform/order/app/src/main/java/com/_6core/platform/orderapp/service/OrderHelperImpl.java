package com._6core.platform.orderapp.service;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderapp.port.out.persistence.CreateOrderPersistencePort;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;
import reactor.core.publisher.Mono;

public class OrderHelperImpl implements OrderHelperService {
  private final CreateOrderPersistencePort createOrderPersistencePort;

  public OrderHelperImpl(CreateOrderPersistencePort createOrderPersistencePort) {
    this.createOrderPersistencePort = createOrderPersistencePort;
  }

  @Override
  public Mono<ImmutableOrderV01Impl> getOrderById(String orderId) {
    return createOrderPersistencePort.getOrderById(orderId);
  }

  @Override
  public Mono<ImmutableOrderV01Impl> getOrderByIdAndStatus(String orderId, String status) {
    return createOrderPersistencePort.getOrderByIdAndStatus(orderId, status);
  }
}

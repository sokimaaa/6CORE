package com._6core.platform.orderapp.service;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderapp.port.out.persistence.OrderRepository;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;
import reactor.core.publisher.Mono;

public class OrderHelperImpl implements OrderHelperService {
  private final OrderRepository orderRepository;

  public OrderHelperImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Mono<OrderV01> getOrderById(String orderId) {
    return orderRepository.getOrderById(orderId);
  }

  @Override
  public Mono<OrderV01> getOrderByIdAndStatus(String orderId, String status) {
    return orderRepository.getOrderByIdAndStatus(orderId, status);
  }
}

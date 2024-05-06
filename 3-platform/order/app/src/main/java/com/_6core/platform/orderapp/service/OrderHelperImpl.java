package com._6core.platform.orderapp.service;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderapp.port.out.persistence.CreateOrderPort;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;
import reactor.core.publisher.Mono;

public class OrderHelperImpl implements OrderHelperService {
  private final CreateOrderPort createOrderPort;

  public OrderHelperImpl(CreateOrderPort createOrderPort) {
    this.createOrderPort = createOrderPort;
  }

  @Override
  public Mono<OrderV01> getOrderById(String orderId) {
    return createOrderPort.getOrderById(orderId);
  }

  @Override
  public Mono<OrderV01> getOrderByIdAndStatus(String orderId, String status) {
    return createOrderPort.getOrderByIdAndStatus(orderId, status);
  }
}

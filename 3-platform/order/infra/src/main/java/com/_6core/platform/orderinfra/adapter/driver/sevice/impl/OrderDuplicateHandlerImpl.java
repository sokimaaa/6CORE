package com._6core.platform.orderinfra.adapter.driver.sevice.impl;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderapp.validator.OrderDuplicateHandler;
import com._6core.platform.orderinfra.adapter.driven.persistence.entity.OrderEntity;
import com._6core.platform.orderinfra.adapter.driven.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class OrderDuplicateHandlerImpl implements OrderDuplicateHandler {

    private final OrderRepository orderRepository;

    @Override
    public Boolean isDublicate(OrderV01 order) {
        Mono<OrderEntity> orderById = orderRepository.getOrderById(order.orderId());
        return null;
    }
}

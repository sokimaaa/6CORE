package com._6core.platform.orderapp.service.check.strategy.duplicate;

import com._6core.platform.orderapp.port.out.persistence.OrderRepository;
import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.duplicate.strategy.OrderDuplicateStrategy;

public class OrderIDDuplicateStrategy implements OrderDuplicateStrategy<OrderRequest> {
    private final OrderRepository orderRepository;

    public OrderIDDuplicateStrategy(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean isDuplicate(OrderRequest request) {
        return orderRepository.getOrderById(request.orderId()) != null;
    }
}

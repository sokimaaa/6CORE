package com._6core.platform.orderapp.service.check.strategy.correctness;

import com._6core.platform.orderdomain.service.correctness.strategy.OrderCorrectnessStrategy;

public class OrderItemsCorrect implements OrderCorrectnessStrategy {
    @Override
    public boolean isCorrect(Object request) {
        return false;
    }
}

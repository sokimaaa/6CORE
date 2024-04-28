package com._6core.platform.orderapp.service.check.strategy.correctness;

import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.correctness.strategy.OrderCorrectnessStrategy;

public class OrderTotalCorrect implements OrderCorrectnessStrategy<OrderRequest> {
    @Override
    public boolean isCorrect(OrderRequest request) {
        // перевірка на заповнення
        return false;
    }
}

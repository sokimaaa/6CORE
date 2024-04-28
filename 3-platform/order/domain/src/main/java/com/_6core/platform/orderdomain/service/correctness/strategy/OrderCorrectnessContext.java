package com._6core.platform.orderdomain.service.correctness.strategy;

public class OrderCorrectnessContext<T> {
    private OrderCorrectnessStrategy<T> strategy;

    public void setStrategy(OrderCorrectnessStrategy<T> strategy) {
        this.strategy = strategy;
    }
    public boolean executeStrategy(T request) {
        return strategy.isCorrect(request);
    }
}

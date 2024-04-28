package com._6core.platform.orderdomain.service.correctness.strategy;

public interface OrderCorrectnessStrategy<T> {
    boolean isCorrect(T request);
}

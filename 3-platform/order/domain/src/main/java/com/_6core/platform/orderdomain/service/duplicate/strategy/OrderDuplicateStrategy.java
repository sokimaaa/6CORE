package com._6core.platform.orderdomain.service.duplicate.strategy;

public interface OrderDuplicateStrategy<T> {
    boolean isDuplicate(T request);
}


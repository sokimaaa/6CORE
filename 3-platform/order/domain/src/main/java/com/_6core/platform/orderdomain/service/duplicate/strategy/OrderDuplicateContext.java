package com._6core.platform.orderdomain.service.duplicate.strategy;

public class OrderDuplicateContext<T> {
  private OrderDuplicateStrategy<T> strategy;

  public void setStrategy(OrderDuplicateStrategy<T> strategy) {
    this.strategy = strategy;
  }

  public boolean executeStrategy(T request) {
    return strategy.isDuplicate(request);
  }
}

package com._6core.platform.orderdomain.service.duplicate;

import com._6core.lib.hexagonal.annotations.DomainService;

@DomainService
public class DuplicateOrderContext<T> {
  private DuplicateOrderStrategy<T> strategy;

  public void setStrategy(DuplicateOrderStrategy<T> strategy) {
    this.strategy = strategy;
  }

  public boolean executeStrategy(T request) {
    return strategy.isDuplicate(request);
  }
}

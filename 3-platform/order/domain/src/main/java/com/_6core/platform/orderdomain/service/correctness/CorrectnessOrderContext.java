package com._6core.platform.orderdomain.service.correctness;

public class CorrectnessOrderContext<T> {
  private CorrectnessOrderStrategy<T> strategy;

  public void setStrategy(CorrectnessOrderStrategy<T> strategy) {
    this.strategy = strategy;
  }

  public boolean executeStrategy(T request) {
    return strategy.isCorrect(request);
  }
}

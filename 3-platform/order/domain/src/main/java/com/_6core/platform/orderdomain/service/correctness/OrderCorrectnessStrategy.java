package com._6core.platform.orderdomain.service.correctness;

public interface OrderCorrectnessStrategy<T> {
  boolean isCorrect(T request);
}

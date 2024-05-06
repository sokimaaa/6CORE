package com._6core.platform.orderdomain.service.correctness;

public interface CorrectnessOrderStrategy<T> {
  boolean isCorrect(T request);
}

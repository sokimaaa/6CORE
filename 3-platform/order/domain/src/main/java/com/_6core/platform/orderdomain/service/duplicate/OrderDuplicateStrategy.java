package com._6core.platform.orderdomain.service.duplicate;

public interface OrderDuplicateStrategy<T> {
  boolean isDuplicate(T request);
}

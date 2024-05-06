package com._6core.platform.orderdomain.service.duplicate;

public interface DuplicateOrderStrategy<T> {
  boolean isDuplicate(T request);
}

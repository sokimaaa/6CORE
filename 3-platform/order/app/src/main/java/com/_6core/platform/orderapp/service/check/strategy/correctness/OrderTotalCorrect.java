package com._6core.platform.orderapp.service.check.strategy.correctness;

import com._6core.platform.orderdomain.model.OrderItemRequest;
import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.correctness.strategy.OrderCorrectnessStrategy;
import java.math.BigInteger;

public class OrderTotalCorrect implements OrderCorrectnessStrategy<OrderRequest> {
  @Override
  public boolean isCorrect(OrderRequest request) {
    BigInteger summa = BigInteger.ZERO;
    for (OrderItemRequest item : request.orderItems()) {
      summa = summa.add(item.price());
    }
    return request.total().equals(summa);
  }
}

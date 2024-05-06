package com._6core.platform.orderdomain.service.correctness;

import com._6core.lib.hexagonal.annotations.DomainService;
import com._6core.platform.orderdomain.dto.OrderItemRequest;
import com._6core.platform.orderdomain.dto.OrderRequest;
import java.math.BigInteger;

@DomainService
public class TotalCorrectOrderStrategy implements CorrectnessOrderStrategy<OrderRequest> {
  @Override
  public boolean isCorrect(OrderRequest request) {
    BigInteger summa = BigInteger.ZERO;
    for (OrderItemRequest item : request.orderItems()) {
      summa = summa.add(item.price());
    }
    return request.total().equals(summa);
  }
}

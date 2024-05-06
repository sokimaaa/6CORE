package com._6core.platform.orderdomain.service.correctness;

import com._6core.lib.hexagonal.annotations.DomainService;
import com._6core.platform.orderdomain.dto.OrderRequest;
import java.math.BigInteger;
import java.util.Objects;

@DomainService
public class ItemsCorrectOrderStrategy implements CorrectnessOrderStrategy<OrderRequest> {
  @Override
  public boolean isCorrect(OrderRequest request) {
    return request.orderItems().stream()
        .anyMatch(
            item ->
                !item.itemId().isEmpty()
                    && !item.productId().isEmpty()
                    && !Objects.equals(item.price(), BigInteger.ZERO)
                    && item.quantity() != 0);
  }
}

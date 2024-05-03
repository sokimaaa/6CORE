package com._6core.platform.orderdomain.service.correctness;

import com._6core.platform.orderdomain.model.OrderRequest;
import java.math.BigInteger;
import java.util.Objects;

public class OrderItemsCorrect implements OrderCorrectnessStrategy<OrderRequest> {
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

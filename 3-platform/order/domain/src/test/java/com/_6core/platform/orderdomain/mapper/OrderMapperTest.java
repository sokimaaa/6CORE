package com._6core.platform.orderdomain.mapper;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderdomain.dto.OrderItemRequest;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.dto.OrderResponse;
import java.math.BigInteger;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class OrderMapperTest {

  private OrderMapper mapper;
  private OrderRequest request;

  @Before
  public void setup() {
    OrderItemRequest item1 =
        new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100), "order123");
    OrderItemRequest item2 =
        new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150), "order123");
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    request = new OrderRequest("order123", "pending", BigInteger.valueOf(250), orderItems);
  }

  @Test
  public void testMapToOrderResponse() {

    OrderResponse response = mapper.INSTANCE.mapToResponseDto(request);

    Assertions.assertEquals(request.orderId(), response.orderId());
    Assertions.assertEquals(request.status(), response.status());
    Assertions.assertEquals(request.total(), response.total());
    Assertions.assertEquals(request.orderItems().size(), response.orderItems().size());
  }

  @Test
  public void testMapToOrderV01() {
    OrderItemRequest item1 =
        new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100), "order123");
    OrderItemRequest item2 =
        new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150), "order123");
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    OrderRequest request =
        new OrderRequest("order123", "pending", BigInteger.valueOf(250), orderItems);

    ImmutableOrderV01Impl order = mapper.INSTANCE.mapToObject(request);

    Assertions.assertEquals("order123", order.orderId());
    Assertions.assertEquals("pending", order.status());
    Assertions.assertEquals(BigInteger.valueOf(250), order.total());
    Assertions.assertEquals(2, order.orderItems().size());
  }
}

package com._6core.platform.orderdomain.mapper;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderItemV01Impl;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderdomain.dto.OrderItemRequest;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.dto.OrderResponse;
import java.math.BigInteger;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class OrderMapperTest {
  private OrderMapper mapper;
  private OrderRequest request;
  private ImmutableOrderV01Impl.Builder orderBuilder;

  @BeforeEach
  public void setup() {
    OrderItemRequest item1 =
        new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100), "order123");
    OrderItemRequest item2 =
        new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150), "order123");
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    request = new OrderRequest("order123", "pending", BigInteger.valueOf(250), orderItems);

    orderBuilder = ImmutableOrderV01Impl.builder();
    orderBuilder.orderId(request.orderId());
    orderBuilder.status(request.status());
    orderBuilder.total(request.total());

    for (OrderItemRequest item : orderItems) {
      ImmutableOrderItemV01Impl.Builder itemBuilder = ImmutableOrderItemV01Impl.builder();
      itemBuilder.orderId(request.orderId());
      itemBuilder.price(item.price());
      itemBuilder.quantity(item.quantity());
      itemBuilder.itemId(item.itemId());
      itemBuilder.productId(item.productId());
      orderBuilder.addOrderItems(itemBuilder.build());
    }


  }


  @Test
  public void MapToOrderResponse_ok() {
    ImmutableOrderV01Impl order = orderBuilder.build();
    Mono<ImmutableOrderV01Impl> orderMono = Mono.just(order);

    mapper.INSTANCE.mapToResponseDto(orderMono)
            .log()
            .as(StepVerifier::create)
            .consumeNextWith(response -> {
              Assertions.assertEquals(response.orderId(),order.orderId());
              Assertions.assertEquals(response.status(),order.status());
              Assertions.assertEquals(response.total(),order.total());
            })
            .verifyComplete();
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

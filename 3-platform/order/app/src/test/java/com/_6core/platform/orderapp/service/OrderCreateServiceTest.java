package com._6core.platform.orderapp.service;

import static org.mockito.Mockito.when;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderItemV01Impl;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderapp.port.out.persistence.OrderRepository;
import com._6core.platform.orderdomain.dto.OrderItemRequest;
import com._6core.platform.orderdomain.dto.OrderItemResponse;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.dto.OrderResponse;
import com._6core.platform.orderdomain.mapper.OrderMapper;
import com._6core.platform.orderdomain.service.correctness.OrderItemsCorrect;
import com._6core.platform.orderdomain.service.correctness.OrderTotalCorrect;
import com._6core.platform.orderdomain.service.duplicate.OrderIDDuplicateStrategy;
import com._6core.platform.orderdomain.service.duplicate.StatusDuplicateStrategy;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;
import java.math.BigInteger;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class OrderCreateServiceTest {
  @Mock private OrderIDDuplicateStrategy orderIdDuplicateStrategy;

  @Mock private StatusDuplicateStrategy statusDuplicateStrategy;

  @Mock private OrderTotalCorrect orderTotalCorrect;

  @Mock private OrderItemsCorrect orderItemsCorrect;

  @Mock private OrderRepository orderRepository;

  @Mock private OrderHelperService orderHelperService;

  @Mock private OrderMapper mapper;
  @InjectMocks private OrderCreateService orderCreateService;
  private OrderResponse response;
  private OrderRequest request;
  private ImmutableOrderV01Impl.Builder orderBuilder;

  @Before
  public void setup() {
    OrderItemRequest item1 =
        new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100), "order123");
    OrderItemRequest item2 =
        new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150), "order123");
    OrderItemResponse item3 =
        new OrderItemResponse("item1", "2", 10, BigInteger.valueOf(100), "order123");
    OrderItemResponse item4 =
        new OrderItemResponse("item2", "1", 15, BigInteger.valueOf(150), "order123");
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    Set<OrderItemResponse> orderItemsResponse = Set.of(item3, item4);
    response = new OrderResponse("order123", "new", BigInteger.valueOf(250), orderItemsResponse);
    request = new OrderRequest("order123", "new", BigInteger.valueOf(250), orderItems);
    orderBuilder = ImmutableOrderV01Impl.builder();
    orderBuilder.orderId(response.orderId());
    orderBuilder.status(response.status());
    orderBuilder.total(response.total());

    for (OrderItemRequest item : orderItems) {
      ImmutableOrderItemV01Impl.Builder itemBuilder = ImmutableOrderItemV01Impl.builder();
      itemBuilder.orderId(response.orderId());
      itemBuilder.price(item.price());
      itemBuilder.quantity(item.quantity());
      itemBuilder.itemId(item.itemId());
      itemBuilder.productId(item.productId());
      orderBuilder.addOrderItems(itemBuilder.build());
    }

    MockitoAnnotations.initMocks(this);
    orderCreateService =
        new OrderCreateService(
            orderIdDuplicateStrategy,
            statusDuplicateStrategy,
            orderTotalCorrect,
            orderItemsCorrect,
            orderRepository,
            mapper);
  }

  @Test
  public void testCreateOrderSuccess() {
    ImmutableOrderV01Impl order = orderBuilder.build();
    when(mapper.mapToObject(request)).thenReturn(order);
    when(orderHelperService.getOrderById(request.orderId())).thenReturn(Mono.just(order));
    when(orderIdDuplicateStrategy.isDuplicate(request)).thenReturn(false);
    when(statusDuplicateStrategy.isDuplicate(request)).thenReturn(false);
    when(orderItemsCorrect.isCorrect(request)).thenReturn(true);
    when(orderTotalCorrect.isCorrect(request)).thenReturn(true);
    when(orderRepository.createOrder(request)).thenReturn(Mono.just(order));

    orderCreateService
        .createOrder(request)
        .log()
        .as(StepVerifier::create)
        .consumeNextWith(
            orderBuilder -> {
              Assertions.assertEquals(response.orderId(), order.orderId());
              Assertions.assertEquals(response.total(), order.total());
              Assertions.assertNotNull(response.status(), order.status());
              Assertions.assertEquals(response.orderItems().size(), order.orderItems().size());
            })
        .verifyComplete();
  }
}

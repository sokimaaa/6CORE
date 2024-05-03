package com._6core.platform.orderapp.service;

import static org.mockito.Mockito.when;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderItemV01Impl;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderapp.port.out.persistence.OrderRepository;
import com._6core.platform.orderdomain.mapper.OrderMapper;
import com._6core.platform.orderdomain.model.OrderItemRequest;
import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.correctness.OrderItemsCorrect;
import com._6core.platform.orderdomain.service.correctness.OrderTotalCorrect;
import com._6core.platform.orderdomain.service.duplicate.OrderIDDuplicateStrategy;
import com._6core.platform.orderdomain.service.duplicate.UserIDDuplicateStrategy;
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

  @Mock private UserIDDuplicateStrategy userIdDuplicateStrategy;

  @Mock private OrderTotalCorrect orderTotalCorrect;

  @Mock private OrderItemsCorrect orderItemsCorrect;

  @Mock private OrderRepository orderRepository;

  @Mock private OrderHelperService orderHelperService;

  @Mock private OrderMapper mapper;
  @InjectMocks private OrderCreateService orderCreateService;
  private OrderRequest request;
  private ImmutableOrderV01Impl.Builder orderBuilder;
  private ImmutableOrderItemV01Impl.Builder itemBuilder;

  @Before
  public void setup() {
    OrderItemRequest item1 = new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100));
    OrderItemRequest item2 = new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150));
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    request =
        new OrderRequest("order123", "pending", BigInteger.valueOf(250), orderItems, "user456");
    orderBuilder = ImmutableOrderV01Impl.builder();
    orderBuilder.orderId(request.orderId());
    orderBuilder.status(request.status());
    orderBuilder.total(request.total());

    for (OrderItemRequest item : orderItems) {
      itemBuilder = ImmutableOrderItemV01Impl.builder();
      itemBuilder.orderId(request.orderId());
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
            userIdDuplicateStrategy,
            orderTotalCorrect,
            orderItemsCorrect,
            orderRepository,
            mapper);
  }

  @Test
  public void testCreateOrderSuccess() {
    ImmutableOrderV01Impl order = orderBuilder.build();
    when(mapper.mapToOrderRequest(order)).thenReturn(request);
    when(mapper.mapToOrderV01(request)).thenReturn(order);
    when(orderHelperService.getOrderById(request.orderId())).thenReturn(Mono.just(order));
    when(orderIdDuplicateStrategy.isDuplicate(request)).thenReturn(false);
    when(userIdDuplicateStrategy.isDuplicate(request)).thenReturn(false);
    when(orderItemsCorrect.isCorrect(request)).thenReturn(true);
    when(orderTotalCorrect.isCorrect(request)).thenReturn(true);
    when(orderRepository.createOrder(order)).thenReturn(Mono.just(order));

    orderCreateService
        .createOrder(request)
        .log()
        .as(StepVerifier::create)
        .consumeNextWith(
            orderBuilder -> {
              Assertions.assertEquals(request.orderId(), order.orderId());
              Assertions.assertEquals(request.total(), order.total());
              Assertions.assertNotNull(request.status(), order.status());
              Assertions.assertEquals(request.orderItems().size(), order.orderItems().size());
            })
        .verifyComplete();
  }
}

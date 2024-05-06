package com._6core.platform.orderapp.service;

import static org.mockito.Mockito.when;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderItemV01Impl;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderapp.port.out.persistence.CreateOrderPort;
import com._6core.platform.orderdomain.dto.OrderItemRequest;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.service.correctness.OrderItemsCorrect;
import com._6core.platform.orderdomain.service.correctness.OrderTotalCorrect;
import com._6core.platform.orderdomain.service.duplicate.OrderDuplicateContext;
import com._6core.platform.orderdomain.service.duplicate.OrderIDDuplicateStrategy;
import com._6core.platform.orderdomain.service.duplicate.StatusDuplicateStrategy;
import com._6core.platform.orderdomain.service.helper.OrderHelperService;
import java.math.BigInteger;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

  @Mock private CreateOrderPort createOrderPort;

  @Mock private OrderHelperService orderHelperService;
  @InjectMocks private OrderCreateService orderCreateService;
  private OrderRequest request;
  private ImmutableOrderV01Impl.Builder orderBuilder;

  @BeforeEach
  public void setup() {
    OrderItemRequest item1 =
        new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100), "order123");
    OrderItemRequest item2 =
        new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150), "order123");
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    request = new OrderRequest("order123", "new", BigInteger.valueOf(250), orderItems);
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

    MockitoAnnotations.initMocks(this);
    orderCreateService =
        new OrderCreateService(
            orderIdDuplicateStrategy,
            statusDuplicateStrategy,
            orderTotalCorrect,
            orderItemsCorrect,
            createOrderPort);
  }

  @Test
  public void createOrder_success() {
    ImmutableOrderV01Impl order = orderBuilder.build();
    when(orderHelperService.getOrderById(request.orderId())).thenReturn(Mono.just(order));
    when(orderIdDuplicateStrategy.isDuplicate(request)).thenReturn(false);
    when(statusDuplicateStrategy.isDuplicate(request)).thenReturn(false);
    when(orderItemsCorrect.isCorrect(request)).thenReturn(true);
    when(orderTotalCorrect.isCorrect(request)).thenReturn(true);
    when(createOrderPort.createOrder(request)).thenReturn(Mono.just(order));

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

  @Test
  public void orderIdDuplicate_true() {
    when(orderHelperService.getOrderById(request.orderId()))
        .thenReturn(Mono.just(orderBuilder.build()));
    OrderIDDuplicateStrategy strategy = new OrderIDDuplicateStrategy(orderHelperService);

    OrderDuplicateContext<OrderRequest> context = new OrderDuplicateContext<>();
    context.setStrategy(strategy);
    Assertions.assertTrue(context.executeStrategy(request));
  }

  @Test
  public void orderIdDuplicate_false() {
    OrderItemRequest item1 =
        new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100), "order123");
    OrderItemRequest item2 =
        new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150), "order123");
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    OrderRequest request1 =
        new OrderRequest("order345", "new", BigInteger.valueOf(250), orderItems);

    when(orderHelperService.getOrderById(request1.orderId())).thenReturn(Mono.empty());
    OrderIDDuplicateStrategy strategy = new OrderIDDuplicateStrategy(orderHelperService);

    OrderDuplicateContext<OrderRequest> context = new OrderDuplicateContext<>();
    context.setStrategy(strategy);

    Assertions.assertFalse(context.executeStrategy(request1));
  }

  @Test
  public void statusAndIdDuplicate_true() {
    OrderItemRequest item1 =
        new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100), "order123");
    OrderItemRequest item2 =
        new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150), "order123");
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    OrderRequest order = new OrderRequest("order345", "new", BigInteger.valueOf(250), orderItems);

    orderBuilder = ImmutableOrderV01Impl.builder();
    orderBuilder.orderId(order.orderId());
    orderBuilder.status(order.status());
    orderBuilder.total(order.total());

    for (OrderItemRequest item : orderItems) {
      ImmutableOrderItemV01Impl.Builder itemBuilder = ImmutableOrderItemV01Impl.builder();
      itemBuilder.orderId(order.orderId());
      itemBuilder.price(item.price());
      itemBuilder.quantity(item.quantity());
      itemBuilder.itemId(item.itemId());
      itemBuilder.productId(item.productId());
      orderBuilder.addOrderItems(itemBuilder.build());
    }

    when(orderHelperService.getOrderByIdAndStatus(order.orderId(), order.status()))
        .thenReturn(Mono.just(orderBuilder.build()));

    StatusDuplicateStrategy strategy = new StatusDuplicateStrategy(orderHelperService);

    OrderDuplicateContext<OrderRequest> context = new OrderDuplicateContext<>();
    context.setStrategy(strategy);

    Assertions.assertTrue(strategy.isDuplicate(order));
  }

  @Test
  public void statusAndIdDuplicate_False() {
    OrderItemRequest item1 =
        new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100), "order123");
    OrderItemRequest item2 =
        new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150), "order123");
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    OrderRequest order = new OrderRequest("order345", "new", BigInteger.valueOf(250), orderItems);

    when(orderHelperService.getOrderByIdAndStatus(order.orderId(), order.status()))
        .thenReturn(Mono.empty());

    StatusDuplicateStrategy strategy = new StatusDuplicateStrategy(orderHelperService);

    OrderDuplicateContext<OrderRequest> context = new OrderDuplicateContext<>();
    context.setStrategy(strategy);

    Assertions.assertFalse(strategy.isDuplicate(order));
  }
}

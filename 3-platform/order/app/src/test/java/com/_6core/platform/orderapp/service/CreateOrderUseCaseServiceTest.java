package com._6core.platform.orderapp.service;

import static org.mockito.Mockito.when;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderItemV01Impl;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderapp.port.out.persistence.CreateOrderPersistencePort;
import com._6core.platform.orderdomain.dto.OrderItemRequest;
import com._6core.platform.orderdomain.dto.OrderItemResponse;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.dto.OrderResponse;
import com._6core.platform.orderdomain.mapper.OrderMapper;
import com._6core.platform.orderdomain.service.correctness.ItemsCorrectOrderStrategy;
import com._6core.platform.orderdomain.service.correctness.TotalCorrectOrderStrategy;
import com._6core.platform.orderdomain.service.duplicate.DuplicateOrderContext;
import com._6core.platform.orderdomain.service.duplicate.IDDuplicateOrderStrategy;
import com._6core.platform.orderdomain.service.duplicate.StatusDuplicateOrderStrategy;
import com._6core.platform.orderdomain.service.helper.CreateOrderHelperService;
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

public class CreateOrderUseCaseServiceTest {
  @Mock private IDDuplicateOrderStrategy orderIdDuplicateStrategy;

  @Mock private StatusDuplicateOrderStrategy statusDuplicateStrategy;

  @Mock private TotalCorrectOrderStrategy orderTotalCorrect;

  @Mock private ItemsCorrectOrderStrategy orderItemsCorrect;

  @Mock private CreateOrderPersistencePort createOrderPersistencePort;

  @Mock private CreateOrderHelperService createOrderHelperService;
  @Mock private OrderMapper mapper;
  @InjectMocks private CreateOrderUseCaseService orderCreateUseCaseService;
  private OrderRequest request;
  private ImmutableOrderV01Impl.Builder orderBuilder;
  private OrderResponse response;

  @BeforeEach
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
    request = new OrderRequest("order123", "new", BigInteger.valueOf(250), orderItems);
    response = new OrderResponse("order123", "new", BigInteger.valueOf(250), orderItemsResponse);
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
    orderCreateUseCaseService =
        new CreateOrderUseCaseService(
            orderIdDuplicateStrategy,
            statusDuplicateStrategy,
            orderTotalCorrect,
            orderItemsCorrect,
            createOrderPersistencePort,
            mapper);
  }

  @Test
  public void createOrder_success() {
    ImmutableOrderV01Impl order = orderBuilder.build();
    when(mapper.mapToResponseDto(order)).thenReturn(response);
    when(createOrderHelperService.getOrderById(request.orderId())).thenReturn(Mono.just(order));
    when(orderIdDuplicateStrategy.isDuplicate(request)).thenReturn(false);
    when(statusDuplicateStrategy.isDuplicate(request)).thenReturn(false);
    when(orderItemsCorrect.isCorrect(request)).thenReturn(true);
    when(orderTotalCorrect.isCorrect(request)).thenReturn(true);
    when(createOrderPersistencePort.createOrder(request)).thenReturn(Mono.just(order));

    orderCreateUseCaseService
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

  @Test
  public void orderIdDuplicate_true() {
    when(createOrderHelperService.getOrderById(request.orderId()))
        .thenReturn(Mono.just(orderBuilder.build()));
    IDDuplicateOrderStrategy strategy = new IDDuplicateOrderStrategy(createOrderHelperService);

    DuplicateOrderContext<OrderRequest> context = new DuplicateOrderContext<>();
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

    when(createOrderHelperService.getOrderById(request1.orderId())).thenReturn(Mono.empty());
    IDDuplicateOrderStrategy strategy = new IDDuplicateOrderStrategy(createOrderHelperService);

    DuplicateOrderContext<OrderRequest> context = new DuplicateOrderContext<>();
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

    when(createOrderHelperService.getOrderByIdAndStatus(order.orderId(), order.status()))
        .thenReturn(Mono.just(orderBuilder.build()));

    StatusDuplicateOrderStrategy strategy = new StatusDuplicateOrderStrategy(createOrderHelperService);

    DuplicateOrderContext<OrderRequest> context = new DuplicateOrderContext<>();
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

    when(createOrderHelperService.getOrderByIdAndStatus(order.orderId(), order.status()))
        .thenReturn(Mono.empty());

    StatusDuplicateOrderStrategy strategy = new StatusDuplicateOrderStrategy(createOrderHelperService);

    DuplicateOrderContext<OrderRequest> context = new DuplicateOrderContext<>();
    context.setStrategy(strategy);

    Assertions.assertFalse(strategy.isDuplicate(order));
  }
}

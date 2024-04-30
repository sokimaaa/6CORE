package com._6core.platform.orderapp.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com._6core.platform.orderapp.port.out.persistence.OrderRepository;
import com._6core.platform.orderdomain.mapper.OrderMapper;
import com._6core.platform.orderdomain.model.OrderItemRequest;
import com._6core.platform.orderdomain.model.OrderRequest;
import com._6core.platform.orderdomain.service.correctness.OrderCorrectnessContext;
import com._6core.platform.orderdomain.service.correctness.OrderCorrectnessStrategy;
import com._6core.platform.orderdomain.service.duplicate.OrderDuplicateContext;
import com._6core.platform.orderdomain.service.duplicate.OrderDuplicateStrategy;
import java.math.BigInteger;
import java.util.Set;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

public class OrderCreateServiceTest {
  @Mock private OrderDuplicateStrategy<OrderRequest> orderIdDuplicateStrategy;

  @Mock private OrderDuplicateStrategy<OrderRequest> userIdDuplicateStrategy;

  @Mock private OrderDuplicateContext<OrderRequest> duplicateContext;

  @Mock private OrderCorrectnessStrategy<OrderRequest> orderTotalCorrect;

  @Mock private OrderCorrectnessStrategy<OrderRequest> orderItemsCorrect;

  @Mock private OrderCorrectnessContext<OrderRequest> correctnessContext;

  @Mock private OrderRepository orderRepository;

  @Mock private OrderMapper mapper;

  private OrderCreateService orderCreateService;

  @BeforeEach
  void setUp() {

    MockitoAnnotations.openMocks(this);
    orderCreateService =
        new OrderCreateService(
            orderIdDuplicateStrategy,
            userIdDuplicateStrategy,
            duplicateContext,
            orderTotalCorrect,
            orderItemsCorrect,
            correctnessContext,
            orderRepository,
            mapper);
  }

  @Test
  public void testCreateOrderDuplicate() {
    OrderItemRequest item1 = new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100));
    OrderItemRequest item2 = new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150));
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    OrderRequest request =
        new OrderRequest("order123", "pending", BigInteger.valueOf(100), orderItems, "user456");
    when(mapper.mapToOrderRequest(any())).thenReturn(request);
    when(orderIdDuplicateStrategy.isDuplicate(any())).thenReturn(Mono.just(true));
    when(userIdDuplicateStrategy.isDuplicate(any())).thenReturn(Mono.just(false));
  }

  @Test
  public void testCreateOrderInvalidData() {
    OrderItemRequest item1 = new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100));
    OrderItemRequest item2 = new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150));
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    OrderRequest request =
        new OrderRequest("order123", "pending", BigInteger.valueOf(100), orderItems, "user456");
    when(mapper.mapToOrderRequest(any())).thenReturn(request);
    when(orderIdDuplicateStrategy.isDuplicate(any())).thenReturn(Mono.just(false));
    when(userIdDuplicateStrategy.isDuplicate(any())).thenReturn(Mono.just(false));
    when(orderItemsCorrect.isCorrect(any())).thenReturn(true);
    when(orderTotalCorrect.isCorrect(any())).thenReturn(false);
  }

  @Test
  public void testCreateOrderSuccess() {
    OrderItemRequest item1 = new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100));
    OrderItemRequest item2 = new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150));
    Set<OrderItemRequest> orderItems = Set.of(item1, item2);
    OrderRequest request =
        new OrderRequest("order123", "pending", BigInteger.valueOf(100), orderItems, "user456");
    when(mapper.mapToOrderRequest(any())).thenReturn(request);
    when(orderIdDuplicateStrategy.isDuplicate(any())).thenReturn(Mono.just(false));
    when(userIdDuplicateStrategy.isDuplicate(any())).thenReturn(Mono.just(false));
    when(orderItemsCorrect.isCorrect(any())).thenReturn(true);
    when(orderTotalCorrect.isCorrect(any())).thenReturn(true);
  }
}

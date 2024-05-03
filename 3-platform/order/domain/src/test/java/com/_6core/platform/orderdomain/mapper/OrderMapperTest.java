package com._6core.platform.orderdomain.mapper;

import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderItemV01Impl;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderdomain.model.OrderItemRequest;
import com._6core.platform.orderdomain.model.OrderRequest;
import org.junit.Test;
import java.math.BigInteger;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import reactor.core.publisher.Mono;

public class OrderMapperTest {
    private final OrderMapper mapper = new OrderMapperImpl();
    @Test
    public void testMapToOrderRequest() {
        ImmutableOrderV01Impl order = ImmutableOrderV01Impl.builder()
                .orderId("order123")
                .status("pending")
                .total(BigInteger.valueOf(100))
                .addOrderItems(
                        ImmutableOrderItemV01Impl.builder()
                                .orderId("order123")
                                .itemId("item1")
                                .productId("1")
                                .quantity(10)
                                .price(BigInteger.valueOf(100))
                                .build()
                )
                .build();

        OrderRequest request = mapper.mapToOrderRequest(order);

        Assertions.assertEquals("order123", request.orderId());
        Assertions.assertEquals("pending", request.status());
        Assertions.assertEquals(BigInteger.valueOf(100), request.total());
        Assertions.assertEquals(1, request.orderItems().size());
    }

    @Test
    public void testMapToOrderV01() {
        OrderItemRequest item1 = new OrderItemRequest("item1", "2", 10, BigInteger.valueOf(100));
        OrderItemRequest item2 = new OrderItemRequest("item2", "1", 15, BigInteger.valueOf(150));
        Set<OrderItemRequest> orderItems = Set.of(item1, item2);
        OrderRequest request =
                new OrderRequest("order123", "pending", BigInteger.valueOf(250), orderItems, "user456");

        ImmutableOrderV01Impl order = mapper.mapToOrderV01(request);

        Assertions.assertEquals("order123", order.orderId());
        Assertions.assertEquals("pending", order.status());
        Assertions.assertEquals(BigInteger.valueOf(250), order.total());
        Assertions.assertEquals(2, order.orderItems().size());
    }

}
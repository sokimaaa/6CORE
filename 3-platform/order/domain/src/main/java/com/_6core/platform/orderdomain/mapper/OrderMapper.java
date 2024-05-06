package com._6core.platform.orderdomain.mapper;

import com._6core.lib.java.domain.model.order.OrderItemV01;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderItemV01Impl;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderdomain.dto.OrderItemRequest;
import com._6core.platform.orderdomain.dto.OrderItemResponse;
import com._6core.platform.orderdomain.dto.OrderRequest;
import com._6core.platform.orderdomain.dto.OrderResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import reactor.core.publisher.Mono;

@Mapper
public interface OrderMapper {

  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  OrderResponse mapToResponseDto(ImmutableOrderV01Impl order);

  default Mono<OrderResponse> mapToResponseDto(Mono<ImmutableOrderV01Impl> orderMono) {

    return orderMono
        .map(this::mapToResponseDto)
        .defaultIfEmpty(new OrderResponse("", "", BigInteger.ZERO, null));
  }

  @Mapping(target = "orderItems", source = "orderItems", qualifiedByName = "mapOrderItemRequests")
  ImmutableOrderV01Impl mapToObject(OrderRequest request);

  @Named("mapOrderItemRequests")
  default Iterable<? extends OrderItemV01> mapOrderItemRequests(
      Set<OrderItemRequest> orderItemRequests) {
    List<OrderItemV01> orderItems = new ArrayList<>();
    for (OrderItemRequest itemRequest : orderItemRequests) {
      ImmutableOrderItemV01Impl item =
          ImmutableOrderItemV01Impl.builder()
              .orderId(itemRequest.orderId())
              .itemId(itemRequest.itemId())
              .quantity(itemRequest.quantity())
              .productId(itemRequest.productId())
              .price(itemRequest.price())
              .build();
      orderItems.add(item);
    }
    return orderItems;
  }

  @Named("mapToOrderItemResponse")
  default Set<OrderItemResponse> mapToOrderItemResponse(Set<OrderItemV01> orderItems) {
    Set<OrderItemResponse> orderItemResponses = new HashSet<>();
    for (OrderItemV01 orderItem : orderItems) {
      OrderItemResponse item =
          new OrderItemResponse(
              orderItem.getOrderId(),
              orderItem.getItemId(),
              orderItem.getQuantity(),
              orderItem.getPrice(),
              orderItem.getProductId());
      orderItemResponses.add(item);
    }
    return orderItemResponses;
  }
}

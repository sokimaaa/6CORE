package com._6core.platform.orderdomain.mapper;

import com._6core.lib.java.domain.model.order.OrderItemV01;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderItemV01Impl;
import com._6core.lib.java.domain.model.order.immutable.ImmutableOrderV01Impl;
import com._6core.platform.orderdomain.dto.OrderItemRequest;
import com._6core.platform.orderdomain.dto.OrderRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

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
}

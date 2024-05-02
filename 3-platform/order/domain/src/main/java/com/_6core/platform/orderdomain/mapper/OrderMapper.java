package com._6core.platform.orderdomain.mapper;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderdomain.model.OrderRequest;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
  OrderRequest mapToOrderRequest(OrderV01 order);

  OrderV01 mapToImmutableOrder(OrderRequest request);
}

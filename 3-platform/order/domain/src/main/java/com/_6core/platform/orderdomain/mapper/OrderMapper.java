package com._6core.platform.orderdomain.mapper;

import com._6core.lib.proto.domain.model.Order;
import com._6core.platform.orderdomain.model.OrderRequest;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
  OrderRequest mapToOrderRequest(Order orderProto);
}

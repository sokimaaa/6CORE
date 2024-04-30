package com._6core.platform.orderdomain.mapper;

import com._6core.lib.java.domain.model.order.OrderV01;
import com._6core.platform.orderdomain.model.OrderRequest;
import org.mapstruct.Mapper;

@Mapper
public interface  OrderMapper {
    OrderV01 mapToOrderV01(OrderRequest orderRequest);
}

package com._6core.platform.orderinfra.adapter.driver.sevice;

import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableProductV01Impl;
import com._6core.platform.orderdomain.model.ProductStatus;
import com._6core.platform.orderinfra.adapter.driven.persistence.entity.OrderEntity;
import reactor.core.publisher.Mono;

public interface OrderWarehouseService {
    Mono<ProductStatus> checkProducts();

    Mono<OrderEntity> putProductOnHold(ImmutableProductV01Impl product);
}

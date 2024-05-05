package com._6core.platform.orderapp.port.out;

import com._6core.platform.orderdomain.model.ProductStatus;
import reactor.core.publisher.Mono;

public interface WarehouseReservePort {
  Mono<ProductStatus> isAvailable(String productId, Integer quantity);
  Mono<ProductStatus> reserve(String productId, Integer quantity);
}

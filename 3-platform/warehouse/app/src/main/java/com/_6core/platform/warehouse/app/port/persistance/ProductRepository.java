package com._6core.platform.warehouse.app.port.persistance;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ProductEntity;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ProductRepository {
    Flux<ProductEntity> findProductsByIds(List<String> productIds);
}

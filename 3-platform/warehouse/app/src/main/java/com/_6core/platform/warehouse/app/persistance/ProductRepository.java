package com._6core.platform.warehouse.app.persistance;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ProductEntity;
import java.util.List;
import reactor.core.publisher.Flux;


public interface ProductRepository {
  Flux<ProductEntity> getProductsByIds(List<String> productsIds);
}

package com._6core.platform.warehouse.domain.persistance.out;

import com._6core.lib.java.domain.model.warehouse.immutable.ProductV01Impl;
import java.util.List;

import com._6core.platform.warehouse.domain.dto.ProductsIds;
import reactor.core.publisher.Flux;

public interface GetProductsPort {
  Flux<ProductV01Impl> getProductsByIds(ProductsIds productsIds);
}

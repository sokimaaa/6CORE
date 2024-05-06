package com._6core.platform.warehouse.domain.persistance.in;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.platform.warehouse.domain.dto.product.ProductsIds;
import reactor.core.publisher.Flux;

public interface GetProductsUseCase {
  Flux<ProductV01> getProductsByIds(ProductsIds productsIds);
}

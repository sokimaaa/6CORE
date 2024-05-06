package com._6core.platform.warehouse.domain.persistance.in;

import com._6core.platform.warehouse.domain.dto.product.ProductResponse;
import com._6core.platform.warehouse.domain.dto.product.ProductsIds;
import reactor.core.publisher.Flux;

public interface GetProductsUseCase {
  Flux<ProductResponse> getProductsByIds(ProductsIds productsIds);
}

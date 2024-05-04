package com._6core.platform.warehouse.domain.persistance.in;

import com._6core.platform.warehouse.domain.dto.ProductResponse;
import java.util.List;

import com._6core.platform.warehouse.domain.dto.ProductsIds;
import reactor.core.publisher.Flux;

public interface GetProductsUseCase {
  Flux<ProductResponse> getProductsByIds(ProductsIds productsIds);
}

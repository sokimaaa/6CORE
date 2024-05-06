package com._6core.platform.warehouse.app.usecase;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.platform.warehouse.domain.dto.product.ProductsIds;
import com._6core.platform.warehouse.domain.persistance.out.GetProductsPort;
import com._6core.platform.warehouse.domain.persistance.in.GetProductsUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetProductsService implements GetProductsUseCase {
  private final GetProductsPort getProductsPort;

  @Override
  public Flux<ProductV01> getProductsByIds(ProductsIds productsIds) {
    return getProductsPort.getProductsByIds(productsIds);
  }
}

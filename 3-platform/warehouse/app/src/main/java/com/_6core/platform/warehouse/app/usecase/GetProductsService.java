package com._6core.platform.warehouse.app.usecase;

import com._6core.platform.warehouse.domain.dto.product.ProductResponse;
import com._6core.platform.warehouse.domain.dto.product.ProductsIds;
import com._6core.platform.warehouse.domain.mapper.ProductMapper;
import com._6core.platform.warehouse.domain.persistance.in.GetProductsUseCase;
import com._6core.platform.warehouse.domain.persistance.out.GetProductsPort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetProductsService implements GetProductsUseCase {
  private final GetProductsPort getProductsPort;
  private final ProductMapper productMapper;

  @Override
  public Flux<ProductResponse> getProductsByIds(ProductsIds productsIds) {
    return getProductsPort.getProductsByIds(productsIds).map(productMapper::mapToProductResponse);
  }
}

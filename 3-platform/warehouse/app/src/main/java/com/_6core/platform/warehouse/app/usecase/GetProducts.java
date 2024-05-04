package com._6core.platform.warehouse.app.usecase;

import com._6core.platform.warehouse.domain.dto.ProductsIds;
import com._6core.platform.warehouse.domain.persistance.out.GetProductsPort;
import com._6core.platform.warehouse.domain.persistance.in.GetProductsUseCase;
import com._6core.platform.warehouse.domain.dto.ProductResponse;
import com._6core.platform.warehouse.domain.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetProducts implements GetProductsUseCase {
  private final GetProductsPort productRepository;
  private final ProductMapper productMapper;

  @Override
  public Flux<ProductResponse> getProductsByIds(ProductsIds productsIds) {
    return productRepository.getProductsByIds(productsIds).map(productMapper::toProductResponse);
  }
}

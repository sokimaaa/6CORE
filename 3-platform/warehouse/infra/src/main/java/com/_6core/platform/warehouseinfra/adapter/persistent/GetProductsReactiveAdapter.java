package com._6core.platform.warehouseinfra.adapter.persistent;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.platform.warehouse.domain.dto.product.ProductsIds;
import com._6core.platform.warehouse.domain.persistance.out.GetProductsPort;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ProductEntity;
import com._6core.platform.warehouseinfra.adapter.persistent.repository.ProductsReactiveRepository;
import com._6core.platform.warehouseinfra.mapper.ImmutableProduct2ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetProductsReactiveAdapter implements GetProductsPort {
  private final ProductsReactiveRepository productsReactiveRepository;
  private final ImmutableProduct2ProductEntity mapper = ImmutableProduct2ProductEntity.INSTANCE;

  @Override
  public Flux<ProductV01> getProductsByIds(ProductsIds productsIds) {
    Flux<ProductEntity> productsByIds =
        productsReactiveRepository.getProductsByIds(productsIds.productsIds());
    return productsByIds.map(mapper::map2Domain);
  }
}

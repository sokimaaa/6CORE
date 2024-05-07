package com._6core.platform.warehouseinfra.adapter.persistent.repository;

import com._6core.platform.warehouseinfra.adapter.driven.driven.persistence.entity.ProductEntity;
import java.util.List;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface ProductsReactiveRepository extends R2dbcRepository<ProductEntity, String> {

  @Query("SELECT * FROM ProductEntity p WHERE p.id IN (?1)")
  Flux<ProductEntity> getProductsByIds(List<String> ids);
}

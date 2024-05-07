package com._6core.platform.warehouseinfra.adapter.driven.persistent.repository;

import com._6core.platform.warehouseinfra.adapter.driven.persistent.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ProductsReactiveRepository extends R2dbcRepository<ProductEntity, String> {

    @Query("SELECT p FROM ProductEntity p WHERE p.id IN (:ids)")
    Flux<ProductEntity> getProductsByIds(List<String> ids);
}

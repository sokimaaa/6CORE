package com._6core.platform.warehouseinfra.adapter.driven.persistence.repository;

import com._6core.platform.warehouse.domain.dto.product.ProductsIds;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends R2dbcRepository<ProductEntity, String> {

    @Query("")
    Flux<ProductEntity> getProductsByIds(ProductsIds productsIds);

}

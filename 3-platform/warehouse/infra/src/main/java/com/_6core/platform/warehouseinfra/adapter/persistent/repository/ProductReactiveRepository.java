package com._6core.platform.warehouseinfra.adapter.persistent.repository;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReactiveRepository extends R2dbcRepository<ProductEntity, String> {}

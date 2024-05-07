package com._6core.platform.warehouseinfra.adapter.driven.persistent.repository;

import com._6core.platform.warehouseinfra.adapter.driven.persistent.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProductReactiveRepository extends R2dbcRepository<ProductEntity, String> {}

package com._6core.platform.warehouseinfra.adapter.driven.persistent.repository;

import com._6core.platform.warehouseinfra.adapter.driven.persistent.entity.WarehouseEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface WarehouseReactiveRepository extends R2dbcRepository<WarehouseEntity, String> {}

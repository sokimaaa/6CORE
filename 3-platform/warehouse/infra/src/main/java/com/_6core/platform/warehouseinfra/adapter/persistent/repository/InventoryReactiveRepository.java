package com._6core.platform.warehouseinfra.adapter.persistent.repository;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.InventoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface InventoryReactiveRepository extends R2dbcRepository<InventoryEntity, String> {
  @Query(
      "SELECT i FROM InventoryEntity i LEFT JOIN FETCH i.warehouse w LEFT JOIN i.product p WHERE p.id = ?1")
  Mono<InventoryEntity> findByProductId(String productId);
}

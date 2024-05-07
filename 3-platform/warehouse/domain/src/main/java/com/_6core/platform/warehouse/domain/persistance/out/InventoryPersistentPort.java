package com._6core.platform.warehouse.domain.persistance.out;

import com._6core.lib.java.domain.model.warehouse.InventoryV01;
import reactor.core.publisher.Mono;

public interface InventoryPersistentPort {
  Mono<InventoryV01> findByProductId(String productId);

  Mono<InventoryV01> update(InventoryV01 inventory);
}

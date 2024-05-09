package com._6core.platform.domain.port.out;

import com._6core.lib.hexagonal.annotations.PortOut;
import com._6core.lib.java.domain.model.warehouse.immutable.InventoryV01Impl;
import reactor.core.publisher.Mono;

@PortOut
public interface InventoryPersistentPort {
  Mono<InventoryV01Impl> findByProductId(String productId);

  Mono<InventoryV01Impl> update(InventoryV01Impl inventory);
}

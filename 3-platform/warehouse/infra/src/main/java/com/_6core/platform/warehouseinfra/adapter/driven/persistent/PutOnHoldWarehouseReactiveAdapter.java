package com._6core.platform.warehouseinfra.adapter.driven.persistent;

import com._6core.lib.java.domain.model.warehouse.InventoryV01;
import com._6core.platform.warehouse.domain.persistance.out.InventoryPersistentPort;
import com._6core.platform.warehouseinfra.adapter.driven.persistent.repository.InventoryReactiveRepository;
import com._6core.platform.warehouseinfra.mapper.ImmutableInventory2InventoryEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PutOnHoldWarehouseReactiveAdapter implements InventoryPersistentPort {
  private final InventoryReactiveRepository inventoryRepository;

  public PutOnHoldWarehouseReactiveAdapter(InventoryReactiveRepository inventoryRepository) {
    this.inventoryRepository = inventoryRepository;
  }

  @Override
  public Mono<InventoryV01> findByProductId(String productId) {
    return inventoryRepository
        .findByProductId(productId)
        .map(ImmutableInventory2InventoryEntity.INSTANCE::map2Domain);
  }

  @Override
  public Mono<InventoryV01> update(InventoryV01 inventory) {
    return inventoryRepository
        .save(ImmutableInventory2InventoryEntity.INSTANCE.map2Entity(inventory))
        .map(ImmutableInventory2InventoryEntity.INSTANCE::map2Domain);
  }
}

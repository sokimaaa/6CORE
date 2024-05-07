package com._6core.platform.warehouseinfra.mapper;

import com._6core.lib.java.domain.model.warehouse.InventoryV01;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableInventoryV01Impl;
import com._6core.platform.warehouseinfra.adapter.driven.persistent.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ImmutableProduct2ProductEntity.class, ImmutableWarehouse2WarehouseEntity.class})
public interface ImmutableInventory2InventoryEntity {
  ImmutableInventory2InventoryEntity INSTANCE =
      Mappers.getMapper(ImmutableInventory2InventoryEntity.class);

  @Mapping(target = "inventoryId", source = "id")
  ImmutableInventoryV01Impl map2Domain(InventoryEntity entity);

  @Mapping(target = "id", source = "inventoryId")
  InventoryEntity map2Entity(InventoryV01 domainInventory);
}

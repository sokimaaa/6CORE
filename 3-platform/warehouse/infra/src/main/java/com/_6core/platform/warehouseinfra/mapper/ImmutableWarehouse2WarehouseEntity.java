package com._6core.platform.warehouseinfra.mapper;

import com._6core.lib.java.domain.model.warehouse.WarehouseV01;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableWarehouseV01Impl;
import com._6core.platform.warehouseinfra.adapter.driven.persistent.entity.WarehouseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImmutableWarehouse2WarehouseEntity {
  ImmutableWarehouse2WarehouseEntity INSTANCE =
      Mappers.getMapper(ImmutableWarehouse2WarehouseEntity.class);

  @Mapping(target = "warehouseId", source = "id")
  ImmutableWarehouseV01Impl map2Domain(WarehouseEntity entity);

  @Mapping(target = "id", source = "warehouseId")
  WarehouseEntity map2Entity(WarehouseV01 doaminWarehouse);
}

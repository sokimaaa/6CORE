package com._6core.platform.warehouseinfra.mapper;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableProductV01Impl;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImmutableProduct2ProductEntity {
  ImmutableProduct2ProductEntity INSTANCE = Mappers.getMapper(ImmutableProduct2ProductEntity.class);

  @Mapping(target = "productId", source = "id")
  ImmutableProductV01Impl map2Domain(ProductEntity entity);

  @Mapping(target = "id", source = "productId")
  ProductEntity map2Entity(ProductV01 domainProduct);
}

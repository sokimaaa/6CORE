package com._6core.platform.warehouse.domain.mapper;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.platform.warehouse.domain.dto.product.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.reactivestreams.Publisher;

@Mapper
public interface ProductMapper {
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mapping(target = "name", expression = "java(productV01.name())")
  @Mapping(target = "description", expression = "java(productV01.description())")
  @Mapping(target = "image", expression = "java(productV01.image())")
  @Mapping(target = "price", expression = "java(productV01.price())")
  @Mapping(target = "category", expression = "java(productV01.category())")
  ProductResponse mapToProductResponse(ProductV01 productV01);
}

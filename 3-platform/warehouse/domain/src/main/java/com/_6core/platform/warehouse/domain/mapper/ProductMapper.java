package com._6core.platform.warehouse.domain.mapper;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.platform.warehouse.domain.dto.product.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  @Mapping(target = "id", source = "productId")
  ProductResponse mapToProductResponse(ProductV01 productV01);
}

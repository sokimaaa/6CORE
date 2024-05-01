package com._6core.platform.warehouseinfra.adapter.driven.persistence.mapper;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ProductEntity;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductResponse toProductResponse(ProductEntity product);
}

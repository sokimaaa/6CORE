package com._6core.platform.warehouse.domain.mapper;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ProductEntity;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.get.out.product.GetProductsResponse;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    GetProductsResponse toProductResponse(ProductEntity productEntity);
}

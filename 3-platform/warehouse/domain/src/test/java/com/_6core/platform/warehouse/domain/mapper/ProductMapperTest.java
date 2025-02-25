package com._6core.platform.warehouse.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableProductV01Impl;
import com._6core.platform.warehouse.domain.dto.product.ProductResponse;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

class ProductMapperTest {
  @Test
  public void mapToProductResponse_fullProductV01_validProductResponse() {
    ProductV01 productV01 =
        ImmutableProductV01Impl.builder()
            .productId("1")
            .name("Product 1")
            .description("Description 1")
            .price(BigInteger.valueOf(10))
            .image("image1.jpg")
            .category("Category 1")
            .build();
    ProductResponse productResponse = ProductMapper.INSTANCE.mapToProductResponse(productV01);
    assertEquals(productV01.getProductId(), productResponse.id());
    assertEquals(productV01.getName(), productResponse.name());
    assertEquals(productV01.getDescription(), productResponse.description());
    assertEquals(productV01.getImage(), productResponse.image());
    assertEquals(productV01.getPrice(), productResponse.price());
    assertEquals(productV01.getCategory(), productResponse.category());
  }
}

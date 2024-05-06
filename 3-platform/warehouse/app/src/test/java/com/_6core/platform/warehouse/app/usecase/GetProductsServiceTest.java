package com._6core.platform.warehouse.app.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableProductV01Impl;
import com._6core.platform.warehouse.domain.dto.product.ProductsIds;
import com._6core.platform.warehouse.domain.persistance.out.GetProductsPort;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class GetProductsServiceTest {
  @Mock private GetProductsPort getProductsPort;
  @InjectMocks private GetProductsService getProductsService;

  @Test
  void getProductsByIds_fullProductsIds_valiedFluxProductV01() {
    ProductsIds productIds = new ProductsIds(List.of("1", "2"));
    ProductV01 product1 =
        ImmutableProductV01Impl.builder()
            .productId("1")
            .name("Product 1")
            .description("Description 1")
            .price(BigInteger.valueOf(10))
            .image("image1.jpg")
            .category("Category 1")
            .build();
    ProductV01 product2 =
        ImmutableProductV01Impl.builder()
            .productId("2")
            .name("Product 2")
            .description("Description 2")
            .price(BigInteger.valueOf(20))
            .image("image2.jpg")
            .category("Category 2")
            .build();
    when(getProductsPort.getProductsByIds(productIds)).thenReturn(Flux.just(product1, product2));
    getProductsService
        .getProductsByIds(productIds)
        .log()
        .as(StepVerifier::create)
        .consumeNextWith(
            productV01 -> {
              assertEquals("1", product1.productId());
              assertEquals("Product 1", product1.name());
              assertEquals("Description 1", product1.description());
              assertEquals("image1.jpg", product1.image());
              assertEquals(BigInteger.valueOf(10), product1.price());
              assertEquals("Category 1", product1.category());
            })
        .consumeNextWith(
            productV01 -> {
              assertEquals("2", product2.productId());
              assertEquals("Product 2", product2.name());
              assertEquals("Description 2", product2.description());
              assertEquals("image2.jpg", product2.image());
              assertEquals(BigInteger.valueOf(20), product2.price());
              assertEquals("Category 2", product2.category());
            })
        .verifyComplete();
  }

  @Test
  void getProductsByIds_emptyProductsIds_emptyFluxProductV01() {
    ProductsIds productIds = new ProductsIds(new ArrayList<>());
    when(getProductsPort.getProductsByIds(productIds)).thenReturn(Flux.empty());
    getProductsService.getProductsByIds(productIds).log().as(StepVerifier::create).verifyComplete();
  }
}

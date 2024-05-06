package com._6core.platform.warehouse.app.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableProductV01Impl;
import com._6core.platform.warehouse.domain.dto.product.ProductResponse;
import com._6core.platform.warehouse.domain.dto.product.ProductsIds;
import com._6core.platform.warehouse.domain.mapper.ProductMapper;
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
  @Mock private ProductMapper productMapper;
  @InjectMocks private GetProductsService getProductsService;

  @Test
  void getProductsByIds_fullProductsIds_valiedFluxProductResponse() {
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
    ProductResponse productResponse1 =
        new ProductResponse(
            "1", "Product 1", "Description 1", "image1.jpg", new BigInteger("10"), "Category 1");
    ProductResponse productResponse2 =
        new ProductResponse(
            "2", "Product 2", "Description 2", "image2.jpg", new BigInteger("20"), "Category 2");
    when(productMapper.mapToProductResponse(product1)).thenReturn(productResponse1);
    when(productMapper.mapToProductResponse(product2)).thenReturn(productResponse2);
    getProductsService
        .getProductsByIds(productIds)
        .log()
        .as(StepVerifier::create)
        .consumeNextWith(
            productResponse -> {
              assertEquals(product1.getProductId(), productResponse1.id());
              assertEquals(product1.getName(), productResponse1.name());
              assertEquals(product1.getDescription(), productResponse1.description());
              assertEquals(product1.getImage(), productResponse1.image());
              assertEquals(product1.getPrice(), productResponse1.price());
              assertEquals(product1.getCategory(), productResponse1.category());
            })
        .consumeNextWith(
            productResponse -> {
              assertEquals(product2.getProductId(), productResponse2.id());
              assertEquals(product2.getName(), productResponse2.name());
              assertEquals(product2.getDescription(), productResponse2.description());
              assertEquals(product2.getImage(), productResponse2.image());
              assertEquals(product2.getPrice(), productResponse2.price());
              assertEquals(product2.getCategory(), productResponse2.category());
            })
        .verifyComplete();
  }

  @Test
  void getProductsByIds_emptyProductsIds_emptyFluxProductResponse() {
    ProductsIds productIds = new ProductsIds(new ArrayList<>());
    when(getProductsPort.getProductsByIds(productIds)).thenReturn(Flux.empty());
    getProductsService.getProductsByIds(productIds).log().as(StepVerifier::create).verifyComplete();
  }
}

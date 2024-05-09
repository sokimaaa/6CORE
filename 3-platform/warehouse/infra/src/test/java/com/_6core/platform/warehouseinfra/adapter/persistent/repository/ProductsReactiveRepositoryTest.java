package com._6core.platform.warehouseinfra.adapter.persistent.repository;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ProductEntity;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataR2dbcTest
public class ProductsReactiveRepositoryTest extends TestCase {

    @Autowired
    private ProductsReactiveRepository productsRepository;


    @Test
    public void testGetProductsByIds() {
        // Arrange
        List<String> ids = Arrays.asList("1", "2", "3");

        // Act
        Flux<ProductEntity> productsFlux = productsRepository.getProductsByIds(ids);

        // Assert
        StepVerifier.create(productsFlux)
                .expectNextCount(3) // Assuming there are 3 products with the provided IDs
                .verifyComplete();
    }

}
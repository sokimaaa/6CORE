package com._6core.platform.warehouse.app.service;


import com._6core.platform.warehouse.app.persistance.ProductRepository;
import com._6core.platform.warehouse.app.port.in.ProductUseCase;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.dto.ProductResponse;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ProductService implements ProductUseCase {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Flux<ProductResponse> getProductsByIds(List<String> productsIds) {
        return productRepository.getProductsByIds(productsIds)
                .map(productMapper::toProductResponse);
    }
}

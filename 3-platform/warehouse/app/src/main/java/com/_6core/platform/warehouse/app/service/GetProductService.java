package com._6core.platform.warehouse.app.service;

import com._6core.platform.warehouse.app.port.in.GetProductUseCase;
import com._6core.platform.warehouse.app.port.persistance.ProductRepository;
import com._6core.platform.warehouse.domain.mapper.ProductMapper;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.get.in.product.GetProductsRequest;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.get.out.product.GetProductsResponse;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetProductService implements GetProductUseCase {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public Flux<GetProductsResponse> getProductsByIds(GetProductsRequest request) {
        return productRepository.findProductsByIds(request.productsIds())
                .map(productMapper::toProductResponse);
    }
}

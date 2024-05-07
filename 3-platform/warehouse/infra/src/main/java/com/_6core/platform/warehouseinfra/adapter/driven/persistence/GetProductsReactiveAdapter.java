package com._6core.platform.warehouseinfra.adapter.driven.persistence;

import com._6core.lib.java.domain.model.warehouse.ProductV01;
import com._6core.platform.warehouse.domain.dto.product.ProductsIds;
import com._6core.platform.warehouse.domain.persistance.out.GetProductsPort;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetProductsReactiveAdapter implements GetProductsPort {
    private final ProductRepository productRepository;

    @Override
    public Flux<ProductV01> getProductsByIds(ProductsIds productsIds) {
        return null;
    }
}

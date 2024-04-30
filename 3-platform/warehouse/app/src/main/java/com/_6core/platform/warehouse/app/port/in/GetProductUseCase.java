package com._6core.platform.warehouse.app.port.in;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.get.in.product.GetProductsRequest;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.get.out.product.GetProductsResponse;
import reactor.core.publisher.Flux;

public interface GetProductUseCase {
    Flux<GetProductsResponse> getProductsByIds(GetProductsRequest request);
}

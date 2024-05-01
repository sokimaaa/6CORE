package com._6core.platform.warehouse.app.port.in;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.dto.ProductResponse;
import java.util.List;
import reactor.core.publisher.Flux;

public interface ProductUseCase {
  Flux<ProductResponse> getProductsByIds(List<String> productsIds);
}

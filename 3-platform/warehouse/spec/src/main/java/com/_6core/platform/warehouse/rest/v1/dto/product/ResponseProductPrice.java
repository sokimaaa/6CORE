package com._6core.platform.warehouse.rest.v1.dto.product;

import java.math.BigDecimal;

public record ResponseProductPrice(Long productId, BigDecimal price) {
}

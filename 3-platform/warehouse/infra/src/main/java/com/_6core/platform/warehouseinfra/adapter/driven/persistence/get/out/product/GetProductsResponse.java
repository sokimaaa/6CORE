package com._6core.platform.warehouseinfra.adapter.driven.persistence.get.out.product;

import java.math.BigDecimal;

public record GetProductsResponse(String name,
                                  String description,
                                  String image,
                                  BigDecimal price) {
}

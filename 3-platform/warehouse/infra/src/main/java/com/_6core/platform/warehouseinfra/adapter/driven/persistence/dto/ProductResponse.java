package com._6core.platform.warehouseinfra.adapter.driven.persistence.dto;

import java.math.BigDecimal;

public record ProductResponse(String name,
                              String description,
                              String image,
                              BigDecimal price) {
}

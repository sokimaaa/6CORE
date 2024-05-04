package com._6core.platform.warehouse.domain.dto;

import java.math.BigDecimal;

public record ProductResponse(String name, String description, String image, BigDecimal price) {}
